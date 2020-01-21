package shop.ui;

import shop.BasketPosition;
import shop.BasketPricing;
import shop.ProductMetadata;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CommandLineOutput {
    public static final String PROMPT = "> ";

    private PrintWriter writer;

    public CommandLineOutput(PrintWriter writer) {
        this.writer = writer;
    }

    public void welcomeTheCustomer() {
        writer.println("Welcome to Henry's Groceries. Here's what you can do:");
        writer.println("- buy: e.g. \"buy 1 apple\", \"buy 2 bottles of milk\", \"buy 3 loaves of bread\", \"buy 4 tins of soup\"");
        writer.println("- price: shows the total price of the basket");
        writer.println("- quit: quits the shop");
        writer.println("");
        writer.flush();
    }

    public void showPrompt() {
        writer.print(PROMPT);
        writer.flush();
    }

    public void showBasketPricing(BasketPricing basketPricing) {
        writer.println(String.format("Total cost: £%.2f", basketPricing.totalCost));
        writer.println(String.format("Basket content: %s", getJoin(basketPricing)));
    }

    public void warnItemIsMissing(String missingItem) {
        writer.println("Henry's Groceries doesn't have \"" + missingItem + "\" at this point. Anything else we can help with?");
    }

    public void askToCorrectTheBuyCommand() {
        writer.println("Could you please enter the quantity followed by the name of the product, e.g. \"buy 1 apple\"");
    }

    public void warnCommandIsUnknown() {
        writer.println("Unknown command");
    }

    private String getJoin(BasketPricing basketPricing) {
        if (basketPricing.isEmptyBasket()) {
            return "<empty>";
        }

        return basketPricing.basketPositions.stream()
                .sorted(Comparator.comparing(basketPosition -> basketPosition.productMetadata.singularName))
                .map(CommandLineOutput::asString)
                .collect(Collectors.joining(", "));
    }

    public static String asString(BasketPosition basketPosition) {
        return basketPosition.quantity + " " + productMetadataAsString(basketPosition.productMetadata, basketPosition.quantity);
    }

    private static String productMetadataAsString(ProductMetadata productMetadata, int quantity) {
        return quantity == 1 ? productMetadata.singularName : productMetadata.pluralName;
    }
}
