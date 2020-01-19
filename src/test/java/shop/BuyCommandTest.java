package shop;

import org.junit.Test;
import org.mockito.Mockito;
import shop.cli.CommandLineOutput;

import static org.mockito.Mockito.verify;

public class BuyCommandTest {

    private Basket basket = Mockito.mock(Basket.class);
    private CommandLineOutput commandLineOutput = Mockito.mock(CommandLineOutput.class);
    private BuyCommand buyCommand = new BuyCommand(commandLineOutput);

    @Test
    public void buyOneNonExistingProduct() {
        buyCommand.handleBuy(basket, "buy 1 moon");
        verify(commandLineOutput).showLine("Henry's Groceries doesn't have \"1 moon\" at this point. Anything else we can help with?");
    }

    @Test
    public void buyOneApple() {
        buyCommand.handleBuy(basket, "buy 1 apple");
        verify(basket).addApples(1);
    }

    @Test
    public void buyOneBottleOfMilk() {
        buyCommand.handleBuy(basket, "buy 1 bottle of milk");
        verify(basket).addBottlesOfMilk(1);
    }


}