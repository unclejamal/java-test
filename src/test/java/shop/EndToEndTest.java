package shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

public class EndToEndTest {

    private PrintWriter inWriter;
    private Thread shoppingApplicationThread;

    @Before
    public void startTheApp() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);

        shoppingApplicationThread = new Main().createShoppingApplicationThread(new PipedInputStream(inStream));
        shoppingApplicationThread.start();
    }

    @After
    public void waitForTheAppToTerminate() throws Exception {
        System.out.println("Tearing down start");
        shoppingApplicationThread.join(1000);
        if (shoppingApplicationThread.isAlive()) {
            shoppingApplicationThread.interrupt();
            throw new RuntimeException("App is still running");
        }
    }

    @Test
    public void quitTheShop() throws Exception {
        enter("quit");
    }

    private void enter(String command) {
        inWriter.println(command);
    }

}
