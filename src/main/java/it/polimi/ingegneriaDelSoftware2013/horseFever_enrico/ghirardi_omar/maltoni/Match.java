package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class Match {

    private ArrayList<Player> players;
    private ArrayList<Stable> stables;
    private int currentPLayer; //giocatore in azione
    private int currentTurn;
    private int firstPlayer;  // indice primo giocatore nell'array list
    private Deck movementCardDeck;
    private Deck actionCardDeck;

    // mazzi board game

    private Deck characterCardDeck;
    private Deck stableCardDeck;
    private Deck horseCardDeck;
    private Deck employerCardDeck; //mazzo carte aiutanti
    private Deck goalCardDeck;
    //come implementare le carte debito?
    //...

    public Match() {
        // riceve dati da interfaccia o lancia lui l'interfaccia?  inizializza il gioco o è l'istanza dei turni? ... lo lancia HorseFever
        //inizializza le variabili dei mazzi con la chiamata del costruttore

        //inizializzi i mazzi
        // inizializzi la blackboard
        //inizializzi bet manager
        //inizializzi stable


    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Deck getActionCardDeck() {
        return actionCardDeck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfTurns() {
        switch (players.size()) {
            case 2:
            case 3:
            case 6:
                return 6;
            case 4:
                return 4;
            case 5:
                return 5;
            default:
                return -1;
        }

    }

    public int numberOfMarksPerColor() {
        switch (players.size()) {
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
}

