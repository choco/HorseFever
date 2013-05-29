package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */

public class Player {
    private String idTag;
    private int money;
    private int victoryPoints;
    private int remainingBets;
    private boolean firstPlayer;
    private CharacterCard charCard;
    private ArrayList<ActionCard> actionCardPile;

    private ArrayList<Stable> ownedStables;

    public Player(String name) {
        idTag = name;
        money = 0;
        victoryPoints = 1;
        remainingBets = 2;
        firstPlayer = false;
        //characterCard
        actionCardPile = new ArrayList<ActionCard>();
    }

    //method used in the boardgame
    public boolean isActionCardPileEmpty() {
        return actionCardPile.isEmpty();
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }


    public void setFirstPlayer(boolean value) {
        firstPlayer = value;
    }

    Bet makeBet(int amount, BetType type, Stable stable) throws InvalidBetException {
        if (remainingBets > 0) {
            if (amount <= money) {
                if (amount >= victoryPoints * 100) {
                    money -= amount;
                    remainingBets--;
                    return new Bet(this, amount, stable, type);
                } else {
                    throw new InvalidBetException(InvalidBetExceptionType.NOTAMINIMUMBET);
                }
            } else {
                throw new InvalidBetException(InvalidBetExceptionType.NOTENOUGHTMONEY);
            }
        } else {
            throw new InvalidBetException(InvalidBetExceptionType.NOMOREREMAINIGBETS);
        }

    }

    public void setCharCard(CharacterCard card) {
        charCard = card;
        updatePlayerInfo();
    }

    private void updatePlayerInfo() {
        // prende dati dalla charcard che gli è stata assegnata (soldi, colore) con anche le abilità (se aggiungiamo carte opzionali)
    }

    public void addActionCard(ActionCard card) {
        actionCardPile.add(card);
    }


    void playActionCard(ActionCard card, Horse horse) {
        horse.addActionCard((ActionCard) actionCardPile.remove(actionCardPile.indexOf(card)));
    }
}


