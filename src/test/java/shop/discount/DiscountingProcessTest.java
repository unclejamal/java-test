package shop.discount;

import org.junit.Test;
import org.mockito.Mockito;
import shop.Basket;

public class DiscountingProcessTest {

    private final Basket basket = Mockito.mock(Basket.class);

    @Test
    public void passesDiscountsToBasket() {
        DiscountingProcess discountingProcess = new DiscountingProcess(
                (basket) -> 0.50d,
                (basket) -> 0.40d
        );

        discountingProcess.applyTo(basket);

        Mockito.verify(basket).addDiscountForValueOf(0.90d);
    }
}