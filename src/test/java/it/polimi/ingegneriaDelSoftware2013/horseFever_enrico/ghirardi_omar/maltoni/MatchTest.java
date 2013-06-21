package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.Match;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.Player;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 21/06/13
 * Time: 10.31
 * To change this template use File | Settings | File Templates.
 */
public class MatchTest {

    Player player1 = new Player("Player1");
    Player player2 = new Player("Player2");
    Player player3 = new Player("Player3");
    Match match = new Match();

    @Test
    public void testSetCurrentPLayer() throws Exception {
        match.addPlayer(player1);
        match.addPlayer(player2);
        match.addPlayer(player3);

        match.setCurrentPLayer(10);
        assertEquals(0, match.getCurrentPLayer());
        String s = "Player1";
        assertTrue(s.equals(match.getPlayers().get(0).getIdTag()));

        match.setCurrentPLayer(-10);
        assertEquals(2, match.getCurrentPLayer());
        s = "Player3";
        assertTrue(s.equals(match.getPlayers().get(2).getIdTag()));

    }

    @Test
    public void testGetFirstPlayer() {
        player1.setFirstPlayer(false);
        player2.setFirstPlayer(false); //just in case
        player3.setFirstPlayer(true);

        match.addPlayer(player1);
        match.addPlayer(player2);
        match.addPlayer(player3);

        Player temp = match.getFirstPlayer();
        String s = "Player3";
        assertTrue(s.equals(temp.getIdTag()));
    }
}
