package shop;

import org.hamcrest.Matchers;
import org.junit.Test;
import shop.cli.ProductMetadata;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static shop.BasketPricing.EMPTY_BASKET_PRICING;

public class BasketPricingTest {

    @Test
    public void emptyBasketPricing() {
        assertThat(EMPTY_BASKET_PRICING.isEmptyBasket(), Matchers.is(true));
    }

    @Test
    public void nonEmptyBasketPricing() {
        assertThat(someNonEmptyBasketPricing().isEmptyBasket(), Matchers.is(false));
    }

    private BasketPricing someNonEmptyBasketPricing() {
        return new BasketPricing(4.44d, new HashSet<>(asList(new BasketPosition(1, new ProductMetadata("car", "cars", 1000.0d)))));
    }

}