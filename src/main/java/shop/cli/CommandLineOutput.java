package shop.cli;

import shop.BasketPricing;

import java.io.PrintWriter;
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
                .map(bp -> bp.quantity + " " + bp.product)
                .collect(Collectors.joining(", "));
    }
}
