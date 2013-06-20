package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 20/06/13
 * Time: 18.04
 * To change this template use File | Settings | File Templates.
 */
public class StableTest {

    Stable stable1 = new Stable(StableColor.BLACK);

    Stable stable2 = new Stable(StableColor.BLUE);

    @Test
    public void testCompareTo(){
        stable1.getHorse().setCurrentPosition(10);
        stable2.getHorse().setCurrentPosition(9);
        int temp = stable1.compareTo(stable2);
        assertEquals(-1, temp);

        int temp2 = stable2.compareTo(stable1);
        assertEquals(1, temp2);

        stable1.getHorse().setCurrentPosition(10);
        stable2.getHorse().setCurrentPosition(10);
        int temp3 = stable1.compareTo(stable2);
        assertEquals(0, temp3);
        assertEquals(0, stable2.compareTo(stable1));

    }
}
