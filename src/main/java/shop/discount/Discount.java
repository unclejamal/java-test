package shop.discount;

import shop.Basket;

public interface Discount {
    double NO_DISCOUNT = 0.00d;

    double applyTo(Basket basket);
}
