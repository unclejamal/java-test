package shop;

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
    public void priceMixedBasket() {
        Basket basket = new Basket();
        basket.addProduct(1, new ProductMetadata("apple", "apples", 0.10d));
        basket.addProduct(2, new ProductMetadata("bottle of milk", "bottles of milk", 1.30d));
        basket.addDiscountForValueOf(0.20d);

        BasketPricing actualBasketPricing = basket.getBasketPricing();

        assertThat(actualBasketPricing, equalTo(new BasketPricing(
                2.5d,
                new HashSet<>(asList(
                        new BasketPosition(1, new ProductMetadata("apple", "apples", 0.10d)),
                        new BasketPosition(2, new ProductMetadata("bottle of milk", "bottles of milk", 1.30d))
                )))));
    }

    @Test
    public void countProduct() {
        ProductMetadata apple = new ProductMetadata("apple", "apples", 0.10d);
        Basket basket = new Basket();
        basket.addProduct(10, apple);

        int count = basket.countProduct(apple);

        assertThat(count, equalTo(10));
    }

    @Test
    public void countMissingProduct() {
        ProductMetadata apple = new ProductMetadata("apple", "apples", 0.10d);
        Basket basket = new Basket();

        int count = basket.countProduct(apple);

        assertThat(count, equalTo(0));
    }
}