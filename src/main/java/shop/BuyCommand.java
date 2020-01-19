package shop;

import shop.ui.CommandLineOutput;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyCommand {

    private final ProductCatalog productCatalog;
    private final CommandLineOutput commandLineOutput;

    public BuyCommand(CommandLineOutput commandLineOutput, ProductCatalog productCatalog) {
        this.commandLineOutput = commandLineOutput;
        this.productCatalog = productCatalog;
    }

    public void handleBuy(Basket basket, String command) {
        Pattern pattern = Pattern.compile("^buy (\\d+) (.+)$");
        Matcher matcher = pattern.matcher(command);
        boolean isFound = matcher.find();
        if (!isFound) {
            commandLineOutput.askToCorrectTheBuyCommand();
            return;
        }
        int quantity = Integer.valueOf(matcher.group(1));
        String product = matcher.group(2);


        Optional<ProductMetadata> productMetadata = productCatalog.findProductMetadata(product);
        if (productMetadata.isPresent()) {
            basket.addProduct(quantity, productMetadata.get());

        } else {
            String missingItem = quantity + " " + product;
            commandLineOutput.warnItemIsMissing(missingItem);
        }
    }
}