package shop;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasketTest {

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
        assertThat(basket.getContent(), equalTo("<empty>"));
    }

    @Test
    public void contentOfMixedBasketOfOneAppleAndTwoBottlesOfMilk() {
        Basket basket = new Basket();
        basket.addApples(1);
        basket.addBottlesOfMilk(2);
        assertThat(basket.getContent(), equalTo("1 apple, 2 bottles of milk"));
    }
}