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
    public void buyWithoutQuantity() {
        buyCommand.handleBuy(basket, "buy stuff");
        verify(commandLineOutput).showLine("Could you please enter the quantity followed by the name of the product, e.g. \"buy 1 apple\"");
    }

    @Test
    public void buyWithoutProduct() {
        buyCommand.handleBuy(basket, "buy 1");
        verify(commandLineOutput).showLine("Could you please enter the quantity followed by the name of the product, e.g. \"buy 1 apple\"");
    }

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
    public void buyTwoApples() {
        buyCommand.handleBuy(basket, "buy 2 apples");
        verify(basket).addApples(2);
    }

    @Test
    public void buyOneBottleOfMilk() {
        buyCommand.handleBuy(basket, "buy 1 bottle of milk");
        verify(basket).addBottlesOfMilk(1);
    }

    @Test
    public void buyTwoBottlesOfMilk() {
        buyCommand.handleBuy(basket, "buy 2 bottles of milk");
        verify(basket).addBottlesOfMilk(2);
    }

    @Test
    public void buyOneTinOfSoup() {
        buyCommand.handleBuy(basket, "buy 1 tin of soup");
        verify(basket).addTinsOfSoup(1);
    }

    @Test
    public void buyTwoTinsOfSoup() {
        buyCommand.handleBuy(basket, "buy 2 tins of soup");
        verify(basket).addTinsOfSoup(2);
    }


}