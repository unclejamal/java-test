package shop;

public class BasketPosition extends ValueObject {

    public int quantity;
    public String product;

    public BasketPosition(int quantity, String product) {
        this.quantity = quantity;
        this.product = product;
    }
}
