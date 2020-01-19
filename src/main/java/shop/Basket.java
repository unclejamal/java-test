package shop;

public class Basket {
    private int apples = 0;
    private int bottlesOfMilk = 0;

    public void addApples(int number) {
        apples += number;
    }

    public void addBottlesOfMilk(int bottles) {
        bottlesOfMilk += bottles;
    }

    public int getApples() {
        return apples;
    }

    public int getBottlesOfMilk() {
        return bottlesOfMilk;
    }
}
