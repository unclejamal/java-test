package shop;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        new Main().createShoppingApplicationThread(System.in).start();
    }

    public Thread createShoppingApplicationThread(InputStream in) {
        ShopApplication shopApplication = new ShopApplication(
                new BufferedReader(new InputStreamReader(in))
        );

        return new Thread(shopApplication);
    }
}
