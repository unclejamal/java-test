package shop;

import java.util.ArrayList;
import java.util.List;

public class BasketPricing extends ValueObject {

    public static final BasketPricing EMPTY_BASKET_PRICING = new BasketPricing(0.00d, new ArrayList<>());

    public final double totalCost;
    public final List<BasketPosition> basketPositions;

    public BasketPricing(double totalCost, List<BasketPosition> basketPositions) {
        this.totalCost = totalCost;
        this.basketPositions = basketPositions;
    }
}
