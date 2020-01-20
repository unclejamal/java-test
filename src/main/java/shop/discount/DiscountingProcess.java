package shop.discount;

import shop.Basket;

import java.util.List;

import static java.util.Arrays.asList;

public class DiscountingProcess {
    private List<Discount> discounts;

    public DiscountingProcess(Discount... discounts) {
        this.discounts = asList(discounts);
    }

    public void applyTo(Basket basket) {
        double totalDiscount = discounts.stream()
                .mapToDouble(discount -> discount.applyTo(basket))
                .sum();

        basket.addDiscountForValueOf(totalDiscount);
    }
}
