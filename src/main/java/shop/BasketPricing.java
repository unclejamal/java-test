package shop;

import shop.util.ValueObject;

import java.util.HashSet;
import java.util.Set;

public class BasketPricing extends ValueObject {

    public static final BasketPricing EMPTY_BASKET_PRICING = new BasketPricing(0.00d, new HashSet<>());

    public final double totalCost;
    public final Set<BasketPosition> basketPositions;

    public BasketPricing(double totalCost, Set<BasketPosition> basketPositions) {
        this.totalCost = totalCost;
        this.basketPositions = basketPositions;
    }

    public boolean isEmptyBasket() {
        return basketPositions.isEmpty();
    }
}
