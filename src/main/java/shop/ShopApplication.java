package shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ShopApplication implements Runnable {
    private final PrintWriter writer;
    private final BufferedReader reader;

    public ShopApplication(BufferedReader reader, PrintWriter writer) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        int applesBought = 0;
        while (true) {
            try {
                String command = reader.readLine();
                if (command.equals("quit")) {
                    break;
                }

                if (command.equals("buy 1 apple")) {
                    applesBought++;

                } else if (command.equals("price")) {
                    double totalCost = 0.10 * applesBought;
                    String product = "apple";
                    if (applesBought != 1) {
                        product = "apples";
                    }
                    writer.printf("Total cost: £%.2f (for basket with: %d " + product + ")%n", totalCost, applesBought);

                } else {
                    writer.println("Unknown command");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
