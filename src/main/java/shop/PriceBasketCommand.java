package shop;

import shop.cli.CommandLineOutput;

public class PriceBasketCommand {
    private CommandLineOutput commandLineOutput;

    public PriceBasketCommand(CommandLineOutput commandLineOutput) {
        this.commandLineOutput = commandLineOutput;
    }

    public void handlePriceBasket(Basket basket) {
        commandLineOutput.showLine(String.format("Total cost: £%.2f", basket.getTotalCost()));
        commandLineOutput.showLine(String.format("Basket content: %s", basket.getContent()));
    }
}
