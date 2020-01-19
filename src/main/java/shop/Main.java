package shop;

import shop.cli.CommandLineOutput;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        new Main().createShoppingApplicationThread(System.in, System.out).start();
    }

    public Thread createShoppingApplicationThread(InputStream in, OutputStream out) {
        final PrintWriter writer = new PrintWriter(out, true);
        ShopApplication shopApplication = new ShopApplication(
                new BufferedReader(new InputStreamReader(in)),
                new CommandLineOutput(writer)
        );

        return new Thread(shopApplication);
    }
}
