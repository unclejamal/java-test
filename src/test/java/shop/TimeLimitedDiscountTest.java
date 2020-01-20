package shop;

import org.junit.Test;
import org.mockito.Mockito;
import shop.time.Clock;
import shop.time.DateRange;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TimeLimitedDiscountTest {

    private Basket basket = Mockito.mock(Basket.class);

    private DateRange discountValidityRange = new DateRange(
            LocalDate.of(2020, 1, 20),
            LocalDate.of(2020, 1, 22)
    );

    @Test
    public void delegatesIfDiscountIsApplicable() {
        Clock todaysClock = () -> LocalDate.of(2020, 1, 20);

        TimeLimitedDiscount discount = new TimeLimitedDiscount(
                todaysClock,
                discountValidityRange,
                (basket) -> 1.00d
        );

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(1.00));
    }

    @Test
    public void returnsNoDiscountIfDiscountIsNotApplicable() {
        Clock todaysClock = () -> LocalDate.of(2020, 1, 1);

        TimeLimitedDiscount discount = new TimeLimitedDiscount(
                todaysClock,
                discountValidityRange,
                (basket) -> 1.00d
        );

        double discountValue = discount.applyTo(basket);

        assertThat(discountValue, equalTo(0.00));
    }
}