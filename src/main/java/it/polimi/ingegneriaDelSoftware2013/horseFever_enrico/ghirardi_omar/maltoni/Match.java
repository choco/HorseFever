package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Dictionary;

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

    /* mazzi board game

    private Deck characterCardDeck;
    private Deck stableCardDeck;
    private Deck horseCardDeck;
    private Deck employerCardDeck; //mazzo carte aiutanti
    private Deck goalCardDeck;
    //come implementare le carte debito?

    */
    //...

    public Match (){
        // riceve dati da interfaccia o lancia lui l'interfaccia?  inizializza il gioco o è l'istanza dei turni? ... lo lancia HorseFever
        //inizializza le variabili dei mazzi con la chiamata del costruttore
    }

   /* private int getNumberOfPlayers (){ //lancia interfaccia grafica e compila la lista giocatori.. eccezione da gestire se vengono aggiunti più di 6 e meno di 2 giocatori

    } */

    private int getNumberOfTurns (int numberOfPlayers){
          if (numberOfPlayers==2) return 6;
          if (numberOfPlayers==3) return 6;
          if (numberOfPlayers==4) return 4;
          if (numberOfPlayers==5) return 5;
          if (numberOfPlayers==6) return 6;
          return -1;
          }



}

