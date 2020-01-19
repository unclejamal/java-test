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

    @Test
    public void totalCostForEmptyBasket() {
        Basket basket = new Basket();
        assertThat(basket.getTotalCost(), equalTo(0.00d));
    }

    @Test
    public void totalCostForMixedBasketOfOneAppleAndTwoBottlesOfMilk() {
        Basket basket = new Basket();
        basket.addApples(1);
        basket.addBottlesOfMilk(2);
        assertThat(basket.getTotalCost(), equalTo(2.70d));
    }

    @Test
    public void contentOfEmptyBasket() {
        Basket basket = new Basket();
        assertThat(basket.getContent(), equalTo(""));
    }

    @Test
    public void contentOfMixedBasketOfOneAppleAndTwoBottlesOfMilk() {
        Basket basket = new Basket();
        basket.addApples(1);
        basket.addBottlesOfMilk(2);
        assertThat(basket.getContent(), equalTo("1 apple, 2 bottles of milk"));
    }
}