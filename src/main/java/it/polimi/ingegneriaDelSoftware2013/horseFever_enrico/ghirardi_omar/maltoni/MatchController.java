package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 27/05/13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class MatchController {

    private Match match;
    private BetManager betManager;
    private RaceManager raceManager;
    public GameLobbyInterface lobbyInterface;
    public GameInterface gameInterface;
    public BetPhaseInterface betPhaseInterface;

    private static final int NUMBER_OF_ACTIONCARDS_AT_EACH_TURN = 2;


    public MatchController() {
        match = new Match();
        betManager = new BetManager();
        raceManager = new RaceManager(match.getStables(), match.getMovementCardDeck());
        lobbyInterface = new GameLobbyView();

        //mostra interfaccia
    }

    public void startMatch() {
        giveCharacterCards();
        initializeFirstPlayer();
        startTurn();
    }

    public void startTurn() {
        giveActionCards(NUMBER_OF_ACTIONCARDS_AT_EACH_TURN);


    }

    /*
    *
    * Riceve un ArrayList di stringhe che corrispondono ai "nickname" scelti per ogni giocatore
    * e crea nel modello i giocatori corrispondenti, successivamente avvia la partita
    *
    * @param players    ArrayList<String> nickname dei giocatori
    *
     */

    public void setPlayers(ArrayList<String> players) {
        for (String temp : players) {
            match.addPlayer(new Player(temp));
        }

        startMatch();
    }
      /*
    private void rigPhase() {
        //sempre questione del giro orario che parte dal first player da risolvere
        while (someoneStillHasActionCards()) {
            for (Player player : match.getPlayers()) {
                if (!player.isActionCardPileEmpty()) {
                    //chiamiamo interfaccia per determinare carta e lane su cui giocare la carta stessa
                    player.playActionCard(card, lane);
                }
            }
        }
    }  */

    private boolean someoneStillHasActionCards() {
        for (Player player : match.getPlayers()) {
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

        //variables which will be updated via user interface
        int amount = 0;
        Stable stable = null;
        BetType type = null;

        ArrayList<Player> players = match.getPlayers();
        Player firstPlayer = match.getFirstPlayer();
        int j = players.indexOf(firstPlayer);

        /* if the bet is mandatory it means the game is currently at the first bet which every player have to do in clockwise order starting from the 1st player
           if the bet isn't mandatory the players, starting from the 1st player, are not obliged to do a bet (anticlockwise order)
         */
        if (mandatory) {
            for (int i = 0; i < players.size(); i++) {
                if (j >= players.size()) j = 0;

                boolean betCorrectlyMade = false;
                while (!betCorrectlyMade) {

                    //chiama interfaccia e chiede al giocatore i valori per la makeBet


                    Bet playerBet = null;

                    try {
                        playerBet = players.get(j).makeBet(amount, type, stable);
                    } catch (InvalidBetException e) {
                        switch (e.getType()) {

                            case NOTENOUGHTMONEY:
                                break;
                            case NOTAMINIMUMBET:
                                break;
                            case NOMOREREMAINIGBETS:
                                break;
                        }

                    }

                    if (betManager.insertBet(playerBet)) {
                        betCorrectlyMade = true;
                    }

                }

                ++j;
            }

        } else {
            for (int i = 0; i < players.size(); i++) {
                if (j < 0) j = players.size();


                //chiama interfaccia e chiede al giocatore i valori per la makeBet
                //in questo caso chiede anche se vuole farla o meno

                boolean betCorrectlyMade = false;
                while (!betCorrectlyMade) {
                    Bet playerBet = null;

                    try {
                        playerBet = players.get(j).makeBet(amount, type, stable);
                    } catch (InvalidBetException e) {

                    }

                    if (betManager.insertBet(playerBet)) {
                        betCorrectlyMade = true;
                    }

                }
                --j;
            }


        }
    }

    private void giveActionCards(int numCards) {

        ArrayList<Player> players = match.getPlayers();

        int j = players.indexOf(match.getFirstPlayer()) + 1;
        for (int i = 0; i < players.size(); i++) {
            if (j >= players.size()) j = 0;

            for (int k = 0; k < numCards; k++) {
                players.get(j).addActionCard((ActionCard) match.getActionCardDeck().draw());

            }
            ++j;
        }
    }

    private void initializeFirstPlayer() {
        Random generator = new Random();
        match.getPlayers().get(generator.nextInt(match.getPlayers().size() - 1)).setFirstPlayer(true);
    }

    private void giveCharacterCards() {
        for (Player player : match.getPlayers()) {
            player.setCharCard((CharacterCard) match.getCharacterCardDeck().draw());
        }

        initializePlayersInfo();
    }


    /*
    *
    *  Give players their starting money based on the character card they got
    *  Player and respective stable get linked
    *
     */

    private void initializePlayersInfo() {
        for (Player player : match.getPlayers()) {
            player.setMoney(player.getCharCard().getStartingMoney());
            for (Stable stable : match.getStables()) {
                if (stable.getQuotation() == player.getCharCard().getBaseStableQuotation()) {
                    stable.setStableOwner(player);
                    player.addStable(stable);
                    break;
                }
            }

        }
    }

}