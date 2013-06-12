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
    private boolean gotPlaced;
    private ArrayList<ActionCard> actionPile;

    private static final int DID_NOT_CHANGE = -100;

    public Horse(Stable ownerStable) {
        resetVars();
        actionPile = new ArrayList<ActionCard>();
        this.ownerStable = ownerStable;
    }

    boolean didFixedStartStepsChange() {
        if (fixedStartSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didAddStartStepsChange() {
        if (addStartSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didFixedSprintStepsChange() {
        if (fixedSprintSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didAddSprintStepsChange() {
        if (addSprintSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didWinsPhotofinishChange() {
        if (winsPhotofinish != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didCanMoveAfterFinishLineChange() {
        if (canMoveAfterFinishLine != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didAddFinishStepsChange() {
        if (addFinishSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didIsLastFixedStepsChange() {
        if (isLastFixedSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

    boolean didIsFirstFixedStepsChange() {
        if (isFirstFixedSteps != DID_NOT_CHANGE)
            return true;
        return false;
    }

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
        if (position < 0)
            position = 0;
        this.currentPosition = position;
    }

    public boolean hasFinishedRace() {
        return finishedRace;
    }

    public void setHasFinishedRace(boolean finished) {
        finishedRace = finished;
    }

    public void setGotPlaced(boolean placed) {
        gotPlaced = placed;
    }

    public boolean gotPlaced() {
        return gotPlaced;
    }

    public void resetVars() {
        fixedStartSteps =
                addStartSteps =
                        fixedSprintSteps =
                                addSprintSteps =
                                        winsPhotofinish =
                                                canMoveAfterFinishLine =
                                                        addFinishSteps =
                                                                isLastFixedSteps =
                                                                        isFirstFixedSteps = DID_NOT_CHANGE;
        currentPosition = 0;
        finishedRace = false;
        gotPlaced = false;
    }
}
