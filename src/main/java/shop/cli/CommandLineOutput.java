package shop.cli;

import shop.BasketPosition;
import shop.BasketPricing;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CommandLineOutput {
    public static final String PROMPT = "> ";

    private PrintWriter writer;

    public CommandLineOutput(PrintWriter writer) {
        this.writer = writer;
    }

    public void showPrompt() {
        writer.print(PROMPT);
        writer.flush();
    }

    public void showLine(String line) {
        writer.println(line);
    }

    public void showBasketPricing(BasketPricing basketPricing) {
        showLine(String.format("Total cost: £%.2f", basketPricing.totalCost));
        showLine(String.format("Basket content: %s", getJoin(basketPricing)));
    }

    private String getJoin(BasketPricing basketPricing) {
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
