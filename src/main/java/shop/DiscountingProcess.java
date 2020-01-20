package shop;

public class DiscountingProcess {
    private Discount discount;

    public DiscountingProcess(Discount discount) {
        this.discount = discount;
    }

    public void applyTo(Basket basket) {
        basket.addDiscountForValueOf(discount.applyTo(basket));
    }
}
