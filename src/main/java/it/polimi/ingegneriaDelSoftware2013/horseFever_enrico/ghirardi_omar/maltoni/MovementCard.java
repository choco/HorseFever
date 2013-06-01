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

    public int getMovementForQuotation(int quotation) {
        switch (quotation) {
            case 2:
                return movements.get(0);
            case 3:
                return movements.get(1);
            case 4:
                return movements.get(2);
            case 5:
                return movements.get(3);
            case 6:
                return movements.get(4);
            case 7:
                return movements.get(5);
            default:
                break;
        }

        return 0;
    }

    public void setMovements(ArrayList<Integer> movements) {
        this.movements = movements;
    }

    public ArrayList<Integer> getMovements() {
        return movements;
    }
}
