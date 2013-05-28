package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public class Horse {
    private Stable ownerStable;
    //private HorseCard horseCard;
    private int fixedStartSteps;
    private int addStartSteps;
    private int fixedSprintSteps;
    private int addSprintSteps;
    private int currentPosition;
    private boolean finishedRace;
    private ArrayList<ActionCard> actionPile;

    void addActionCard(ActionCard card) {
        actionPile.add(card);
    }

    void applyActionCards() {

    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }

    public boolean hasFinishedRace() {
        return finishedRace;
    }
}
