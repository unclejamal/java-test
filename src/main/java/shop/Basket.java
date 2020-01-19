package shop;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private int apples = 0;
    private int bottlesOfMilk = 0;

    public void addApples(int number) {
        apples += number;
    }

    public void addBottlesOfMilk(int bottles) {
        bottlesOfMilk += bottles;
    }

    private double getTotalCost() {
        return 0.10d * apples + 1.30d * bottlesOfMilk;
    }

    private List<BasketPosition> getBasketPositions() {
        List<BasketPosition> basketPositions = new ArrayList<>();

        if (apples > 0) {
            String product = "apple";
            if (apples != 1) {
                product = "apples";
            }

            basketPositions.add(new BasketPosition(apples, product));
        }

        if (bottlesOfMilk > 0) {
            String product = "bottle of milk";
            if (bottlesOfMilk != 1) {
                product = "bottles of milk";
            }

            basketPositions.add(new BasketPosition(bottlesOfMilk, product));
        }

        return basketPositions;
    }

    public BasketPricing getBasketPricing() {
        return new BasketPricing(
                getTotalCost(),
                getBasketPositions()
        );
    }
}
