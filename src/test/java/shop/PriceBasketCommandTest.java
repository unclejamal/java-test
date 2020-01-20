package shop;

import org.junit.Test;
import shop.ui.CommandLineOutput;

import static org.mockito.Mockito.*;

public class PriceBasketCommandTest {
    private Basket basket = mock(Basket.class);
    private CommandLineOutput commandLineOutput = mock(CommandLineOutput.class);
    private PriceBasketCommand command = new PriceBasketCommand(commandLineOutput);


    @Test
    public void passesBasketPricingToCommandLineOutput() {
        BasketPricing basketPricing = mock(BasketPricing.class);
        when(basket.getBasketPricing()).thenReturn(basketPricing);

        command.handlePriceBasket(basket);

        verify(commandLineOutput).showBasketPricing(basketPricing);
    }
}