package shop;

import shop.util.ValueObject;

public class BasketPosition extends ValueObject {

    public int quantity;
    public ProductMetadata productMetadata;

    public BasketPosition(int quantity, ProductMetadata productMetadata) {
        this.quantity = quantity;
        this.productMetadata = productMetadata;
    }
}
