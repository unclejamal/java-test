package shop.main;

import shop.ProductCatalog;
import shop.ProductMetadata;
import shop.ShopApplication;
import shop.ui.CommandLineOutput;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        new Main().createShoppingApplicationThread(
                System.in,
                System.out,
                loadRealProductCatalog()
        ).start();
    }

    private static ProductCatalog loadRealProductCatalog() {
        return new ProductCatalog(
                new ProductMetadata("apple", "apples", 0.10d),
                new ProductMetadata("bottle of milk", "bottles of milk", 1.30d),
                new ProductMetadata("tin of soup", "tins of soup", 0.65d),
                new ProductMetadata("loaf of bread", "loaves of bread", 0.80d)
        );
    }

    public Thread createShoppingApplicationThread(InputStream in, OutputStream out, ProductCatalog productCatalog) {
        PrintWriter writer = new PrintWriter(out, true);
        ShopApplication shopApplication = new ShopApplication(
                new BufferedReader(new InputStreamReader(in)),
                new CommandLineOutput(writer),
                productCatalog
        );

        return new Thread(shopApplication);
    }
}
