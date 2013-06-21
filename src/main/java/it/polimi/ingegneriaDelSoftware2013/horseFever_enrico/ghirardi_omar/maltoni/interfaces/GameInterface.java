package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.interfaces;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Omar
 * Date: 29/05/13
 * Time: 13.24
 * To change this template use File | Settings | File Templates.
 */
public interface GameInterface {

    /**
     * Updates the bet mark pool after every choice of the players
     *
     * @param betMarkPool hash map representing the bet mark pool to update
     */

    void updateBetMarkPool(Map<StableColor, Integer> betMarkPool);

    /**
     * Updates players info after every changing
     *
     * @param players array list representing the players taking part in the game
     */

    void updatePlayersInfo(ArrayList<Player> players);

    /**
     * Checks if the current user wants to make the second bet
     *
     * @param player that wants to bet
     * @return a boolean value representing the choice of the current player
     */

    boolean userWantsToBet(Player player);

    /**
     * Get the bet from the player through the gui
     *
     * @param stables array list of stable on which the player will bet
     * @return the bet made by the player
     */

    Bet getPlayerBet(ArrayList<Stable> stables);

    /**
     * Informs the player if his bet has correctly been registered
     */

    void betWasRegisteredCorrectly();

    /**
     * Informs the player if his bet isn't valid
     *
     * @param s the error message to show
     */

    void betRegistrationError(String s);

    /**
     * Updates the gui depending on the games phase
     *
     * @param matchPhase the phase of the match
     */

    void updateUIForPhase(MatchPhase matchPhase);

    /**
     * Informs the user of the next active player
     *
     * @param player the next player who's going to play
     */

    void setCurrentPlayer(Player player);

    /**
     * Gets the action card to play from an array list of cards
     *
     * @param cards array list of cards from which it will take the card
     * @return the chosen card
     */


    void playerHasLostTheGame(Player player);

    void playerHasWonTheGame(Player winner);

    void userShouldStartRaceTurn();

    void updateRacePhase(RacePhase racePhase);

    void userShouldThrowSprintDices();

    void showSprintingHorses(ArrayList<StableColor> sprintingColors);

    void updateCurrentMovementCard(MovementCard movementCard);

    void updateStableQuotations(ArrayList<Stable> stables);

    ArrayList getActionCardToPlayOnHorse(ArrayList<Horse> horses, ArrayList<ActionCard> actionCardPile);

    void updateRaceStandings(Map<Stable, Integer> standing);
}
