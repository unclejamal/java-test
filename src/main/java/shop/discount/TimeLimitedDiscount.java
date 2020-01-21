package shop.discount;

import shop.Basket;
import shop.time.Clock;
import shop.time.DateRange;

import java.time.LocalDate;

public class TimeLimitedDiscount implements Discount {

    private final Clock clock;
    private final DateRange discountValidityRange;
    private final Discount delegateDiscount;

    public TimeLimitedDiscount(Clock clock, DateRange discountValidityRange, Discount delegateDiscount) {
        this.clock = clock;
        this.discountValidityRange = discountValidityRange;
        this.delegateDiscount = delegateDiscount;
    }

    @Override
    public double applyTo(Basket basket) {
        LocalDate today = clock.today();
        if (!discountValidityRange.includes(today)) {
            return NO_DISCOUNT;
        }

        return delegateDiscount.applyTo(basket);
    }
}
