package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.Player;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 21/06/13
 * Time: 11.04
 * To change this template use File | Settings | File Templates.
 */
public class MatchControllerTest {

    Player firstPlayer = new Player("FirstPlayer");
    MatchController matchController = new MatchController();


    @Test
    public void testSetNextFirstPlayer() {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Player1");
        strings.add("Player2");
        strings.add("Player3");
        matchController.setPlayers(strings);
        firstPlayer.setFirstPlayer(true);
        matchController.getMatch().addPlayer(firstPlayer);  //adds first player as the last element of players array list

        matchController.setNextFirstPlayer();

        String s = "Player1";
        assertTrue(s.equals(matchController.getMatch().getFirstPlayer().getIdTag()));
    }

  /*  @Test
    public void testGetNextPlayer(){
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Player p3 = new Player("Player3");
        Player p4 = new Player("Player4");
        p1.setFirstPlayer(false); //just in case
        p2.setFirstPlayer(true);
        p3.setFirstPlayer(false); //just in case
        p4.setFirstPlayer(false);

        matchController.getMatch().addPlayer(p1);
        matchController.getMatch().addPlayer(p2);
        matchController.getMatch().addPlayer(p3);
        matchController.getMatch().addPlayer(p4);


        matchController.getMatch().setCurrentPLayer(1); //p2 is the current player
        matchController.updatePlayersOrder();
        matchController.getMatch().setMatchPhase(MatchPhase.FIRST_BET_PHASE);
        String s1 = matchController.getNextPlayer().getIdTag();
        assertTrue(s1.equals("Player3"));

        matchController.getMatch().setMatchPhase(MatchPhase.RIG_PHASE);
        String s2 = matchController.getNextPlayer().getIdTag();
        assertTrue(s2.equals("Player3"));

        matchController.getMatch().setMatchPhase(MatchPhase.SECOND_BET_PHASE);
        String s3 = matchController.getNextPlayer().getIdTag();
        assertTrue(s3.equals("Player4"));
    }                                                     */
}
