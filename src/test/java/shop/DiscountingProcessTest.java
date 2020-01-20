package shop;

import org.junit.Test;
import org.mockito.Mockito;

public class DiscountingProcessTest {

    private final Basket basket = Mockito.mock(Basket.class);

    @Test
    public void passesDiscountToBasket() {
        DiscountingProcess discountingProcess = new DiscountingProcess((basket) -> 0.50d);
        discountingProcess.applyTo(basket);

        Mockito.verify(basket).addDiscountForValueOf(0.50d);
    }
}