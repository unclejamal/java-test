package shop.discount;

import org.junit.Test;
import shop.Basket;
import shop.ProductMetadata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SingleProductByPercentageDiscountTest {

    private final ProductMetadata apple = new ProductMetadata("apple", "apples", 1.00d);
    private final ProductMetadata orange = new ProductMetadata("orange", "orange", 0.80d);
    private final Basket basket = new Basket();

    private final SingleProductByPercentageDiscount discount = new SingleProductByPercentageDiscount(apple, 10);

    @Test
    public void applicableOnce() {
        basket.addProduct(1, apple);
        double discountValue = discount.applyTo(basket);
        assertThat(discountValue, equalTo(0.10d));
    }

    @Test
    public void applicableTwice() {
        basket.addProduct(2, apple);
        double discountValue = discount.applyTo(basket);
        assertThat(discountValue, equalTo(0.20d));
    }

    @Test
    public void notApplicable_emptyBasket() {
        double discountValue = discount.applyTo(basket);
        assertThat(discountValue, equalTo(0.00d));
    }

    @Test
    public void notApplicable_productMissingInBasket() {
        basket.addProduct(1, orange);
        double discountValue = discount.applyTo(basket);
        assertThat(discountValue, equalTo(0.00d));
    }
}