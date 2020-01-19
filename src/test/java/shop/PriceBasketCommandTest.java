package shop;

import org.junit.Test;
import org.mockito.Mockito;
import shop.cli.CommandLineOutput;

public class PriceBasketCommandTest {
    private CommandLineOutput commandLineOutput = Mockito.mock(CommandLineOutput.class);
    private PriceBasketCommand command = new PriceBasketCommand(commandLineOutput);

    @Test
    public void emptyBasket() {
        Basket basket = new Basket();
        command.handlePriceBasket(basket);
        Mockito.verify(commandLineOutput).showLine("Total cost: £0.00");
        Mockito.verify(commandLineOutput).showLine("Basket content: <empty>");
    }
}