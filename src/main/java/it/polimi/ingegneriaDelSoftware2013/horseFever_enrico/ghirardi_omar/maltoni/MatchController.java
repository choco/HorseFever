package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 27/05/13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class MatchController {
    /*
    public void startMatch() {
        giveCharacterCards();
        setFirstPlayer();

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
        //per family game e quasi anche per il boardgame

        //prima fase

        // bisogna implementare il problema di compiere questo for in senso antiorario o orario in base alla variabile mandatory (true = orario, false = antiorario)
        for (Player player : players) {   //senso orario
            if (!mandatory) {
                // possibilita di uscire dalla funzione e non farla

                if(false) { //se ha scelto di non farla
                    continue;
                }
            }
            //chiama interfaccia e chiede al giocatore i valori per la makeBet


            boolean betCorrectlyMade = false;
            while (!betCorrectlyMade){
                Bet playerBet;

                try {
                    //playerBet = player.makeBet(amount, type, lane);
                } catch () {

                }

                if (betManager.insertBet(playerBet)) {
                    betCorrectlyMade = true;
                }

            }
        }


    }

    private void giveActionCards(int numCards) {
        // assegnamento temporaneo in attesa di struttura migliore per gestire i players
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
    }    */
}
