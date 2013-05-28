package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 28/05/13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class MovementCard extends Card {
    private ArrayList<Integer> movements;

    public MovementCard() {
        movements = new ArrayList<Integer>();
    }

    public void setMovements(ArrayList<Integer> movements) {
        this.movements = movements;
    }

    public ArrayList<Integer> getMovements() {
        return movements;
    }
}
