package shop;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasketTest {

    @Test
    public void priceEmptyBasket() {
        Basket basket = new Basket();
        assertThat(basket.getBasketPricing(), equalTo(BasketPricing.EMPTY_BASKET_PRICING));
    }

    @Test
    public void priceMixedBasketOfOneAppleAndTwoBottlesOfMilk() {
        Basket basket = new Basket();
        basket.addProduct(1, new ProductMetadata("apple", "apples", 0.10d));
        basket.addProduct(2, new ProductMetadata("bottle of milk", "bottles of milk", 1.30d));

        BasketPricing actualBasketPricing = basket.getBasketPricing();

        assertThat(actualBasketPricing, Matchers.equalTo(new BasketPricing(
                2.7,
                new HashSet<>(asList(
                        new BasketPosition(1, new ProductMetadata("apple", "apples", 0.10d)),
                        new BasketPosition(2, new ProductMetadata("bottle of milk", "bottles of milk", 1.30d))
                )))));
    }
}