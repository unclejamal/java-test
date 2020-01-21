package shop.discount;

import org.junit.Test;
import org.mockito.Mockito;
import shop.Basket;

import static shop.discount.Discount.NO_DISCOUNT;

public class DiscountingProcessTest {

    private final Basket basket = Mockito.mock(Basket.class);

    @Test
    public void noDiscountsExist() {
        DiscountingProcess discountingProcess = new DiscountingProcess();

        discountingProcess.applyTo(basket);

        Mockito.verify(basket).addDiscountForValueOf(NO_DISCOUNT);
    }

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