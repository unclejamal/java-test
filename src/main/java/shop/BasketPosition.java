package shop;

import shop.util.ValueObject;

public class BasketPosition extends ValueObject {

    public final int quantity;
    public final ProductMetadata productMetadata;

    public BasketPosition(int quantity, ProductMetadata productMetadata) {
        this.quantity = quantity;
        this.productMetadata = productMetadata;
    }
}
