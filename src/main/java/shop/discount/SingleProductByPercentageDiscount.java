package shop.discount;

import shop.Basket;
import shop.ProductMetadata;

public class SingleProductByPercentageDiscount implements Discount {

    private final ProductMetadata discountedProduct;
    private final int howManyPercentCheaper;

    public SingleProductByPercentageDiscount(ProductMetadata discountedProduct, int howManyPercentCheaper) {
        this.discountedProduct = discountedProduct;
        this.howManyPercentCheaper = howManyPercentCheaper;
    }

    @Override
    public double applyTo(Basket basket) {
        return basket.countProduct(discountedProduct) * applyPercentage();
    }

    private double applyPercentage() {
        return howManyPercentCheaper * discountedProduct.price / 100;
    }
}
