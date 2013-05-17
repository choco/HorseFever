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
    private int victoryPoint;
    private int remainingBets;
    private boolean isFirstPlayer;
    private CharacterCard charCard;
    private ArrayList<ActionCard> actionCardPile;

    Bet makeBet(int amount, BetType type, Lane lane) throws InvalidBetException{
        if(remainingBets > 0) {
            if(amount <= money) {
                if(amount >= victoryPoint*100) {
                    money -= amount;
                    remainingBets--;
                    return new Bet(this, amount, lane, type);
                }
                else {
                    throw new InvalidBetException(InvalidBetExceptionType.NOTAMINIMUMBET);
                }
            }
            else {
                throw new InvalidBetException(InvalidBetExceptionType.NOTENOUGHTMONEY);
            }
        }
        else {
            throw new InvalidBetException(InvalidBetExceptionType.NOMOREREMAINIGBETS);
        }
    }


    void playActionCard(ActionCard card, Lane lane) {
        ;
    }
}


