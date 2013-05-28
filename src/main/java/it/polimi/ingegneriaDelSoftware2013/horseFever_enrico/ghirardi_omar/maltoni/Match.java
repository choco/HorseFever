package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
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

    private BetManager betManager;
    private RaceManager raceManager;

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

    private ArrayList loadCards(String cardsFile, CardType type) throws IOException {
        ArrayList cards;

        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createParser(new FileInputStream(cardsFile));
        ObjectMapper mapper = new ObjectMapper();
        // advance stream to START_ARRAY first:
        jp.nextToken();

        switch (type) {
            case ACTION: {
                cards = new ArrayList<ActionCard>();
                // and then each time, advance to opening START_OBJECT
                while (jp.nextToken() == JsonToken.START_OBJECT) {
                    ActionCard card = mapper.readValue(jp, ActionCard.class);
                    cards.add(card);
                }
                return cards;
            }
            case MOVEMENT:
                break;
            case CHARACTER:
                break;
            case STABLE:
                break;
            case HORSE:
                break;
            case EMPLOYER:
                break;
            case GOAL:
                break;
            default:
                break;
        }
        return null;
    }
}

