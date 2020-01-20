package shop;

import org.junit.Test;
import shop.ui.CommandLineOutput;

import static org.mockito.Mockito.*;

public class PriceBasketCommandTest {
    private Basket basket = mock(Basket.class);
    private CommandLineOutput commandLineOutput = mock(CommandLineOutput.class);
    private DiscountingProcess discountingProcess = mock(DiscountingProcess.class);
    private BasketPricing basketPricing = mock(BasketPricing.class);

    private PriceBasketCommand command = new PriceBasketCommand(commandLineOutput, discountingProcess);

    @Test
    public void passesBasketPricingToCommandLineOutput() {
        when(basket.getBasketPricing()).thenReturn(basketPricing);

        command.handlePriceBasket(basket);

        verify(commandLineOutput).showBasketPricing(basketPricing);
    }
}