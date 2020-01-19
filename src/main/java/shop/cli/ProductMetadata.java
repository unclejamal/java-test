package shop.cli;

import shop.ValueObject;

public class ProductMetadata extends ValueObject {
    public final String singularName;
    public final String pluralName;
    public double price;

    public ProductMetadata(String singularName, String pluralName, double price) {
        this.singularName = singularName;
        this.pluralName = pluralName;
        this.price = price;
    }
}
