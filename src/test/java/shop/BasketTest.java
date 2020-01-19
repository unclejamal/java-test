package shop;

import org.hamcrest.Matchers;
import org.junit.Test;
import shop.cli.ProductMetadata;

import java.util.Collections;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasketTest {

    @Test
    public void priceEmptyBasket() {
        Basket basket = new Basket();
        assertThat(basket.getBasketPricing(), equalTo(new BasketPricing(0.0d, Collections.emptySet())));
    }

    @Test
    public void priceMixedBasketOfOneAppleAndTwoBottlesOfMilk() {
        Basket basket = new Basket();
        basket.addApples(1);
        basket.addBottlesOfMilk(2);
        basket.addTinsOfSoup(3);

        BasketPricing actualBasketPricing = basket.getBasketPricing();

        assertThat(actualBasketPricing, Matchers.equalTo(new BasketPricing(
                4.65,
                new HashSet<>(asList(
                        new BasketPosition(1, new ProductMetadata("apple", "apples")),
                        new BasketPosition(2, new ProductMetadata("bottle of milk", "bottles of milk")),
                        new BasketPosition(3, new ProductMetadata("tin of soup", "tins of soup"))
                )))));
    }
}