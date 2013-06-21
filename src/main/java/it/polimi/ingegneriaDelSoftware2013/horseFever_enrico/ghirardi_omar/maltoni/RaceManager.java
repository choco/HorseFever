package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.interfaces.GameInterface;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.interfaces.RaceInterface;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */

public class RaceManager {

    private RaceInterface raceInterface;
    private GameInterface gameInterface;
    private ArrayList<Horse> horsesList;
    private Deck movementCardDeck;
    private Map<Stable, Integer> standing;
    private ArrayList<ActionCard> playedActionCards;
    private RacePhase racePhase;

    private static final int FINISH_LINE = 12; //no. of steps to finish line
    private static final int STANDARD_SPRINT_STEPS = 1;
    private static final int NUMBER_OF_DICE = 2;


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

    /**
     * Constructor of a race manager object
     *
     * @param stables          stable taking part in the game
     * @param movementCardDeck the deck of movement cards
     */
    public RaceManager(ArrayList<Stable> stables, Deck movementCardDeck) {

        horsesList = new ArrayList<Horse>();
        standing = new HashMap<Stable, Integer>();

        for (Stable stable : stables) {
            standing.put(stable, 0);
            horsesList.add(stable.getHorse());
        }

        this.movementCardDeck = movementCardDeck;
        playedActionCards = new ArrayList<ActionCard>();
    }

    public void setGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }

    public void setRaceInterface(RaceInterface raceInterface) {
        this.raceInterface = raceInterface;
    }

    /**
     * Updates the quotation of every stable at the end of a race
     */

    public void updateStableQuotations() {
        for (Stable temp : standing.keySet()) {
            if (temp.getQuotation() - 1 > standing.get(temp))
                temp.setQuotation(temp.getQuotation() - 1);
            else if (temp.getQuotation() - 1 < standing.get(temp))
                temp.setQuotation(temp.getQuotation() + 1);
        }
    }

    public Map<Stable, Integer> getStanding() {
        return standing;
    }

    public void setStanding(Map<Stable, Integer> st) {
        this.standing = st;

    }

    /**
     * Standing is reset, played actioncards are put back
     * in the respective deck.
     *
     * @param actionCardsDeck A reference to the match action cards deck
     */

    public void resetRace(Deck actionCardsDeck) {

        for (Stable stable : standing.keySet())
            standing.put(stable, 0);

        for (ActionCard card : playedActionCards) {
            actionCardsDeck.putBottom(card);
        }
        playedActionCards.clear();

        for (Horse horse : horsesList) {
            horse.resetVars();
        }

        raceInterface.updateHorsesPositions(horsesList);

    }

    /**
     * Starts the race flow
     */


    public void startRace() {      // handles the start

        racePhase = RacePhase.START;

        gameInterface.updateRacePhase(racePhase);

        for (Horse horse : horsesList) {
            System.out.println("Carte assegnate: " + horse.getActionPile());
        }
        checkActionCardsAtStart();

        gameInterface.updateStableQuotations(new ArrayList<Stable>(standing.keySet()));

        while (!allHorsesGotPlaced()) {
            raceTurn();
            for (Horse horse : horsesList) {
                System.out.println("Il cavallo color " + horse.getOwnerStable().getColor() + " ha fatto " + horse.getCurrentPosition() + "passi! Ed è arrivato: " + horse.hasFinishedRace() + " ed è stato piazzato: " + horse.gotPlaced());
            }
            System.out.println("\n");

            for (Stable stable : standing.keySet()) {
                System.out.println("Il cavallo color " + stable.getColor() + " è in posizione" + standing.get(stable));
            }

        }

        racePhase = RacePhase.FINISH;

        fixStandingBasedOnQuotation();
        for (Stable stable : standing.keySet()) {
            System.out.println("Il cavallo color " + stable.getColor() + " è in posizione" + standing.get(stable));
        }

        gameInterface.updateRaceStandings(standing);

    }

    /**
     * Manages every turn of the race updating the position and steps of every horse
     */

    private void raceTurn() {        // handles every race turn

        MovementCard movementCard = (MovementCard) movementCardDeck.draw();
        movementCardDeck.putBottom(movementCard);

        gameInterface.userShouldStartRaceTurn();

        gameInterface.updateCurrentMovementCard(movementCard);

        for (Horse horse : horsesList) {
            System.out.println("Carte assegnate: " + horse.getActionPile());
            applyMovementCardToHorse(movementCard, horse);
        }

        racePhase = RacePhase.MIDDLE;


        for (Horse horse : horsesList) {
            checkIfHorseArrived(horse);
        }

        System.out.println(horsesList);
        raceInterface.updateHorsesPositions(horsesList);

        racePhase = RacePhase.SPRINT;

        gameInterface.updateRacePhase(racePhase);
        gameInterface.userShouldThrowSprintDices();

        ArrayList<StableColor> sprintingColors = new ArrayList<StableColor>();
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            StableColor temp = throwSprintDice();
            if (!sprintingColors.contains(temp))
                sprintingColors.add(temp);
        }

        gameInterface.showSprintingHorses(sprintingColors);

        for (StableColor color : sprintingColors) {
            for (Stable stable : standing.keySet()) {
                if (stable.getColor() == color) {
                    System.out.println("Sprinterà il cavallo: " + color);
                    makeHorseSprint(stable.getHorse());
                }
            }
        }

        raceInterface.updateHorsesPositions(horsesList);

        for (Horse horse : horsesList) {
            checkIfHorseArrived(horse);
        }

        raceInterface.updateHorsesPositions(horsesList);

        updateStanding();

        racePhase = RacePhase.MIDDLE;
        gameInterface.updateRacePhase(racePhase);

    }

    /**
     * Removes the selected type of cards from the action cards pile of the horse
     *
     * @param actionType type of action cards to remove
     * @param horse      horse to act
     */

    private void removeActionCardsOfTypeFromHorse(ActionType actionType, Horse horse) {
        for (ActionCard card : horse.getActionPile()) {
            if (card.getType() == actionType) {
                playedActionCards.add(card);
            }
        }

        for (ActionCard card : playedActionCards) {
            if (horse.getActionPile().contains(card)) {
                System.out.print("Sto per rimuovere: " + card);
                horse.getActionPile().remove(card);
            }
        }
    }

    /**
     * Checks if all horses has finished the race
     *
     * @return true or false based on the condition above
     */

    private boolean allHorsesFinishedRace() {
        for (Horse horse : horsesList) {
            if (!horse.hasFinishedRace())
                return false;
        }

        return true;
    }

    /**
     * Checks if all the racing horses have been added to the final standing hence they're placed
     *
     * @return return true or false based on the condition above
     */

    private boolean allHorsesGotPlaced() {
        for (Horse horse : horsesList) {
            if (!horse.gotPlaced())
                return false;
        }

        return true;
    }

    /**
     * Checks if a particular horse has passes the finish line
     *
     * @param horse horse to check
     */

    private void checkIfHorseArrived(Horse horse) {
        if (!horse.gotPlaced()) {
            if (horse.getCurrentPosition() >= FINISH_LINE) {
                if (horse.didAddFinishStepsChange()) {
                    horse.setCurrentPosition(horse.getCurrentPosition() + horse.getAddFinishSteps());
                } else if (horse.didCanMoveAfterFinishLineChange()) {
                    horse.setCurrentPosition(FINISH_LINE);
                }

                horse.setFinishedRace(true);
            }
        }
    }

    /**
     * Makes the horse sprint
     *
     * @param horse horse which is going to sprint
     */

    private void makeHorseSprint(Horse horse) {
        if (!horse.gotPlaced()) {
            int current = horse.getCurrentPosition();
            if (horse.didFixedSprintStepsChange()) {
                if (horse.didAddSprintStepsChange()) {
                    System.out.print("Sprinti qui 1: eri a " + current + " sprinti in modo fissato di " + horse.getFixedSprintSteps() + "ci aggiungi " + horse.getAddSprintSteps());
                    horse.setCurrentPosition(current + horse.getFixedSprintSteps() + horse.getAddSprintSteps());
                } else {
                    System.out.print("Sprinti qui 1: eri a " + current + " sprinti in modo fissato di " + horse.getFixedSprintSteps());
                    horse.setCurrentPosition(current + horse.getFixedSprintSteps());
                }
            } else {
                if (horse.didAddSprintStepsChange()) {
                    System.out.print("Sprinti qui 1: eri a " + current + " ci aggiungi " + horse.getAddSprintSteps());

                    horse.setCurrentPosition(current + horse.getAddSprintSteps() + STANDARD_SPRINT_STEPS);
                } else {
                    horse.setCurrentPosition(current + STANDARD_SPRINT_STEPS);
                }
            }
        }
    }

    /**
     * Applies movement card to horse hence the horse moves of a number of steps equals to the number associated to his..
     * Stable quotation on the movement card
     *
     * @param card  movement card to apply
     * @param horse horse to move
     */

    private void applyMovementCardToHorse(MovementCard card, Horse horse) {
        if (!horse.gotPlaced()) {
            int baseMovement = card.getMovementForQuotation(horse.getOwnerStable().getQuotation());

            switch (racePhase) {

                case START:
                    if (horse.didFixedStartStepsChange()) {
                        if (horse.didAddStartStepsChange()) {
                            System.out.println("Qui 1");
                            System.out.println("Il cavallo parte con " + horse.getFixedStartSteps() + " e ci aggiunge " + horse.getAddStartSteps());

                            horse.setCurrentPosition(horse.getFixedStartSteps() + horse.getAddStartSteps());
                        } else {
                            System.out.println("Qui 2");

                            horse.setCurrentPosition(horse.getFixedStartSteps());
                        }
                    } else {
                        if (horse.didAddStartStepsChange()) {
                            System.out.println("Qui 3");

                            horse.setCurrentPosition(baseMovement + horse.getAddStartSteps());
                        } else {
                            System.out.println("Qui 4");

                            horse.setCurrentPosition(baseMovement);
                        }
                    }
                    break;
                case MIDDLE:
                    if (horse.didIsFirstFixedStepsChange()) {
                        if (isHorseFirst(horse)) {
                            System.out.println("Qui 5");

                            horse.setCurrentPosition(horse.getCurrentPosition() + horse.getFirstFixedSteps());
                        } else {
                            horse.setCurrentPosition(horse.getCurrentPosition() + baseMovement);
                        }
                    } else if (horse.didIsLastFixedStepsChange()) {
                        if (isHorseLast(horse)) {
                            System.out.println("Qui 6");

                            horse.setCurrentPosition(horse.getCurrentPosition() + horse.getLastFixedSteps());
                        } else {
                            horse.setCurrentPosition(horse.getCurrentPosition() + baseMovement);
                        }
                    } else {
                        System.out.println("Qui 7");

                        horse.setCurrentPosition(horse.getCurrentPosition() + baseMovement);
                    }
                    break;

            }
        }

    }

    /**
     * Applies neutral cards to the horse...a little tricky
     */

    private void applyNeutralCards() {
        for (Horse horse : horsesList) {
            for (ActionCard card : horse.getActionPile()) {
                if (card.getType() == ActionType.NEUTRAL) {
                    if (card.getAction().equals(REMOVE_NEGATIVE_ACTIONCARDS)) {
                        System.out.println("Rimuoverò le carte negative");
                        removeActionCardsOfTypeFromHorse(ActionType.NEGATIVE, horse);
                        break;
                    }
                }
            }

            for (ActionCard card : horse.getActionPile()) {
                if (card.getType() == ActionType.NEUTRAL) {
                    if (card.getAction().equals(REMOVE_POSITIVE_ACTIONCARDS)) {
                        System.out.println("Rimuoverò le carte positive");

                        removeActionCardsOfTypeFromHorse(ActionType.POSITIVE, horse);
                        break;
                    }
                }
            }
            for (ActionCard card : horse.getActionPile()) {
                if (card.getType() == ActionType.NEUTRAL) {
                    if (card.getAction().equals(ADD_QUOTATION)) {
                        horse.getOwnerStable().setQuotation(horse.getOwnerStable().getQuotation() + card.getActionValue());
                    }
                }
            }
        }
    }

    /**
     * Applies the action cards of the action cards pile which affects the movement of a horse
     *
     * @param horse horse to apply cards to
     */

    private void applyMovementRelatedActionCardsToHorse(Horse horse) {

        for (ActionCard card : horse.getActionPile()) {

            if (card.getAction().equals(FIXED_START_STEPS)) {
                horse.setFixedStartSteps(card.getActionValue());
            } else if (card.getAction().equals(ADD_START_STEPS)) {
                if (horse.didAddStartStepsChange()) {
                    horse.setAddStartSteps(horse.getAddStartSteps() + card.getActionValue());
                } else {
                    horse.setAddStartSteps(card.getActionValue());
                }
            } else if (card.getAction().equals(FIXED_SPRINT_STEPS)) {
                horse.setFixedSprintSteps(card.getActionValue());
            } else if (card.getAction().equals(ADD_SPRINT_STEPS)) {
                if (horse.didAddSprintStepsChange()) {
                    horse.setAddSprintSteps(horse.getAddSprintSteps() + card.getActionValue());
                } else {
                    horse.setAddSprintSteps(card.getActionValue());
                }
            } else if (card.getAction().equals(WINS_PHOTOFINISH)) {
                horse.setWinsPhotofinish(card.getActionValue());
            } else if (card.getAction().equals(ADD_FINISH_STEPS)) {
                if (horse.didAddFinishStepsChange()) {
                    horse.setAddFinishSteps(horse.getAddFinishSteps() + card.getActionValue());
                } else {
                    horse.setAddFinishSteps(card.getActionValue());
                }
            } else if (card.getAction().equals(IS_LAST_FIXED_STEPS)) {
                horse.setLastFixedSteps(card.getActionValue());
            } else if (card.getAction().equals(IS_FIRST_FIXED_STEPS)) {
                horse.setFirstFixedSteps(card.getActionValue());
            } else if (card.getAction().equals(CAN_MOVE_AFTER_FINISH_LINE)) {
                horse.setCanMoveAfterFinishLine(card.getActionValue());
            }
        }

    }

    /**
     * Removes the cards with the same letter from teh action cards pile of a horse
     */

    private void removeSameCharCards() {

        for (Horse horse : horsesList) {
            for (int i = 0; i < horse.getActionPile().size(); i++) {
                for (int j = 0; j < horse.getActionPile().size(); j++) {
                    if ((j != i) && (horse.getActionPile().get(j).getCardLetter() == horse.getActionPile().get(i).getCardLetter()) && horse.getActionPile().get(j).getCardLetter() != 'N') {

                        playedActionCards.add(horse.getActionPile().get(j));
                        playedActionCards.add(horse.getActionPile().get(i));

                    }
                }
            }
            for (ActionCard card : playedActionCards) {
                if (horse.getActionPile().contains(card)) {
                    horse.getActionPile().remove(card);
                }
            }
        }
    }

    /**
     * Throws sprint dice
     *
     * @return the color of the stable which owns the horse which is going to sprint
     */

    private StableColor throwSprintDice() {

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

    /**
     * Checks the action cards pile of a horse before the race begins
     * Applies and remove cards by calling several methods written above
     */

    private void checkActionCardsAtStart() {

        applyNeutralCards();

        for (Horse horse : horsesList) {
            removeActionCardsOfTypeFromHorse(ActionType.NEUTRAL, horse);
        }

        removeSameCharCards();

        for (Horse horse : horsesList) {
            applyMovementRelatedActionCardsToHorse(horse);
        }


    }

    /**
     * Updates the standing
     */

    //TODO: controllare se ha senso metterlo public...altrimenti tolgo il test (comunque positivo!)
    public void updateStanding() {
        ArrayList<Stable> temp = new ArrayList<Stable>(standing.keySet());
        Collections.sort(temp);
        for (Stable stable : temp) {
            System.out.println("Stable di colore " + stable.getColor() + " con cavallo in posizione " + stable.getHorse().getCurrentPosition());
        }
        int rank, posFix;
        rank = 0;
        posFix = 1;

        for (Stable stable : temp) {
            if ((stable.getHorse().gotPlaced()))
                rank++;
        }
        System.out.println("Si parte dal rank " + rank);

        int lastPosition = -1;
        for (Stable stable : temp) {
            if (!(stable.getHorse().gotPlaced())) {
                if ((stable.getHorse()).getCurrentPosition() != lastPosition) {
                    System.out.println("Il cavallo color " + stable.getColor() + " ha posizione diversa dal precedente");
                    rank += posFix;
                    posFix = 1;
                } else {
                    System.out.println("Il cavallo color " + stable.getColor() + " NON ha posizione diversa dal precedente");

                    posFix += 1;
                }

                standing.put(stable, rank);

                if (stable.getHorse().hasFinishedRace()) {
                    stable.getHorse().setGotPlaced(true);
                }
                lastPosition = stable.getHorse().getCurrentPosition();
            }
        }
    }

    /**
     * Adds horses to standing in case of draw depending on the quotation of each horse
     */

    private void fixStandingBasedOnQuotation() {
        ArrayList<Stable> temp = new ArrayList<Stable>(standing.keySet());
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {
                if (i != j && (standing.get(temp.get(i)) == standing.get(temp.get(j))) && temp.get(i).getHorse().gotPlaced() && temp.get(j).getHorse().gotPlaced()) {
                    if (temp.get(i).getHorse().didWinsPhotofinishChange()) {
                        if (temp.get(i).getHorse().getWinsPhotofinish() == 1)
                            standing.put(temp.get(j), standing.get(temp.get(j)) + 1);
                        else
                            standing.put(temp.get(i), standing.get(temp.get(i)) + 1);
                    } else if (temp.get(j).getHorse().didWinsPhotofinishChange()) {
                        if (temp.get(j).getHorse().getWinsPhotofinish() == 1)
                            standing.put(temp.get(i), standing.get(temp.get(i)) + 1);
                        else
                            standing.put(temp.get(j), standing.get(temp.get(j)) + 1);
                    } else if (temp.get(i).getQuotation() > temp.get(j).getQuotation())
                        standing.put(temp.get(i), standing.get(temp.get(i)) + 1);
                    else if (temp.get(i).getQuotation() < temp.get(j).getQuotation())
                        standing.put(temp.get(j), standing.get(temp.get(j)) + 1);
                    else
                        ;//chiamo interfaccia deve decidere il primo giocatore!!!!!!
                }
            }
        }
    }

    /**
     * Checks in the horse is first
     *
     * @param horse to check
     * @return true if it's first, false otw
     */

    private boolean isHorseFirst(Horse horse) {
        if (standing.get(horse.getOwnerStable()) == 1)
            return true;

        return false;
    }

    /**
     * Checks in the horse is last
     *
     * @param horse to check
     * @return true if it's last, false otw
     */

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


}

