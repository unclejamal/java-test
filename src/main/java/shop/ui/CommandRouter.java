package shop.ui;

import shop.Basket;
import shop.BuyCommand;
import shop.PriceBasketCommand;

public class CommandRouter {
    private CommandLineOutput commandLineOutput;
    private BuyCommand buyCommand;
    private PriceBasketCommand priceBasketCommand;

    public CommandRouter(CommandLineOutput commandLineOutput, BuyCommand buyCommand, PriceBasketCommand priceBasketCommand) {
        this.commandLineOutput = commandLineOutput;
        this.buyCommand = buyCommand;
        this.priceBasketCommand = priceBasketCommand;
    }

    public void handleUserInput(String userInput, Basket basket) {
        if (userInput.startsWith("buy")) {
            buyCommand.handleBuy(basket, userInput);

        } else if (userInput.equals("price")) {
            priceBasketCommand.handlePriceBasket(basket);

        } else {
            commandLineOutput.warnCommandIsUnknown();
        }
    }
}
