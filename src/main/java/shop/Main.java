package shop;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        new Main().createShoppingApplicationThread(System.in, System.out).start();
    }

    public Thread createShoppingApplicationThread(InputStream in, OutputStream out) {
        ShopApplication shopApplication = new ShopApplication(
                new BufferedReader(new InputStreamReader(in)),
                new PrintWriter(out, true)
        );

        return new Thread(shopApplication);
    }
}
