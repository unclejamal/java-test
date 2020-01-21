package shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shop.discount.BuyTwoGetOneForHalfPriceDiscount;
import shop.discount.DiscountingProcess;
import shop.discount.SingleProductByPercentageDiscount;
import shop.discount.TimeLimitedDiscount;
import shop.main.Main;
import shop.time.DateRange;
import shop.time.FrozenClock;
import shop.ui.CommandLineOutput;

import java.io.*;
import java.time.LocalDate;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static shop.time.DateRange.dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth;

public class EndToEndTest {

    private FrozenClock frozenClock;
    private PrintWriter inWriter;
    private BufferedReader outReader;
    private Thread shoppingApplicationThread;

    @Before
    public void startTheApp() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        ProductMetadata apple = new ProductMetadata("apple", "apples", 0.10d);
        ProductMetadata bottleOfMilk = new ProductMetadata("bottle of milk", "bottles of milk", 1.30d);
        ProductMetadata tinOfSoup = new ProductMetadata("tin of soup", "tins of soup", 0.65d);
        ProductMetadata loafOfBread = new ProductMetadata("loaf of bread", "loaves of bread", 0.80d);

        ProductCatalog productCatalog = new ProductCatalog(apple, bottleOfMilk, tinOfSoup, loafOfBread);

        frozenClock = new FrozenClock();
        frozenClock.setTodayTo(LocalDate.of(2020, 1, 20));

        DiscountingProcess discountingProcess = new DiscountingProcess(
                new TimeLimitedDiscount(
                        frozenClock,
                        DateRange.dateRangeFromYesterdayAndValidForSevenDays(frozenClock.today()),
                        new BuyTwoGetOneForHalfPriceDiscount(tinOfSoup, loafOfBread)
                ),
                new TimeLimitedDiscount(
                        frozenClock,
                        dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth(frozenClock.today()),
                        new SingleProductByPercentageDiscount(apple, 10)
                )
        );


        shoppingApplicationThread = new Main().createShoppingApplicationThread(
                new PipedInputStream(inStream),
                new PipedOutputStream(outStream),
                productCatalog,
                discountingProcess
        );
        shoppingApplicationThread.start();
    }

    @After
    public void waitForTheAppToTerminate() throws Exception {
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
        enter("buy 1 loaf of bread");
        enter("price");
        assertOutputLines("Total cost: £3.95",
                "Basket content: 3 apples, 1 bottle of milk, 1 loaf of bread, 3 tins of soup");
        enter("quit");
    }

    @Test
    public void acceptanceTest_3TinsOfSoup2LoavesOfBreadBoughtToday() throws Exception {
        enter("buy 3 tins of soup");
        enter("buy 2 loaves of bread");
        enter("price");
        assertOutputLines("Total cost: £3.15",
                "Basket content: 2 loaves of bread, 3 tins of soup");
        enter("quit");
    }

    @Test
    public void acceptanceTest_6ApplesAndABottleOfMilkBoughtToday() throws Exception {
        enter("buy 6 apples");
        enter("buy 1 bottle of milk");
        enter("price");
        assertOutputLines("Total cost: £1.90",
                "Basket content: 6 apples, 1 bottle of milk");
        enter("quit");
    }

    @Test
    public void acceptanceTest_6ApplesAndABottleOfMilkBoughtIn5DaysTime() throws Exception {
        frozenClock.setTodayTo(LocalDate.of(2020, 1, 25));

        enter("buy 6 apples");
        enter("buy 1 bottle of milk");
        enter("price");
        assertOutputLines("Total cost: £1.84",
                "Basket content: 6 apples, 1 bottle of milk");
        enter("quit");
    }

    @Test
    public void acceptanceTest_3ApplesAnd2TinsOfSoupAndALoafOfBreadBoughtIn5DaysTime() throws Exception {
        frozenClock.setTodayTo(LocalDate.of(2020, 1, 25));

        enter("buy 3 apples");
        enter("buy 2 tins of soup");
        enter("buy 1 loaf of bread");
        enter("price");
        assertOutputLines("Total cost: £1.97",
                "Basket content: 3 apples, 1 loaf of bread, 2 tins of soup");
        enter("quit");
    }

    @Test
    public void buyNonExistingProduct() throws Exception {
        enter("buy 1 nonExistingProduct");
        assertOutputLines("Henry's Groceries doesn't have \"1 nonExistingProduct\" at this point. Anything else we can help with?");
        enter("quit");
    }

    @Test
    public void priceEmptyBasket() throws Exception {
        enter("price");
        assertOutputLines("Total cost: £0.00",
                "Basket content: <empty>");
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
