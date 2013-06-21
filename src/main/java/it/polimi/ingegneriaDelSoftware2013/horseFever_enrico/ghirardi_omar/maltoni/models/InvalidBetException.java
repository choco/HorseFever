package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 21/06/13
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class InvalidBetException extends Exception {

    private InvalidBetExceptionType type;

    public InvalidBetExceptionType getType() {
        return type;
    }

    public InvalidBetException(InvalidBetExceptionType type) {
        this.type = type;
    }
}