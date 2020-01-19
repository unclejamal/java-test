package shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class EndToEndTest {

    private PrintWriter inWriter;
    private BufferedReader outReader;
    private Thread shoppingApplicationThread;

    @Before
    public void startTheApp() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        shoppingApplicationThread = new Main().createShoppingApplicationThread(new PipedInputStream(inStream), new PipedOutputStream(outStream));
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

    @Test
    public void unknownCommand() throws Exception {
        enter("watch a movie");
        assertOutputLines("Unknown command");
        enter("quit");
    }

    @Test
    public void priceOneApple() throws Exception {
        enter("buy 1 apple");
        enter("price");
        assertOutputLines("Total cost: £0.10 (for basket with: 1 apple)");
        enter("quit");
    }

    private void enter(String command) {
        inWriter.println(command);
    }

    private void assertOutputLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line);
        }
    }

    private void read(String expectedLine) throws IOException {
        String actualLine = outReader.readLine();
        assertEquals(expectedLine, actualLine);
    }

}
