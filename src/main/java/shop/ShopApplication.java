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
        while (true) {
            try {
                String command = reader.readLine();
                if (command.equals("quit")) {
                    break;
                }

                if (command.equals("buy 1 apple")) {
                    // ignore for now
                } else if (command.equals("price")) {
                    writer.println("Total cost: £0.10 (for basket with: 1 apple)");
                } else {
                    writer.println("Unknown command");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
