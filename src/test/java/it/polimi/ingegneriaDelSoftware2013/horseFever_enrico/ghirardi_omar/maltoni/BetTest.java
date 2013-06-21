package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 20/06/13
 * Time: 16.17
 * To change this template use File | Settings | File Templates.
 */
public class BetTest {

    Player bettingPlayer = new Player("Player");
    int amount = 1000;
    Stable bettingStable = new Stable(StableColor.BLUE);
    BetType type = BetType.WINNING;

    Bet bet = new Bet(bettingPlayer, amount, bettingStable, type);

    @Test
    public void testGetAmount() {
        int temp = bet.getAmount();
        assertEquals(1000, temp);
    }

    @Test
    public void testGetBettingPlayer() {
        String temp = bettingPlayer.getIdTag();
        assert ("Player" == temp);
    }

    @Test
    public void testGetBettingStable() {
        StableColor color = bettingStable.getColor();
        assert (StableColor.BLUE == color);
    }

    @Test
    public void testGetType() {
        BetType temp = bet.getType();
        assert (BetType.WINNING == temp);
    }
}
