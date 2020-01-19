package shop;

import org.junit.Test;
import org.mockito.Mockito;
import shop.cli.CommandLineOutput;
import shop.cli.ProductMetadata;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PriceBasketCommandTest {
    private Basket basket = Mockito.mock(Basket.class);
    private CommandLineOutput commandLineOutput = Mockito.mock(CommandLineOutput.class);
    private PriceBasketCommand command = new PriceBasketCommand(commandLineOutput);

    @Test
    public void emptyBasket() {
        when(basket.getBasketPricing()).thenReturn(BasketPricing.EMPTY_BASKET_PRICING);
        command.handlePriceBasket(basket);
        verify(commandLineOutput).showBasketPricing(BasketPricing.EMPTY_BASKET_PRICING);
    }

    @Test
    public void nonEmptyBasket() {
        Set<BasketPosition> basketContent = new HashSet<>(asList(
                new BasketPosition(1, new ProductMetadata("apple", "apples", 0.10d)),
                new BasketPosition(3, new ProductMetadata("car", "cars", 1000.0d))
        ));
        BasketPricing basketPricing = new BasketPricing(1.55d, basketContent);
        when(basket.getBasketPricing()).thenReturn(basketPricing);

        command.handlePriceBasket(basket);

        verify(commandLineOutput).showBasketPricing(basketPricing);
    }
}