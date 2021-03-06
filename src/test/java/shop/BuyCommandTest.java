package shop;

import org.junit.Test;
import org.mockito.Mockito;
import shop.ui.CommandLineOutput;

import static org.mockito.Mockito.verify;

public class BuyCommandTest {

    private Basket basket = Mockito.mock(Basket.class);
    private CommandLineOutput commandLineOutput = Mockito.mock(CommandLineOutput.class);
    private BuyCommand buyCommand = new BuyCommand(
            commandLineOutput,
            new ProductCatalog(
                    new ProductMetadata("apple", "apples", 0.10d),
                    new ProductMetadata("bottle of milk", "bottles of milk", 1.30d),
                    new ProductMetadata("tin of soup", "tins of soup", 0.65d)
            )
    );

    @Test
    public void buyWithoutQuantity() {
        buyCommand.handleBuy(basket, "buy stuff");
        verify(commandLineOutput).askToCorrectTheBuyCommand();
    }

    @Test
    public void buyWithoutProduct() {
        buyCommand.handleBuy(basket, "buy 1");
        verify(commandLineOutput).askToCorrectTheBuyCommand();
    }

    @Test
    public void buyOneNonExistingProduct() {
        buyCommand.handleBuy(basket, "buy 1 moon");
        verify(commandLineOutput).warnItemIsMissing("1 moon");
    }

    @Test
    public void buyOneApple() {
        buyCommand.handleBuy(basket, "buy 1 apple");
        verify(basket).addProduct(1, new ProductMetadata("apple", "apples", 0.10d));
    }

    @Test
    public void buyTwoApples() {
        buyCommand.handleBuy(basket, "buy 2 apples");
        verify(basket).addProduct(2, new ProductMetadata("apple", "apples", 0.10d));
    }

    @Test
    public void buyOneBottleOfMilk() {
        buyCommand.handleBuy(basket, "buy 1 bottle of milk");
        verify(basket).addProduct(1, new ProductMetadata("bottle of milk", "bottles of milk", 1.30d));
    }

    @Test
    public void buyTwoBottlesOfMilk() {
        buyCommand.handleBuy(basket, "buy 2 bottles of milk");
        verify(basket).addProduct(2, new ProductMetadata("bottle of milk", "bottles of milk", 1.30d));
    }

    @Test
    public void buyOneTinOfSoup() {
        buyCommand.handleBuy(basket, "buy 1 tin of soup");
        verify(basket).addProduct(1, new ProductMetadata("tin of soup", "tins of soup", 0.65d));
    }

    @Test
    public void buyTwoTinsOfSoup() {
        buyCommand.handleBuy(basket, "buy 2 tins of soup");
        verify(basket).addProduct(2, new ProductMetadata("tin of soup", "tins of soup", 0.65d));
    }


}