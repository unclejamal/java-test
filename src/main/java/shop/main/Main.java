package shop.main;

import shop.*;
import shop.ui.CommandLineOutput;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        ProductMetadata apple = new ProductMetadata("apple", "apples", 0.10d);
        ProductMetadata bottleOfMilk = new ProductMetadata("bottle of milk", "bottles of milk", 1.30d);
        ProductMetadata tinOfSoup = new ProductMetadata("tin of soup", "tins of soup", 0.65d);
        ProductMetadata loafOfBread = new ProductMetadata("loaf of bread", "loaves of bread", 0.80d);

        ProductCatalog productCatalog = new ProductCatalog(
                apple,
                bottleOfMilk,
                tinOfSoup,
                loafOfBread
        );

        DiscountingProcess discountingProcess = new DiscountingProcess(
                new BuyTwoGetOneForHalfPriceDiscount(tinOfSoup, loafOfBread)
        );

        new Main().createShoppingApplicationThread(
                System.in,
                System.out,
                productCatalog,
                discountingProcess
        ).start();
    }

    public Thread createShoppingApplicationThread(InputStream in,
                                                  OutputStream out,
                                                  ProductCatalog productCatalog,
                                                  DiscountingProcess discountingProcess) {

        PrintWriter writer = new PrintWriter(out, true);
        ShopApplication shopApplication = new ShopApplication(
                new BufferedReader(new InputStreamReader(in)),
                new CommandLineOutput(writer),
                productCatalog,
                discountingProcess
        );

        return new Thread(shopApplication);
    }
}
