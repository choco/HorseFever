package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 20/06/13
 * Time: 16.34
 * To change this template use File | Settings | File Templates.
 */
public class BetManagerTest {

    Player bettingPlayer = new Player("Player1");
    int amount = 500;
    Stable bettingStable = new Stable(StableColor.BLUE);
    BetType type = BetType.WINNING;
    Bet bet1 = new Bet(bettingPlayer,amount,bettingStable,type); //first bet
    Bet bet2 = new Bet(bettingPlayer,amount,bettingStable,BetType.PLACED); //second bet, same stable different bet type

    ArrayList<Bet> bets = new ArrayList<Bet>();

    Map<StableColor, Integer> betMarkPool = new HashMap<StableColor, Integer>();
    BetManager betManager = new BetManager(betMarkPool);

    @Test (expected = InvalidBetException.class)
    public void testInsertBet() throws Exception {
        betMarkPool.put(StableColor.BLUE,0);        //no more blue bet marks
        betManager.insertBet(bet1);                 //throws a NOT_ENOUGH_BET_MARKS exception
    }

    @Test (expected = InvalidBetException.class)
    public void testCheckBetValidity() throws Exception {
        betMarkPool.put(StableColor.BLUE, 0); //no more blue bet marks
        betManager.checkBetValidity(bet1);   //throws a NOT_ENOUGH_BET_MARKS exception
    }

    @Test
    public void testCheckWinningBet(){
        assertTrue(betManager.checkWinningBet(bet1, 1));  //winning bet
        assertTrue(betManager.checkWinningBet(bet2,3));   //placed
        assertFalse(betManager.checkWinningBet(bet1, 6)); //losing bet
    }

    @Test
    public void testPayBet(){
        bettingStable.setQuotation(2);     //stable quotation = 2
        bettingPlayer.setMoney(500);       //player's money after the bet, before receiving the payment
        bettingPlayer.setVictoryPoints(2); //player's vp after the bet, before receiving the payment
        betManager.payBet(bet1);           //bet manager pays the bet (bet1)
        assertEquals(1500, bettingPlayer.getMoney());
        assertEquals(5, bettingPlayer.getVictoryPoints());

        bettingStable.setQuotation(2);     //stable quotation = 2
        bettingPlayer.setMoney(500);       //player's money after the bet, before receiving the payment
        bettingPlayer.setVictoryPoints(2); //player's vp after the bet, before receiving the payment
        betManager.payBet(bet2);           //bet manager pays the bet
        assertEquals(1500, bettingPlayer.getMoney());
        assertEquals(3, bettingPlayer.getVictoryPoints());

    }

    @Test
    public void testPaymentTime(){

    }
}
