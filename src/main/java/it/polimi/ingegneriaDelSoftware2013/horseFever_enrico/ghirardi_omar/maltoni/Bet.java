package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */

enum BetType {
    WINNING, PLACED
}

enum InvalidBetExceptionType {
    NOTENOUGHTMONEY, NOTAMINIMUMBET, NOMOREREMAINIGBETS, NOT_ENOUGH_BET_MARKS, SAME_BET
}

class InvalidBetException extends Exception {

    private InvalidBetExceptionType type;

    public InvalidBetExceptionType getType() {
        return type;
    }

    public InvalidBetException(InvalidBetExceptionType type) {
        this.type = type;
    }
}


public class Bet {

    private Player bettingPlayer;
    private int amount;
    private Stable bettingStable;
    private BetType type;

    /**
     * Constructor of a bet type object
     * @param bettingPlayer current player who's making a bet
     * @param amount        amount of money the player wants to bet
     * @param bettingStable stable on which the player bets
     * @param type          type of bet
     */
    public Bet(Player bettingPlayer, int amount, Stable bettingStable, BetType type) {
        this.bettingPlayer = bettingPlayer;
        this.amount = amount;
        this.bettingStable = bettingStable;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public Player getBettingPlayer() {
        return bettingPlayer;
    }

    public Stable getBettingStable() {
        return bettingStable;
    }

    public BetType getType() {
        return type;
    }


}