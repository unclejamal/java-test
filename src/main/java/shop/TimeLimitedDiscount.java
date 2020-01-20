package shop;

import shop.time.Clock;
import shop.time.DateRange;

import java.time.LocalDate;

public class TimeLimitedDiscount implements Discount {

    private Clock clock;
    private DateRange discountValidityRange;
    private Discount delegateDiscount;

    public TimeLimitedDiscount(Clock clock, DateRange discountValidityRange, Discount delegateDiscount) {
        this.clock = clock;
        this.discountValidityRange = discountValidityRange;
        this.delegateDiscount = delegateDiscount;
    }

    @Override
    public double applyTo(Basket basket) {
        LocalDate today = clock.today();
        if (!discountValidityRange.includes(today)) {
            return 0.00d;
        }

        return delegateDiscount.applyTo(basket);
    }
}
