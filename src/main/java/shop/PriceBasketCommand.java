package shop;

import shop.discount.DiscountingProcess;
import shop.ui.CommandLineOutput;

public class PriceBasketCommand {
    private final CommandLineOutput commandLineOutput;
    private final DiscountingProcess discountingProcess;

    public PriceBasketCommand(CommandLineOutput commandLineOutput, DiscountingProcess discountingProcess) {
        this.commandLineOutput = commandLineOutput;
        this.discountingProcess = discountingProcess;
    }

    public void handlePriceBasket(Basket basket) {
        discountingProcess.applyTo(basket);
        BasketPricing summary = basket.getBasketPricing();
        commandLineOutput.showBasketPricing(summary);
    }
}
