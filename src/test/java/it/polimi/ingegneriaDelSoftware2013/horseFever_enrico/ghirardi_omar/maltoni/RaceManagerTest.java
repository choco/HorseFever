package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

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

    Map<Stable,Integer> standing;
    Stable stable1 = new Stable(StableColor.BLACK);
    Stable stable2 = new Stable(StableColor.BLUE);
    Stable stable3 = new Stable(StableColor.GREEN);
    ArrayList<Stable> stables;



    @Test
    public void testUpdateStableQuotations() throws Exception {
        stable1.setQuotation(2);
        stable2.setQuotation(2);
        stable3.setQuotation(4);
        standing = new HashMap<Stable, Integer>();
        standing.put(stable1,1);
        standing.put(stable2,2);
        standing.put(stable3,2);
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
    public void testMakeHorseSprint(){


    }

    @Test
    public void testFixStandingBasedOnQuotation(){

    }
}
