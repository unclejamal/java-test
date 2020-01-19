package shop;

import shop.cli.CommandLineOutput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyCommand {
    private CommandLineOutput commandLineOutput;

    public BuyCommand(CommandLineOutput commandLineOutput) {
        this.commandLineOutput = commandLineOutput;
    }

    public void handleBuy(Basket basket, String command) {
        Pattern pattern = Pattern.compile("^buy (\\d+) (.+)$");
        Matcher matcher = pattern.matcher(command);
        boolean isFound = matcher.find();
        if (!isFound) {
            commandLineOutput.showLine("Could you please enter the quantity followed by the name of the product, e.g. \"buy 1 apple\"");
            return;
        }
        int quantity = Integer.valueOf(matcher.group(1));
        String product = matcher.group(2);


        if (product.equals("apple") || product.equals("apples")) {
            basket.addApples(quantity);

        } else if (product.equals("bottle of milk") || product.equals("bottles of milk")) {
            basket.addBottlesOfMilk(quantity);

        } else if (product.equals("tin of soup") || product.equals("tins of soup")) {
            basket.addTinsOfSoup(quantity);

        } else {
            String missingItem = quantity + " " + product;
            commandLineOutput.showLine("Henry's Groceries doesn't have \"" + missingItem + "\" at this point. Anything else we can help with?");
        }
    }
}
