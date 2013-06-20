package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 20/06/13
 * Time: 18.17
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest {

    Player player = new Player("Player");
    Stable stable = new Stable(StableColor.BLACK);
    BetType type = BetType.WINNING;


    @Test (expected = InvalidBetException.class)
    public void testMakeBetException1() throws Exception {
        player.setMoney(1000);
        player.makeBet(2000,type,stable);
    }

    @Test (expected = InvalidBetException.class)
    public void testMakeBetException2() throws Exception {
        player.setMoney(1000);
        player.setVictoryPoints(2);
        player.makeBet(100,type,stable);
    }

    @Test
    public void testMakeBetMoneySubtraction() throws InvalidBetException {
        player.setMoney(1000);
        player.setVictoryPoints(2);
        player.makeBet(200,type,stable);
        assertEquals(800, player.getMoney());
    }
}
