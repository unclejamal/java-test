package shop;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BuyTwoGetOneForHalfPriceDiscountTest {

    private final ProductMetadata apple = new ProductMetadata("apple", "apples", 1.00d);
    private final ProductMetadata orange = new ProductMetadata("orange", "orange", 0.80d);
    private final Basket basket = new Basket();

    private final BuyTwoGetOneForHalfPriceDiscount discount = new BuyTwoGetOneForHalfPriceDiscount(apple, orange);

    @Test
    public void applicableOnce() {
        basket.addProduct(2, apple);
        basket.addProduct(1, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.40d));
    }

    @Test
    public void applicableTwice() {
        basket.addProduct(4, apple);
        basket.addProduct(2, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.80d));
    }

    @Test
    public void applicableTwiceWithExtraApple() {
        basket.addProduct(5, apple);
        basket.addProduct(2, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.80d));
    }

    @Test
    public void applicableTwiceWithExtraOrange() {
        basket.addProduct(4, apple);
        basket.addProduct(3, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.80d));
    }

    @Test
    public void notApplicable_notEnoughApples() {
        basket.addProduct(1, apple);
        basket.addProduct(1, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.0d));
    }

    @Test
    public void notApplicable_notEnoughOranges() {
        basket.addProduct(2, apple);
        basket.addProduct(0, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.0d));
    }

    @Test
    public void notApplicable_emptyBasket() {
        basket.addProduct(0, apple);
        basket.addProduct(0, orange);

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.0d));
    }
}