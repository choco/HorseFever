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
    NOTENOUGHTMONEY, NOTAMINIMUMBET, NOMOREREMAINIGBETS
}

class InvalidBetException extends Exception {

    private InvalidBetExceptionType type;

    public InvalidBetException(InvalidBetExceptionType type) {
        this.type = type;
    }
}

public class Bet {

    private Player bettingPlayer;
    private int amount;
    private Stable bettingStable;
    private BetType type;


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