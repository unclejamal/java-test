package shop;

import org.hamcrest.Matchers;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasketTest {

    @Test
    public void priceEmptyBasket() {
        Basket basket = new Basket();
        assertThat(basket.getBasketPricing(), equalTo(new BasketPricing(0.0d, emptyList())));
    }

    @Test
    public void priceMixedBasketOfOneAppleAndTwoBottlesOfMilk() {
        Basket basket = new Basket();
        basket.addApples(1);
        basket.addBottlesOfMilk(2);

        BasketPricing actualBasketPricing = basket.getBasketPricing();

        assertThat(actualBasketPricing, Matchers.equalTo(new BasketPricing(
                2.70,
                asList(
                        new BasketPosition(1, "apple"),
                        new BasketPosition(2, "bottles of milk")
                ))));
    }
}