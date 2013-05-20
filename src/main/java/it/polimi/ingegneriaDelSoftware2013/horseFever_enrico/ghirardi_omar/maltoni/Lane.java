package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class Lane implements Comparable<Lane> {
    private Stable stable;
    private Horse horse;
    private ArrayList<ActionCard> actionPile;


    void addActionCard(ActionCard card) {
        ;
    }

    Horse getHorse() {
        return horse;
    }

    public int compareTo(Lane lane) {
        if (this.horse.getCurrentPosition() > lane.horse.getCurrentPosition())
            return 1;
        else if (this.horse.getCurrentPosition() == lane.horse.getCurrentPosition())
            return 0;
        else
            return -1;
    }
}
