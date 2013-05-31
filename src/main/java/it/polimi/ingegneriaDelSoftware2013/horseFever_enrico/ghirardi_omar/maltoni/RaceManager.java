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
public class RaceManager {
    private final int finishLine = 12; //no. of steps to finish line
    private ArrayList<Horse> horsesList;
    private Deck movementCardDeck;
    private Map<Stable, Integer> standing;
    private ArrayList<ActionCard> playedActionCards;

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

    public StableColor throwSprintDice() {

        int roll = -1;
        Random r = new Random();
        roll = r.nextInt(horsesList.size() - 1);

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
        ;
    }

    void raceTurn() {        // handles every race turn
        ;
    }


    void checkActionCardsAtStart() {
        ;
    }

    /* currently untested!!!!! */
    public void updateStanding() {
        ArrayList<Stable> temp = new ArrayList<Stable>(standing.keySet());
        Collections.sort(temp);
        int rank = 0;
        for (Stable stable : temp) {
            if ((stable.getHorse().hasFinishedRace()))
                rank++;
        }

        int lastPosition = -1;
        for (Stable stable : temp) {
            if (!stable.getHorse().hasFinishedRace()) {
                if ((stable.getHorse()).getCurrentPosition() != lastPosition)
                    rank += 1;
                standing.put(stable, rank);
                lastPosition = stable.getHorse().getCurrentPosition();
            }
        }
    }

    public void fixUpStandingsBasedOnQuotations() {
        /* usa le quotazioni per determinare la classifica finale */
    }

    public void resetRace() {

        for (Horse horse : horsesList) {
            horse.resetVars();
        }


    }


}

