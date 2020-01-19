package shop.ui;

import org.junit.Test;
import shop.Basket;
import shop.BuyCommand;
import shop.PriceBasketCommand;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandRouterTest {

    private final Basket someBasket = new Basket();

    private CommandLineOutput commandLineOutput = mock(CommandLineOutput.class);
    private BuyCommand buyCommand = mock(BuyCommand.class);
    private PriceBasketCommand priceBasketCommand = mock(PriceBasketCommand.class);

    private CommandRouter router = new CommandRouter(commandLineOutput, buyCommand, priceBasketCommand);

    @Test
    public void buy() {
        router.handleUserInput("buy stuff", someBasket);
        verify(buyCommand).handleBuy(someBasket, "buy stuff");
    }

    @Test
    public void priceBasket() {
        router.handleUserInput("price", someBasket);
        verify(priceBasketCommand).handlePriceBasket(someBasket);
    }

    @Test
    public void unknownCommand() {
        router.handleUserInput("random-command", someBasket);
        verify(commandLineOutput).warnCommandIsUnknown();
    }
}