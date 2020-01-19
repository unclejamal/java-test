package shop;

import shop.ui.CommandLineOutput;

public class PriceBasketCommand {
    private CommandLineOutput commandLineOutput;

    public PriceBasketCommand(CommandLineOutput commandLineOutput) {
        this.commandLineOutput = commandLineOutput;
    }

    public void handlePriceBasket(Basket basket) {
        BasketPricing summary = basket.getBasketPricing();
        commandLineOutput.showBasketPricing(summary);
    }
}
