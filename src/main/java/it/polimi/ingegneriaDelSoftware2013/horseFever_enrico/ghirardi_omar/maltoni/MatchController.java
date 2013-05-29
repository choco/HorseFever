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
    private GameInterface gameInterface;

    public void startMatch() {
        //gameInterface = new GameInterface();
        giveCharacterCards();
        setFirstPlayer();
        giveActionCards(2);
    }

    /*
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
   */
    private void betPhase(boolean mandatory) {

        //variables which will be updated via user interface
        int amount = 0;
        Stable stable = null;
        BetType type = null;

        // bisogna implementare il problema di compiere questo for in senso antiorario o orario in base alla variabile mandatory (true = orario, false = antiorario)
        ArrayList<Player> players = match.getPlayers();
        Player firstPlayer = match.getFirstPlayer();
        int j = players.indexOf(firstPlayer);

        /* if the bet is mandatory it means the game is currently at the first bet which every player have to do in clockwise order starting from the 1st player
           if the bet isn't mandatory the players, starting from the 1st player, are not obliged to do a bet (anticlockwise order)
         */
        if (mandatory) {
            /*for (Player player : match.getPlayers()) {   //senso orario
                /* if (!mandatory) {
                    // possibilita di uscire dalla funzione e non farla

                    if(false) { //se ha scelto di non farla
                        continue;
                    }
                }    */
            for (int i = 0; i < players.size(); i++) {
                if (j >= players.size()) j = 0;

                //chiama interfaccia e chiede al giocatore i valori per la makeBet

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
        // assegnamento temporaneo in attesa di struttura migliore per gestire i players

        ArrayList<Player> players = match.getPlayers();

        Player player = match.getFirstPlayer();

        // j is the index of the current player
        // j is initialized as the index of the player next to the first player (clockwise)
        int j = players.indexOf(player) + 1;
        for (int i = 0; i < players.size(); i++) {
            if (j >= players.size()) j = 0;

            for (int k = 0; k < numCards; k++) {
                players.get(j).addActionCard((ActionCard) match.getActionCardDeck().draw());

            }
            ++j;
        }
    }

    private void setFirstPlayer() {
        Random generator = new Random();
        match.getPlayers().get(generator.nextInt(match.getPlayers().size() - 1)).setFirstPlayer(true);
    }

    private void giveCharacterCards() {
        for (Player player : match.getPlayers()) {
            player.setCharCard((CharacterCard) match.getCharacterCardDeck().draw());
        }
    }
}