package shop;

import shop.cli.CommandLineOutput;
import shop.cli.ProductMetadata;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        new Main().createShoppingApplicationThread(System.in, System.out).start();
    }

    public Thread createShoppingApplicationThread(InputStream in, OutputStream out) {
        PrintWriter writer = new PrintWriter(out, true);
        ShopApplication shopApplication = new ShopApplication(
                new BufferedReader(new InputStreamReader(in)),
                new CommandLineOutput(writer),
                new ProductCatalog(
                        new ProductMetadata("apple", "apples", 0.10d),
                        new ProductMetadata("bottle of milk", "bottles of milk", 1.30d),
                        new ProductMetadata("tin of soup", "tins of soup", 0.65d),
                        new ProductMetadata("loaf of bread", "loafs of bread", 0.80d)
                )
        );

        return new Thread(shopApplication);
    }
}
