package shop.discount;

import shop.Basket;

public interface Discount {
    double applyTo(Basket basket);
}
