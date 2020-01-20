package shop;

public class BuyTwoGetOneForHalfPriceDiscount {
    private final ProductMetadata buyTwoProduct;
    private final ProductMetadata halfPriceProduct;

    public BuyTwoGetOneForHalfPriceDiscount(ProductMetadata buyTwoProduct, ProductMetadata halfPriceProduct) {
        this.buyTwoProduct = buyTwoProduct;
        this.halfPriceProduct = halfPriceProduct;
    }

    public double applyTo(Basket basket) {
        int buyTwoProductCount = basket.countProduct(buyTwoProduct);
        int halfPriceProductCount = basket.countProduct(halfPriceProduct);

        int maxNumberOfApplications = buyTwoProductCount / 2;
        int actualNumberOfApplications = Math.min(maxNumberOfApplications, halfPriceProductCount);

        return actualNumberOfApplications * (halfPriceProduct.price / 2);
    }
}
