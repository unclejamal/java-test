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
                String command = reader.readLine();
                if (command.equals("quit")) {
                    break;
                }

                if (command.equals("buy 1 apple")) {
                    basket.addApples(1);

                } else if (command.equals("price")) {
                    double totalCost = 0.10 * basket.getApples();
                    String product = "apple";
                    if (basket.getApples() != 1) {
                        product = "apples";
                    }
                    commandLineOutput.showLine(String.format("Total cost: £%.2f (for basket with: %d " + product + ")%n", totalCost, basket.getApples()));

                } else {
                    commandLineOutput.showLine("Unknown command");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
