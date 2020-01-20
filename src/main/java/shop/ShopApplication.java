package shop;

import shop.discount.DiscountingProcess;
import shop.ui.CommandLineOutput;
import shop.ui.CommandRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class ShopApplication implements Runnable {
    private final BufferedReader reader;
    private final CommandLineOutput commandLineOutput;
    private final CommandRouter commandRouter;

    public ShopApplication(BufferedReader reader,
                           CommandLineOutput commandLineOutput,
                           ProductCatalog productCatalog,
                           DiscountingProcess discountingProcess) {

        this.commandLineOutput = commandLineOutput;
        this.reader = reader;
        this.commandRouter = new CommandRouter(
                commandLineOutput,
                new BuyCommand(commandLineOutput, productCatalog),
                new PriceBasketCommand(commandLineOutput, discountingProcess)
        );
    }

    @Override
    public void run() {
        Basket basket = new Basket();

        while (true) {
            try {
                commandLineOutput.showPrompt();
                String userInput = reader.readLine();

                if (userInput.equals("quit")) {
                    break;
                }

                commandRouter.handleUserInput(userInput, basket);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
