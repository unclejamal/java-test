package shop;

import shop.util.ValueObject;

public class ProductMetadata extends ValueObject {
    public final String singularName;
    public final String pluralName;
    public double price;

    public ProductMetadata(String singularName, String pluralName, double price) {
        this.singularName = singularName;
        this.pluralName = pluralName;
        this.price = price;
    }

    public boolean matches(String product) {
        return singularName.equals(product) || pluralName.equals(product);
    }
}
