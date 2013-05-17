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
    private Lane bettingLane;
    private BetType type;


    public Bet(Player bettingPlayer, int amount, Lane bettingLane, BetType type) {
        this.bettingPlayer = bettingPlayer;
        this.amount = amount;
        this.bettingLane = bettingLane;
        this.type = type;
    }

    public Player getBettingPlayer() {
        return bettingPlayer;
    }

    public Lane getBettingLane() {
        return bettingLane;
    }

    public BetType getType() {
        return type;
    }




}