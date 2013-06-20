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
    //TODO: tests on this class
    private Match match;
    private BetManager betManager;
    private RaceManager raceManager;
    private GameInterface gameInterface;
    private BetPhaseInterface betPhaseInterface;

    private static final int NUMBER_OF_ACTIONCARDS_AT_EACH_TURN = 2;
    private static final int NUMBER_OF_VP_LOSE = 2;

    /**
     * Constructor of a match controller object
     */
    public MatchController() {
        match = new Match();
        betManager = new BetManager(match.getBetMarkPool());
        raceManager = new RaceManager(match.getStables(), match.getMovementCardDeck());
        gameInterface = new GameInterfaceView(this);
        raceManager.setGameInterface(gameInterface);
        //mostra interfaccia
    }

    /**
     * Starts the match with the information needed
     */

    public void startMatch() {
        match.setUpMatch();
        giveCharacterCards();
        initializeFirstPlayer();

        gameInterface.updatePlayersInfo(match.getPlayers());
        gameInterface.updateStableQuotations(match.getStables());

        for (int currentTurn = 0; currentTurn < match.getNumberOfTurns(); currentTurn++) {
            updatePlayersOrder();
            startTurn();
            wrapUpTurn();
            setNextFirstPlayer();
            match.setCurrentTurn(currentTurn + 1);
            match.getActionCardDeck().shuffle();
            match.getMovementCardDeck().shuffle();
        }

        endMatch();
    }

    /**
     * End game
     */

    public void endMatch() {
        ArrayList<Player> winnersByVictoryPoints = new ArrayList<Player>();
        ArrayList<Player> winnersByMoney = new ArrayList<Player>();
        Player winner = null;
        boolean weHaveAWinner = false;

        int maxVictoryPoints = 0;
        for (Player player : match.getPlayers()) {
            if (player.getVictoryPoints() > maxVictoryPoints) {
                winnersByVictoryPoints.clear();
                winnersByVictoryPoints.add(player);
            } else if (player.getVictoryPoints() == maxVictoryPoints)
                winnersByVictoryPoints.add(player);
        }

        if (winnersByVictoryPoints.size() != 1) {
            int maxMoney = 0;
            for (Player player : winnersByVictoryPoints) {
                if (player.getMoney() > maxMoney) {
                    winnersByMoney.clear();
                    winnersByMoney.add(player);
                } else if (player.getMoney() == maxMoney)
                    winnersByMoney.add(player);
            }
        } else {
            winner = winnersByVictoryPoints.get(0);
            weHaveAWinner = true;
        }

        if (winnersByMoney.size() != 1 && weHaveAWinner == false) {
            // Mostra interfaccia tabella spareggi e offri opzione per indicare chi vince tra i giocatori rimasti

            winner = winnersByMoney.get(0); //da sostituire con chiamata a interfaccia!
        } else if (winnersByMoney.size() == 1 && weHaveAWinner == false) {
            winner = winnersByMoney.get(0);
        }

        gameInterface.playerHasWonTheGame(winner);

        //chiamo interfaccia e mostro il winner :D

    }

    /**
     * At every turn this method sets the new first player (the next in the array list)
     */

    public void setNextFirstPlayer() {
        ArrayList<Player> players = match.getPlayers();
        int firstPlayerIndex = players.indexOf(match.getFirstPlayer());
        match.getFirstPlayer().setFirstPlayer(false);
        firstPlayerIndex++;
        if (firstPlayerIndex >= players.size()) {
            firstPlayerIndex = 0;
        }
        players.get(firstPlayerIndex).setFirstPlayer(true);
    }

    /**
     * Starts current turn
     */

    public void startTurn() {
        giveActionCards(NUMBER_OF_ACTIONCARDS_AT_EACH_TURN);

        match.setMatchPhase(MatchPhase.FIRST_BET_PHASE);
        gameInterface.updateUIForPhase(match.getMatchPhase());
        betPhase();

        match.setMatchPhase(MatchPhase.RIG_PHASE);
        gameInterface.updateUIForPhase(match.getMatchPhase());
        rigPhase();

        match.setMatchPhase(MatchPhase.SECOND_BET_PHASE);
        gameInterface.updateUIForPhase(match.getMatchPhase());
        betPhase();

        match.setMatchPhase(MatchPhase.RACE_PHASE);
        gameInterface.updateUIForPhase(match.getMatchPhase());

        raceManager.startRace();
    }

    /**
     * Ends the turn
     */

    public void wrapUpTurn() {

        betManager.paymentTime(raceManager.getStanding());
        raceManager.updateStableQuotations();
        gameInterface.updateStableQuotations(match.getStables());
        raceManager.resetRace(match.getActionCardDeck());

        match.setBetMarkPool();

    }

    /**
     * Riceve un ArrayList di stringhe che corrispondono ai "nickname" scelti per ogni giocatore
     * e crea nel modello i giocatori corrispondenti
     *
     * @param players ArrayList<String> nickname dei giocatori
     */

    public void setPlayers(ArrayList<String> players) {
        for (String temp : players) {
            match.addPlayer(new Player(temp));
        }
    }

    /**
     * Manages the rig phase in which every player adds his action cards to the action card pile of a certain horse
     */

    private void rigPhase() {
        while (someoneStillHasActionCards()) {
            for (int i = 0; i < match.getPlayers().size(); i++) {
                Player player = getNextPlayer();
                gameInterface.setCurrentPlayer(player);

                if (!player.isActionCardPileEmpty()) {

                    ArrayList<Horse> horses = new ArrayList<Horse>();
                    for (Stable stable : match.getStables()) {
                        horses.add(stable.getHorse());
                    }

                    ArrayList horseAndCard = gameInterface.getActionCardToPlayOnHorse(horses, player.getActionCardPile());

                    ActionCard card = (ActionCard) horseAndCard.get(0);
                    Horse horse = (Horse) horseAndCard.get(1);
                    //take the card to play and the horse to play it on from the gui
                    player.playActionCard(card, horse);
                }
            }
        }
    }

    /**
     * Checks if there's a player who still has action cards to play, just a check to be sure
     *
     * @return true if there's such a player, false otw
     */

    private boolean someoneStillHasActionCards() {
        for (Player player : match.getPlayers()) {
            if (!player.isActionCardPileEmpty())
                return true;
        }
        return false;
    }

    /**
     * Checks if there's a player who still has bets to make, just a check to be sure
     *
     * @return true if there's such a player, false otw
     */

    private boolean someoneStillHasBetsToMake() {
        //da implementare
        return false;
    }

    /**
     * Get the next player to play in the turn flow, it changes the order of the choice based on the current game phase
     * In particular, the choice is always clockwise and it's anti-clockwise in the second bet phase
     *
     * @return the next player
     */

    public Player getNextPlayer() {
        int current = match.getCurrentPLayer();
        switch (match.getMatchPhase()) {
            case FIRST_BET_PHASE:
            case RIG_PHASE: {
                for (Player player : match.getPlayers()) {
                    if (player.getTurnOrder() == current) {
                        match.setCurrentPLayer(current + 1);
                        return player;
                    }
                }

                break;
            }
            case SECOND_BET_PHASE: {
                for (Player player : match.getPlayers()) {
                    if (player.getTurnOrder() == ((match.getPlayers().size() - 1) - current)) {
                        match.setCurrentPLayer(current + 1);
                        return player;
                    }
                }

                break;
            }
            case RACE_PHASE:
                break;
            case END_GAME:
                break;
            default:
                break;
        }

        return null;
    }

    /**
     * Method which updates the first player to cycle from (it's the first player which change from a turn to one another)
     */

    private void updatePlayersOrder() {
        ArrayList<Player> players = match.getPlayers();
        int firstPlayerIndex = 0;
        for (Player player : players) {
            if (player.isFirstPlayer())
                firstPlayerIndex = players.indexOf(player);
        }
        for (int i = 0; i < players.size(); i++) {
            if (i < firstPlayerIndex) {
                players.get(i).setTurnOrder(players.size() - firstPlayerIndex + i);
            } else if (i >= firstPlayerIndex) {
                players.get(i).setTurnOrder(i - firstPlayerIndex);
            }
        }
    }

    private void playerHasLostGame(Player player) {
        // Segnala tramite interfaccia che il giocatore ha perso
        gameInterface.playerHasLostTheGame(player);

        match.getPlayers().remove(player);

    }

    /**
     * Implements the bet phase flow
     */

    private void betPhase() {

        //metodo checkLoss per determinare eventuali decurtazioni ai pv o estromissione del giocatore dalla partita
        for (int i = 0; i < match.getNumberOfPlayers(); i++) {
            Player player = getNextPlayer();

            if (match.getMatchPhase() == MatchPhase.FIRST_BET_PHASE) {
                gameInterface.setCurrentPlayer(player);
            }

            gameInterface.updateBetMarkPool(match.getBetMarkPool());

            boolean wantsToBet = true;

            if (match.getMatchPhase() == MatchPhase.SECOND_BET_PHASE) {
                wantsToBet = gameInterface.userWantsToBet(player); //prende valore da interfaccia, voglio fare seconda socmmessa?
            }

            System.out.println("L'utente vuole scommettere: " + wantsToBet);

            boolean betCorrectlyMade = false;
            boolean canBet = true;

            while ((!betCorrectlyMade) && wantsToBet && canBet) {
                while (!player.canMakeMinimumBet()) {
                    int victoryPoints = player.getVictoryPoints();
                    victoryPoints -= NUMBER_OF_VP_LOSE;
                    if (victoryPoints < 1) {
                        playerHasLostGame(player);
                        canBet = false;
                    } else
                        player.setVictoryPoints(victoryPoints);
                }

                if (!canBet)
                    break;

                //chiama interfaccia e chiede al giocatore i valori per la makeBet
                Bet playerBet = gameInterface.getPlayerBet(match.getStables());

                try {
                    playerBet = player.makeBet(playerBet);
                    betManager.insertBet(playerBet);
                    betCorrectlyMade = true;
                    gameInterface.betWasRegisteredCorrectly();
                    gameInterface.updatePlayersInfo(match.getPlayers());
                } catch (InvalidBetException e) {
                    switch (e.getType()) {

                        case NOTENOUGHTMONEY:
                            gameInterface.betRegistrationError("You don't have enough money!");
                            break;
                        case NOTAMINIMUMBET:
                            gameInterface.betRegistrationError("That's not a minimum bet!");
                            break;
                        case NOMOREREMAINIGBETS:
                            gameInterface.betRegistrationError("You have finished your bets!");
                            break;
                        case NOT_ENOUGH_BET_MARKS:
                            gameInterface.betRegistrationError("There are no bet marks available for this horse");
                            break;
                        case SAME_BET:
                            gameInterface.betRegistrationError("You already made this bet");
                            break;
                    }
                }
            }
        }
    }

    /**
     * Give action cards to players (numCards per player) drawing the from the action cards deck
     *
     * @param numCards number of cards to give to each players (parametrized for future changes)
     */

    private void giveActionCards(int numCards) {
        for (Player player : match.getPlayers()) {
            for (int i = 0; i < numCards; i++)
                player.addActionCard((ActionCard) match.getActionCardDeck().draw());
        }
    }

    /**
     * Randomized choice of the first player
     */

    private void initializeFirstPlayer() {
        Random generator = new Random();
        int temp = generator.nextInt(match.getPlayers().size() - 1);
        match.getPlayers().get(temp).setFirstPlayer(true);
    }

    /**
     * Give character cards to players (1 per player) drawing the from the character cards deck
     */

    private void giveCharacterCards() {
        for (Player player : match.getPlayers()) {
            player.setCharCard((CharacterCard) match.getCharacterCardDeck().draw());
        }

        initializePlayersInfo();
    }


    /**
     * Give players their starting money based on the character card they got
     * Player and respective stable get linked
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

    public RaceManager getRaceManager() {
        return raceManager;
    }
}