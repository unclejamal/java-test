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

                writer.println("Unknown command");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
