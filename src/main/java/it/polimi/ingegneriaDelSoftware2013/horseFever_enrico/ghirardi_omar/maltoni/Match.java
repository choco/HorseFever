package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class Match {

    private ArrayList<Player> players;
    private Player currentPLayer; //giocatore in azione
    private int currentTurn;
    private int mainPlayer;  // indice primo giocatore nell'array list
    private Deck movementCardDeck;
    private Deck actionCardDeck;

    private BetManager betManager;

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
        betManager = new BetManager();
        //inizializzi lane
        //inizializzi race manager;

    }

    public void startMatch() {
        giveCharacterCards();
        setFirstPlayer();
        /* se è family game */
        giveActionCards(2);

        betPhase(true);
        rigPhase();
        betPhase(false);

        racePhase();

    }

    private void racePhase() {

    }

    private void rigPhase() {
        //sempre questione del giro orario che parte dal first player da risolvere
        while (someoneStillHasActionCards()) {
            for (Player player : players) {
                if (!player.isActionCardPileEmpty()) {
                    //chiamiamo interfaccia per determinare carta e lane su cui giocare la carta stessa
                    player.playActionCard(card, lane);
                }
            }
        }
    }

    private boolean someoneStillHasActionCards() {
        for (Player player : players) {
            if (!player.isActionCardPileEmpty())
                return true;
        }
        return false;
    }

    private boolean someoneStillHasBetsToMake() {
        //da implementare
        return false;
    }

    private void betPhase(boolean mandatory) {
        /*per family game e quasi anche per il boardgame */

        //prima fase

        // bisogna implementare il problema di compiere questo for in senso antiorario o orario in base alla variabile mandatory (true = orario, false = antiorario)
        for (Player player : players) {   //senso orario
            if (!mandatory) {
                // possibilita di uscire dalla funzione e non farla
                /*
                if(false) { //se ha scelto di non farla
                    continue;
                }                  */
            }
            //chiama interfaccia e chiede al giocatore i valori per la makeBet


            boolean betCorrectlyMade = false;
            while (!betCorrectlyMade) {
                Bet playerBet;

                try {
                    playerBet = player.makeBet(amount, type, lane);
                } catch () {

                }

                if (betManager.insertBet(playerBet)) {
                    betCorrectlyMade = true;
                }

            }
        }


    }

    private void giveActionCards(int numCards) {
        /* assegnamento temporaneo in attesa di struttura migliore per gestire i players */
        for (Player player : players) {
            for (int i = 0; i < numCards; i++)
                player.addActionCard((ActionCard) actionCardDeck.draw());
        }
    }

    private void setFirstPlayer() {
        Random generator = new Random();
        players.get(generator.nextInt(players.size() - 1)).setFirstPlayer(true);
    }

    private void giveCharacterCards() {
        for (Player player : players) {
            player.setCharCard((CharacterCard) characterCardDeck.draw());
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private int getNumberOfPlayers() { //lancia interfaccia grafica e compila la lista giocatori.. eccezione da gestire se vengono aggiunti più di 6 e meno di 2 giocatori
        return players.size();
    }

    private int getNumberOfTurns() {
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

    private int numberOfMarksPerColor() {
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

    static void main() {
        Match partita = new Match();
        partita.addPlayer(player);
        partita.startMatch();
    }
}

