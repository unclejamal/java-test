package shop;

import shop.ui.CommandLineOutput;

import java.io.BufferedReader;
import java.io.IOException;

public class ShopApplication implements Runnable {
    private final BufferedReader reader;
    private final CommandLineOutput commandLineOutput;
    private final BuyCommand buyCommand;

    public ShopApplication(BufferedReader reader, CommandLineOutput commandLineOutput, ProductCatalog productCatalog) {
        this.commandLineOutput = commandLineOutput;
        this.reader = reader;
        this.buyCommand = new BuyCommand(commandLineOutput, productCatalog);
    }

    @Override
    public void run() {
        Basket basket = new Basket();

        while (true) {
            try {
                commandLineOutput.showPrompt();

                String command = reader.readLine();
                if (command.equals("quit")) {
                    break;
                }

                if (command.startsWith("buy")) {
                    buyCommand.handleBuy(basket, command);

                } else if (command.equals("price")) {
                    new PriceBasketCommand(commandLineOutput).handlePriceBasket(basket);

                } else {
                    commandLineOutput.warnCommandIsUnknown();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
