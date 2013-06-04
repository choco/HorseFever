package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */

enum RacePhase {
    START,
    MIDDLE,
    SPRINT,
    FINISH
}

public class RaceManager {
    private ArrayList<Horse> horsesList;
    private Deck movementCardDeck;
    private Map<Stable, Integer> standing;
    private ArrayList<ActionCard> playedActionCards;
    private RacePhase racePhase;

    private static final int FINISH_LINE = 12; //no. of steps to finish line
    private static final int STANDARD_SPRINT_STEPS = 1;

    private static final String FIXED_START_STEPS = "fixedStartSteps";
    private static final String ADD_START_STEPS = "addStartSteps";
    private static final String ADD_SPRINT_STEPS = "addSprintSteps";
    private static final String FIXED_SPRINT_STEPS = "fixedSprintSteps";
    private static final String WINS_PHOTOFINISH = "winsPhotofinish";
    private static final String ADD_FINISH_STEPS = "addFinishSteps";
    private static final String IS_LAST_FIXED_STEPS = "isLastFixedSteps";
    private static final String CAN_MOVE_AFTER_FINISH_LINE = "canMoveAfterFinishLine";
    private static final String IS_FIRST_FIXED_STEPS = "isFirstFixedSteps";
    private static final String ADD_QUOTATION = "addQuotation";
    private static final String REMOVE_NEGATIVE_ACTIONCARDS = "removeNegativeActioncards";
    private static final String REMOVE_POSITIVE_ACTIONCARDS = "removePositiveActioncards";

    public RaceManager(Stable[] stables, Deck movementCardDeck) {
        for (Stable stable : stables) {
            standing.put(stable, 0);
        }

        int size = stables.length;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random generator = new Random();

        while (numbers.size() < size) {
            int random = generator.nextInt(size) + 2; //quotation starts from 2
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        for (int i = 0; i < size && !numbers.isEmpty(); i++) {
            stables[i].setQuotation(numbers.remove(0));
        }

        this.movementCardDeck = movementCardDeck;
        playedActionCards = new ArrayList<ActionCard>();
    }

    public void removeActionCardsOfTypeFromHorse(ActionType actionType, Horse horse) {
        for (ActionCard card : horse.getActionPile()) {
            if (card.getType() == actionType) {
                playedActionCards.add(card);
                horse.getActionPile().remove(card);
            }
        }
    }

    public boolean allHorsesFinishedRace() {
        for (Horse horse : horsesList) {
            if (!horse.hasFinishedRace())
                return false;
        }

        return true;
    }

    public void checkIfHorseArrived(Horse horse) {
        if (horse.getCurrentPosition() >= FINISH_LINE) {
            if (horse.didAddFinishStepsChange())
                horse.setCurrentPosition(horse.getCurrentPosition() + horse.getAddFinishSteps());
            else if (horse.didCanMoveAfterFinishLineChange())
                horse.setCurrentPosition(FINISH_LINE);

            horse.setFinishedRace(true);
        }
    }

    public void makeHorseSprint(Horse horse) {
        int current = horse.getCurrentPosition();
        if (horse.didFixedSprintStepsChange()) {
            if (horse.didAddSprintStepsChange())
                horse.setCurrentPosition(current + horse.getFixedSprintSteps() + horse.getAddSprintSteps());
            else
                horse.setCurrentPosition(current + horse.getFixedSprintSteps());
        } else {
            if (horse.didAddSprintStepsChange())
                horse.setCurrentPosition(current + horse.getAddSprintSteps() + STANDARD_SPRINT_STEPS);
            else
                horse.setCurrentPosition(current + STANDARD_SPRINT_STEPS);
        }
    }

    public void applyMovementCardToHorse(MovementCard card, Horse horse) {
        int baseMovement = card.getMovementForQuotation(horse.getOwnerStable().getQuotation());

        switch (racePhase) {

            case START:
                if (horse.didFixedStartStepsChange()) {
                    if (horse.didAddStartStepsChange())
                        horse.setCurrentPosition(horse.getFixedStartSteps() + horse.getAddStartSteps());
                    else
                        horse.setCurrentPosition(horse.getFixedStartSteps());
                } else {
                    if (horse.didAddStartStepsChange())
                        horse.setCurrentPosition(baseMovement + horse.getAddStartSteps());
                    else
                        horse.setCurrentPosition(baseMovement);
                }
                break;
            case MIDDLE:
                if (horse.didIsFirstFixedStepsChange()) {
                    if (isHorseFirst(horse))
                        horse.setCurrentPosition(horse.getCurrentPosition() + horse.getFirstFixedSteps());
                } else if (horse.didIsLastFixedStepsChange()) {
                    if (isHorseLast(horse))
                        horse.setCurrentPosition(horse.getCurrentPosition() + horse.getLastFixedSteps());
                } else
                    horse.setCurrentPosition(horse.getCurrentPosition() + baseMovement);
                break;

        }

    }

    public void applyNeutralCards() {
        for (Horse horse : horsesList) {
            for (ActionCard card : horse.getActionPile()) {
                if (card.getType() == ActionType.NEUTRAL) {
                    if (card.getAction().equals(ADD_QUOTATION))
                        horse.getOwnerStable().setQuotation(horse.getOwnerStable().getQuotation() + card.getActionValue());
                    else if (card.getAction().equals(REMOVE_NEGATIVE_ACTIONCARDS))
                        removeActionCardsOfTypeFromHorse(ActionType.NEGATIVE, horse);
                    else if (card.getAction().equals(REMOVE_POSITIVE_ACTIONCARDS))
                        removeActionCardsOfTypeFromHorse(ActionType.POSITIVE, horse);
                }
            }
        }
    }

    public void applyMovementRelatedActionCardsToHorse(Horse horse) {

        for (ActionCard card : horse.getActionPile()) {

            if (card.getAction().equals(FIXED_START_STEPS))
                horse.setFixedStartSteps(card.getActionValue());

            else if (card.getAction().equals(ADD_START_STEPS))
                horse.setAddStartSteps(horse.getAddStartSteps() + card.getActionValue());

            else if (card.getAction().equals(FIXED_SPRINT_STEPS))
                horse.setFixedSprintSteps(card.getActionValue());

            else if (card.getAction().equals(ADD_SPRINT_STEPS))
                horse.setAddSprintSteps(horse.getAddSprintSteps() + card.getActionValue());

            else if (card.getAction().equals(WINS_PHOTOFINISH))
                horse.setWinsPhotofinish(card.getActionValue());

            else if (card.getAction().equals(ADD_FINISH_STEPS))
                horse.setAddFinishSteps(horse.getAddFinishSteps() + card.getActionValue());

            else if (card.getAction().equals(IS_LAST_FIXED_STEPS))
                horse.setLastFixedSteps(card.getActionValue());

            else if (card.getAction().equals(IS_FIRST_FIXED_STEPS))
                horse.setFirstFixedSteps(card.getActionValue());

            else if (card.getAction().equals(CAN_MOVE_AFTER_FINISH_LINE))
                horse.setCanMoveAfterFinishLine(card.getActionValue());

        }

    }

    public void removeSameCharCards() {

        for (Horse horse : horsesList) {
            for (int i = 0; i < horse.getActionPile().size(); i++) {
                for (int j = 0; j < horse.getActionPile().size(); j++) {
                    if (j != i && horse.getActionPile().get(j).getCardLetter() == horse.getActionPile().get(i).getCardLetter()) {

                        playedActionCards.add(horse.getActionPile().remove(j));
                        playedActionCards.add(horse.getActionPile().remove(i));
                    }
                }
            }
        }
    }


    public StableColor throwSprintDice() {

        Random r = new Random();
        int roll = r.nextInt(horsesList.size() - 1);

        switch (roll) {
            case 0:
                return StableColor.BLACK;
            case 1:
                return StableColor.BLUE;
            case 2:
                return StableColor.GREEN;
            case 3:
                return StableColor.RED;
            case 4:
                return StableColor.WHITE;
            case 5:
                return StableColor.YELLOW;
            default:
                break;
        }
        return null;
    }

    void startRace() {      // handles the start

        MovementCard movementCard = (MovementCard) movementCardDeck.draw();
        checkActionCardsAtStart();
        for (Horse horse : horsesList) {
            applyMovementCardToHorse(movementCard, horse);
        }

    }

    void raceTurn() {        // handles every race turn


    }


    void checkActionCardsAtStart() {

        removeSameCharCards();
        applyNeutralCards();

        for (Horse horse : horsesList) {

            applyMovementRelatedActionCardsToHorse(horse);

        }


    }

    /* currently untested!!!!! */
    public void updateStanding() {
        ArrayList<Stable> temp = new ArrayList<Stable>(standing.keySet());
        Collections.sort(temp);
        int rank, posFix;
        rank = posFix = 0;

        for (Stable stable : temp) {
            if ((stable.getHorse().gotPlaced()))
                rank++;
        }

        int lastPosition = -1;
        for (Stable stable : temp) {
            if (!stable.getHorse().gotPlaced()) {
                if ((stable.getHorse()).getCurrentPosition() != lastPosition)
                    rank += posFix + 1;
                else
                    posFix += 1;
                standing.put(stable, rank);
                if (stable.getHorse().hasFinishedRace())
                    stable.getHorse().setGotPlaced(true);
                lastPosition = stable.getHorse().getCurrentPosition();
            }
        }
    }

    public void fixStandingBasedOnQuotation() {
        ArrayList<Stable> temp = new ArrayList<Stable>(standing.keySet());
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {
                if (i != j && (standing.get(temp.get(i)) == standing.get(temp.get(j))) && temp.get(i).getHorse().gotPlaced() && temp.get(j).getHorse().gotPlaced()) {
                    if (temp.get(i).getQuotation() > temp.get(j).getQuotation())
                        standing.put(temp.get(i), standing.get(temp.get(i)) + 1);
                    else if (temp.get(i).getQuotation() < temp.get(j).getQuotation())
                        standing.put(temp.get(j), standing.get(temp.get(j)) + 1);
                    else
                        ;//chiamo interfaccia deve decidere il primo giocatore!!!!!!
                }
            }
        }
    }


    private boolean isHorseFirst(Horse horse) {
        if (standing.get(horse.getOwnerStable()) == 1)
            return true;

        return false;
    }

    private boolean isHorseLast(Horse horse) {
        for (Stable temp : standing.keySet()) {
            if (standing.get(temp) == 6) {
                if (temp == horse.getOwnerStable())
                    return true;
                else
                    return false;
            }
        }

        if (standing.get(horse.getOwnerStable()) == 5)
            return true;

        return false;
    }

    public void updateStableQuotations() {
        for (Stable temp : standing.keySet()) {
            if (temp.getQuotation() - 1 > standing.get(temp))
                temp.setQuotation(temp.getQuotation() - 1);
            else if (temp.getQuotation() - 1 < standing.get(temp))
                temp.setQuotation(temp.getQuotation() + 1);
        }
    }

    public void resetRace() {

        for (Horse horse : horsesList) {
            horse.resetVars();
        }


    }


}

