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
    private final int finishLine = 12; //numero caselle per il traguardo
    private ArrayList<Horse> horsesList;
    private Deck movementCardDeck;
    private Map<Stable, Integer> standing;

    public RaceManager(Stable[] stables) {
        for (Stable stable : stables) {
            standing.put(stable, 0);
        }

        int size = stables.length;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random generator = new Random();

        while (numbers.size() < size) {
            int random = generator.nextInt(size) + 2; //quotazione parte da 2
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        for (int i = 0; i < size && !numbers.isEmpty(); i++) {
            stables[i].setQuotation(numbers.remove(0));
        }
    }

    void throwSprintDice() {
        ;
    }

    void startRace() {
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
        /* usa le quotazioni salvate dentro la blackboard per determinare la classifica finale */
    }

}

