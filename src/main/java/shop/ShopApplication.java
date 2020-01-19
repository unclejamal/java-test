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

                if (command.equals("buy 1 apple")) {
                    basket.addApples(1);

                } else if (command.equals("buy 1 bottle of milk")) {
                    basket.addBottlesOfMilk(1);

                } else if (command.equals("price")) {
                    priceBasket(basket);

                } else {
                    commandLineOutput.showLine("Unknown command");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void priceBasket(Basket basket) {
        if (basket.getBottlesOfMilk() > 0) {
            double totalCost = 1.30 * basket.getBottlesOfMilk();
            String product = "bottle of milk";
            if (basket.getBottlesOfMilk() != 1) {
                product = "bottles of milk";
            }
            commandLineOutput.showLine(String.format("Total cost: £%.2f", totalCost));
            commandLineOutput.showLine(String.format("Basket content: %d %s", basket.getBottlesOfMilk(), product));

        } else {
            double totalCost = 0.10 * basket.getApples();
            String product = "apple";
            if (basket.getApples() != 1) {
                product = "apples";
            }
            commandLineOutput.showLine(String.format("Total cost: £%.2f", totalCost));
            commandLineOutput.showLine(String.format("Basket content: %d %s", basket.getApples(), product));
        }
    }

}
