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

    public double getTotalCost() {
        return 0.10d * apples + 1.30d * bottlesOfMilk;
    }

    public String getContent() {
        List<String> basketContent = new ArrayList<>();

        if (apples > 0) {
            String product = "apple";
            if (apples != 1) {
                product = "apples";
            }

            basketContent.add(String.format("%d %s", apples, product));
        }

        if (bottlesOfMilk > 0) {
            String product = "bottle of milk";
            if (bottlesOfMilk != 1) {
                product = "bottles of milk";
            }

            basketContent.add(String.format("%d %s", bottlesOfMilk, product));
        }

        return String.join(", ", basketContent);
    }
}
