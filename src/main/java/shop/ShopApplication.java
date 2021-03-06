package shop;

import shop.main.ApplicationData;
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
                           ApplicationData applicationData) {

        this.commandLineOutput = commandLineOutput;
        this.reader = reader;
        this.commandRouter = new CommandRouter(
                commandLineOutput,
                new BuyCommand(commandLineOutput, applicationData.productCatalog),
                new PriceBasketCommand(commandLineOutput, applicationData.discountingProcess)
        );
    }

    @Override
    public void run() {
        Basket basket = new Basket();
        commandLineOutput.welcomeTheCustomer();

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
