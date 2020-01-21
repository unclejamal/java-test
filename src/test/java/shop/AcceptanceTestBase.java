package shop;

import org.junit.After;
import org.junit.Before;
import shop.main.ApplicationData;
import shop.main.Main;
import shop.time.FrozenClock;
import shop.ui.CommandLineOutput;

import java.io.*;
import java.time.LocalDate;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class AcceptanceTestBase {

    protected PrintWriter inWriter;
    protected BufferedReader outReader;
    protected FrozenClock frozenClock = new FrozenClock();
    protected Thread shoppingApplicationThread;

    protected abstract LocalDate givenTodayIs();

    protected abstract ApplicationData givenTheShopRunsWith();

    @Before
    public void startTheApp() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));
        frozenClock.setTodayTo(givenTodayIs());

        ApplicationData applicationData = givenTheShopRunsWith();

        shoppingApplicationThread = new Main().createShoppingApplicationThread(
                new PipedInputStream(inStream),
                new PipedOutputStream(outStream),
                applicationData
        );
        shoppingApplicationThread.start();

        goPastTheWelcomeMessage();
    }

    @After
    public void waitForTheAppToTerminate() throws Exception {
        shoppingApplicationThread.join(1000);
        if (shoppingApplicationThread.isAlive()) {
            shoppingApplicationThread.interrupt();
            throw new RuntimeException("App is still running");
        }
    }

    protected void enter(String command) throws IOException {
        read(CommandLineOutput.PROMPT);
        inWriter.println(command);
    }

    private void goPastTheWelcomeMessage() throws IOException {
        String line = null;
        while (!"".equals(line)) {
            line = outReader.readLine();
        }
    }

    protected void assertOutputLines(String... expectedOutput) throws IOException {
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
