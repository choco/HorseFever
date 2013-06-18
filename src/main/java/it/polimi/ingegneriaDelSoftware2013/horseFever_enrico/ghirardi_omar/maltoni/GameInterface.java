package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

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

    void updateBetMarkPool(Map<StableColor, Integer> betMarkPool);

    void updatePlayersInfo(ArrayList<Player> players);

    boolean userWantsToBet(Player player);

    Bet getPlayerBet(ArrayList<Stable> stables);

    void betWasRegisteredCorrectly();

    void betRegistrationError(String s);

    void updateUIForPhase(MatchPhase matchPhase);

    void setCurrentPlayer(Player player);

    ActionCard getActionCardToPlay(ArrayList<ActionCard> cards);

    Horse getHorseToPlayActionCardOn(ArrayList<Horse> horses);

    void playerHasLostTheGame(Player player);
}
