package shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Basket {
    private Map<ProductMetadata, Integer> quantities = new HashMap<>();

    public void addProduct(int quantityToBeAdded, ProductMetadata productMetadata) {
        quantities.merge(productMetadata, quantityToBeAdded, Integer::sum);
    }

    private double getTotalCost() {
        return quantities.entrySet().stream()
                .mapToDouble(e -> e.getKey().price * e.getValue())
                .sum();
    }

    private Set<BasketPosition> getBasketPositions() {
        return quantities.entrySet().stream()
                .map(e -> new BasketPosition(e.getValue(), e.getKey()))
                .collect(Collectors.toSet());
    }

    public BasketPricing getBasketPricing() {
        return new BasketPricing(
                getTotalCost(),
                getBasketPositions()
        );
    }

    public int countProduct(ProductMetadata productMetadata) {
        return quantities.getOrDefault(productMetadata, 0);
    }

    public void addDiscountForValueOf(double discountValue) {

    }
}
