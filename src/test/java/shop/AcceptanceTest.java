package shop;

import org.junit.Test;
import shop.discount.BuyTwoGetOneForHalfPriceDiscount;
import shop.discount.DiscountingProcess;
import shop.discount.SingleProductByPercentageDiscount;
import shop.discount.TimeLimitedDiscount;
import shop.main.ApplicationData;

import java.time.LocalDate;

import static shop.time.DateRange.dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth;
import static shop.time.DateRange.dateRangeFromYesterdayAndValidForSevenDays;

public class AcceptanceTest extends AcceptanceTestBase {

    @Override
    protected LocalDate givenTodayIs() {
        return LocalDate.of(2020, 1, 20);
    }

    @Override
    protected ApplicationData givenTheShopRunsWith() {
        ProductMetadata apple = new ProductMetadata("apple", "apples", 0.10d);
        ProductMetadata bottleOfMilk = new ProductMetadata("bottle of milk", "bottles of milk", 1.30d);
        ProductMetadata tinOfSoup = new ProductMetadata("tin of soup", "tins of soup", 0.65d);
        ProductMetadata loafOfBread = new ProductMetadata("loaf of bread", "loaves of bread", 0.80d);
        ProductCatalog productCatalog = new ProductCatalog(apple, bottleOfMilk, tinOfSoup, loafOfBread);

        DiscountingProcess discountingProcess = new DiscountingProcess(
                new TimeLimitedDiscount(
                        frozenClock,
                        dateRangeFromYesterdayAndValidForSevenDays(LocalDate.of(2020, 1, 20)),
                        new BuyTwoGetOneForHalfPriceDiscount(tinOfSoup, loafOfBread)
                ),
                new TimeLimitedDiscount(
                        frozenClock,
                        dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth(LocalDate.of(2020, 1, 20)),
                        new SingleProductByPercentageDiscount(apple, 10)
                )
        );

        return new ApplicationData(productCatalog, discountingProcess);
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

}
