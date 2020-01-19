package shop;

import shop.cli.CommandLineOutput;

public class BuyCommand {
    private CommandLineOutput commandLineOutput;

    public BuyCommand(CommandLineOutput commandLineOutput) {
        this.commandLineOutput = commandLineOutput;
    }

    public void handleBuy(Basket basket, String command) {
        if (command.equals("buy 1 apple")) {
            basket.addApples(1);

        } else if (command.equals("buy 1 bottle of milk")) {
            basket.addBottlesOfMilk(1);

        } else {
            String product = command.replaceFirst("buy ", "");
            commandLineOutput.showLine("Henry's Groceries doesn't have \"" + product + "\" at this point. Anything else we can help with?");
        }
    }
}
