package shop;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasketTest {

    @Test
    public void emptyBasket() {
        Basket basket = new Basket();
        assertThat(basket.getApples(), equalTo(0));
    }

    @Test
    public void addApples() {
        Basket basket = new Basket();
        basket.addApples(1);
        assertThat(basket.getApples(), equalTo(1));
    }

    @Test
    public void addApplesMultipleTimes() {
        Basket basket = new Basket();
        basket.addApples(1);
        basket.addApples(2);
        assertThat(basket.getApples(), equalTo(3));
    }
}