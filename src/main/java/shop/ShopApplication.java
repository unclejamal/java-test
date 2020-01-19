package shop;

import shop.cli.CommandLineOutput;

import java.io.BufferedReader;
import java.io.IOException;

public class ShopApplication implements Runnable {
    private final BufferedReader reader;
    private final CommandLineOutput commandLineOutput;

    public ShopApplication(BufferedReader reader, CommandLineOutput commandLineOutput) {
        this.commandLineOutput = commandLineOutput;
        this.reader = reader;
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
                    new BuyCommand(commandLineOutput).handleBuy(basket, command);

                } else if (command.equals("price")) {
                    handlePriceBasket(basket);

                } else {
                    handleUnknownCommand();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handlePriceBasket(Basket basket) {
        commandLineOutput.showLine(String.format("Total cost: £%.2f", basket.getTotalCost()));
        commandLineOutput.showLine(String.format("Basket content: %s", basket.getContent()));
    }

    private void handleUnknownCommand() {
        commandLineOutput.showLine("Unknown command");
    }

}
