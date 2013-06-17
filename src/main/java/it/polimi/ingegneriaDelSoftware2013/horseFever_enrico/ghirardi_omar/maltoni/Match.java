package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */

enum MatchPhase {
    START_GAME,
    FIRST_BET_PHASE,
    SECOND_BET_PHASE,
    RIG_PHASE,
    RACE_PHASE,
    END_GAME
}

public class Match {

    private MatchPhase matchPhase;
    private ArrayList<Player> players;
    private ArrayList<Stable> stables;
    private int currentPLayer; //giocatore in azione
    private int currentTurn;
    private int numberOfTurns; //???

    private Map<StableColor, Integer> betMarkPool;
    private Deck movementCardDeck;
    private Deck actionCardDeck;
    // mazzi board game

    private Deck characterCardDeck;
    private Deck stableCardDeck;
    //private Deck horseCardDeck;
    //private Deck employerCardDeck; //mazzo carte aiutanti
    //private Deck goalCardDeck;
    //come implementare le carte debito?
    //...

    public Match() {

        matchPhase = MatchPhase.START_GAME;

        //inizializzi i mazzi
        //inizializzi stable

        try {
            movementCardDeck = new Deck(CardType.MOVEMENT);
            actionCardDeck = new Deck(CardType.ACTION);
            stableCardDeck = new Deck(CardType.STABLE);
            characterCardDeck = new Deck(CardType.CHARACTER);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        players = new ArrayList<Player>();
        stables = new ArrayList<Stable>();
        betMarkPool = new HashMap<StableColor, Integer>();


        while (!stableCardDeck.isDeckEmpty()) {
            StableCard temp = (StableCard) stableCardDeck.draw();
            Stable stable = new Stable(temp.getColor());
            stable.setStableCard(temp);
            stables.add(stable);
        }

        int size = stables.size();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random generator = new Random();

        while (numbers.size() < size) {
            int random = generator.nextInt(size) + 2; //quotation starts from 2
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        for (int i = 0; i < size && !numbers.isEmpty(); i++) {
            stables.get(i).setQuotation(numbers.remove(0));
        }

        currentPLayer = 0;
        currentTurn = 0;

    }

    public int getCurrentPLayer() {
        return currentPLayer;
    }

    public void setCurrentPLayer(int currentPLayer) {
        if (currentPLayer > (players.size() - 1))
            this.currentPLayer = 0;
        else if (currentPLayer < 0)
            this.currentPLayer = players.size() - 1;
        else
            this.currentPLayer = currentPLayer;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Map<StableColor, Integer> getBetMarkPool() {
        return betMarkPool;
    }

    public void setUpMatch() {
        numberOfTurns = getNumberOfTurnsAtStart();
        currentTurn = 0;
        setBetMarkPool();
    }

    public void setMatchPhase(MatchPhase phase) {
        matchPhase = phase;
    }

    public MatchPhase getMatchPhase() {
        return matchPhase;
    }

    public Deck getMovementCardDeck() {
        return movementCardDeck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Stable> getStables() {
        return stables;
    }

    public Deck getActionCardDeck() {
        return actionCardDeck;
    }

    public Deck getCharacterCardDeck() {
        return characterCardDeck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    private int getNumberOfTurnsAtStart() {
        switch (getNumberOfPlayers()) {
            case 2:
            case 3:
            case 6:
                return 2;
            case 4:
                return 4;
            case 5:
                return 5;
            default:
                return -1;
        }

    }

    public int numberOfMarksPerColor() {
        switch (getNumberOfPlayers()) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
            case 6:
                return 4;
            default:
                return -1;
        }
    }

    public Player getFirstPlayer() {
        Player player = null;
        for (Player temp : players) {
            if (temp.isFirstPlayer()) {
                player = temp;
                break;
            }

        }
        return player;

    }

    private void setBetMarkPool() {
        for (Stable temp : stables) {
            betMarkPool.put(temp.getColor(), numberOfMarksPerColor());
        }
    }
}

