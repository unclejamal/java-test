package shop.cli;

import shop.ValueObject;

public class ProductMetadata extends ValueObject {
    public final String singularName;
    public final String pluralName;

    public ProductMetadata(String singularName, String pluralName) {
        this.singularName = singularName;
        this.pluralName = pluralName;
    }
}
