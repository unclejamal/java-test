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
    public void priceOneApple() throws Exception {
        enter("buy 1 apple");
        enter("price");
        assertOutputLines(                "Total cost: £0.10",
                "Basket content: 1 apple");
        enter("quit");
    }

    @Test
    public void priceTwoApples() throws Exception {
        enter("buy 1 apple");
        enter("buy 1 apple");
        enter("price");
        assertOutputLines(
                "Total cost: £0.20",
                "Basket content: 2 apples"
        );
        enter("quit");
    }

    @Test
    public void priceOneBottleOfMilk() throws Exception {
        enter("buy 1 bottle of milk");
        enter("price");
        assertOutputLines(                "Total cost: £1.30",
                "Basket content: 1 bottle of milk");
        enter("quit");
    }

    @Test
    public void priceTwoBottlesOfMilk() throws Exception {
        enter("buy 1 bottle of milk");
        enter("buy 1 bottle of milk");
        enter("price");
        assertOutputLines(                "Total cost: £2.60",
                "Basket content: 2 bottles of milk");
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
