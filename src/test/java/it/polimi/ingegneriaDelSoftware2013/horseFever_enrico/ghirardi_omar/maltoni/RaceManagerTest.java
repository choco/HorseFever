package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.CardType;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.Deck;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.Stable;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.StableColor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 20/06/13
 * Time: 18.46
 * To change this template use File | Settings | File Templates.
 */
public class RaceManagerTest {

    Map<Stable, Integer> standing;
    Stable stable1 = new Stable(StableColor.BLACK);
    Stable stable2 = new Stable(StableColor.BLUE);
    Stable stable3 = new Stable(StableColor.GREEN);
    Stable stable4 = new Stable(StableColor.RED);
    Stable stable5 = new Stable(StableColor.YELLOW);
    Stable stable6 = new Stable(StableColor.WHITE);
    ArrayList<Stable> stables = new ArrayList<Stable>();


    @Test
    public void testUpdateStableQuotations() throws Exception {
        stable1.setQuotation(2);
        stable2.setQuotation(2);
        stable3.setQuotation(4);
        standing = new HashMap<Stable, Integer>();
        standing.put(stable1, 1);
        standing.put(stable2, 2);
        standing.put(stable3, 2);
        stables = new ArrayList<Stable>();
        stables.add(stable1);
        stables.add(stable2);
        stables.add(stable3);
        Deck movD = new Deck(CardType.MOVEMENT);

        RaceManager raceManager = new RaceManager(stables, movD);
        raceManager.setStanding(standing);

        raceManager.updateStableQuotations();

        assertEquals(2, stable1.getQuotation());
        assertEquals(3, stable2.getQuotation());
        assertEquals(3, stable3.getQuotation());

    }

    @Test
    public void testUpdateStanding() throws Exception {
        stable1.getHorse().setGotPlaced(true);
        stable2.getHorse().setCurrentPosition(9);
        stable3.getHorse().setCurrentPosition(11);
        stable4.getHorse().setCurrentPosition(11);
        stable5.getHorse().setCurrentPosition(9);
        stable6.getHorse().setCurrentPosition(11);

        standing = new HashMap<Stable, Integer>();
        standing.put(stable1, 1);
        standing.put(stable2, 5);
        standing.put(stable3, 2);
        standing.put(stable4, 4);
        standing.put(stable5, 6);
        standing.put(stable6, 3);

        /*
        Current standing:
        1st - BLACK (got placed)
        2nd - GREEN
        3rd - WHITE
        4th - RED
        5th - BLUE
        6th - YELLOW
         */

        stables.add(stable1);
        stables.add(stable2);
        stables.add(stable3);
        stables.add(stable4);
        stables.add(stable5);
        stables.add(stable6);

        Deck movD = new Deck(CardType.MOVEMENT);

        RaceManager raceManager = new RaceManager(stables, movD);
        raceManager.setStanding(standing);

        raceManager.updateStanding();

        /*
        Expected standing
        1st - BLACK (got placed)
        2nd - GREEN,WHITE,RED
        5th - BLUE,YELLOW
         */

        for (Stable stable : standing.keySet()) {
            switch (stable.getColor()) {

                case BLACK:
                    assertEquals(1, (int) standing.get(stable));
                    break;
                case BLUE:
                    assertEquals(5, (int) standing.get(stable));
                    break;
                case GREEN:
                    assertEquals(2, (int) standing.get(stable));
                    break;
                case RED:
                    assertEquals(2, (int) standing.get(stable));
                    break;
                case YELLOW:
                    assertEquals(5, (int) standing.get(stable));
                    break;
                case WHITE:
                    assertEquals(2, (int) standing.get(stable));
                    break;
            }
        }

    }

    @Test
    public void testMakeHorseSprint() {


    }

    @Test
    public void testFixStandingBasedOnQuotation() {

    }
}
