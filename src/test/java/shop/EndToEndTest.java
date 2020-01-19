package shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shop.cli.CommandLineOutput;

import java.io.*;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    public void priceMixedBasket() throws Exception {
        enter("buy 1 apple");
        enter("buy 2 apples");
        enter("buy 1 bottle of milk");
        enter("buy 3 tins of soup");
        enter("price");
        assertOutputLines("Total cost: £3.55",
                "Basket content: 3 apples, 1 bottle of milk, 3 tins of soup");
        enter("quit");
    }

    @Test
    public void buyNonExistingProduct() throws Exception {
        enter("buy 1 nonExistingProduct");
        assertOutputLines("Henry's Groceries doesn't have \"1 nonExistingProduct\" at this point. Anything else we can help with?");
        enter("quit");
    }

    private void enter(String command) throws IOException {
        read(CommandLineOutput.PROMPT);
        inWriter.println(command);
    }

    private void assertOutputLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

}
