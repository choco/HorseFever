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
    private int winsPhotofinish;
    private int canMoveAfterFinishLine;
    private int addFinishSteps;
    private int isLastFixedSteps;
    private int isFirstFixedSteps;
    private int currentPosition;
    private boolean finishedRace;
    private ArrayList<ActionCard> actionPile;

    public Stable getOwnerStable() {
        return ownerStable;
    }

    public void setOwnerStable(Stable ownerStable) {
        this.ownerStable = ownerStable;
    }

    public int getFixedStartSteps() {
        return fixedStartSteps;
    }

    public void setFixedStartSteps(int fixedStartSteps) {
        this.fixedStartSteps = fixedStartSteps;
    }

    public int getAddStartSteps() {
        return addStartSteps;
    }

    public void setAddStartSteps(int addStartSteps) {
        this.addStartSteps = addStartSteps;
    }

    public int getFixedSprintSteps() {
        return fixedSprintSteps;
    }

    public void setFixedSprintSteps(int fixedSprintSteps) {
        this.fixedSprintSteps = fixedSprintSteps;
    }

    public int getAddSprintSteps() {
        return addSprintSteps;
    }

    public void setAddSprintSteps(int addSprintSteps) {
        this.addSprintSteps = addSprintSteps;
    }

    public int getWinsPhotofinish() {
        return winsPhotofinish;
    }

    public void setWinsPhotofinish(int winsPhotofinish) {
        this.winsPhotofinish = winsPhotofinish;
    }

    public int getCanMoveAfterFinishLine() {
        return canMoveAfterFinishLine;
    }

    public void setCanMoveAfterFinishLine(int canMoveAfterFinishLine) {
        this.canMoveAfterFinishLine = canMoveAfterFinishLine;
    }

    public int getAddFinishSteps() {
        return addFinishSteps;
    }

    public void setAddFinishSteps(int addFinishSteps) {
        this.addFinishSteps = addFinishSteps;
    }

    public int getLastFixedSteps() {
        return isLastFixedSteps;
    }

    public void setLastFixedSteps(int lastFixedSteps) {
        isLastFixedSteps = lastFixedSteps;
    }

    public int getFirstFixedSteps() {
        return isFirstFixedSteps;
    }

    public void setFirstFixedSteps(int firstFixedSteps) {
        isFirstFixedSteps = firstFixedSteps;
    }

    public boolean isFinishedRace() {
        return finishedRace;
    }

    public void setFinishedRace(boolean finishedRace) {
        this.finishedRace = finishedRace;
    }

    void addActionCard(ActionCard card) {
        actionPile.add(card);
    }

    ArrayList<ActionCard> getActionPile() {
        return actionPile;
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

    public void resetVars() {
        this.fixedStartSteps = 0;
        this.addStartSteps = 0;
        this.fixedSprintSteps = 0;
        this.addSprintSteps = 0;
        this.currentPosition = 0;
        this.finishedRace = false;
        this.actionPile = null;
    }
}
