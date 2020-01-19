package shop;

import shop.cli.ProductMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Basket {
    private int apples = 0;
    private int bottlesOfMilk = 0;
    private int tinsOfSoup = 0;
    private Map<ProductMetadata, Integer> quantities = new HashMap<>();

    public void addApples(int number) {
        apples += number;
        quantities.merge(new ProductMetadata("apple", "apples"), number, Integer::sum);
    }

    public void addBottlesOfMilk(int bottles) {
        bottlesOfMilk += bottles;
        quantities.merge(new ProductMetadata("bottle of milk", "bottles of milk"), bottles, Integer::sum);
    }

    public void addTinsOfSoup(int tins) {
        tinsOfSoup += tins;
        quantities.merge(new ProductMetadata("tin of soup", "tins of soup"), tins, Integer::sum);
    }

    private double getTotalCost() {
        return 0.10d * apples + 1.30d * bottlesOfMilk + 0.65d * tinsOfSoup;
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
}
