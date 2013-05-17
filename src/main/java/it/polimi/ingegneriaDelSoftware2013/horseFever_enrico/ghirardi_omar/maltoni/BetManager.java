package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class BetManager {
    private ArrayList<Bet> bets;

    boolean insertBet(Bet bet) {
       if(checkBetValidity(bet)) {
           bets.add(bet);
           return true;
       }

       return false;
    }

    boolean checkBetValidity(Bet bet) {
        for(Bet temp : bets) {
            if(bet.getBettingPlayer()==temp.getBettingPlayer()) {
                if(bet.getBettingLane()==temp.getBettingLane()) {
                    if(bet.getType()==temp.getType())
                        return false;
                }
            }
        }

        return true;
    }

    boolean checkWinningBet(Bet bet) {
        return true;
    }

    /*
    void payBet(Bet bet) {
        if(checkWinningBet(bet)) {
            if(bet.getType() == BetType.WINNING)
        }
    }*/

    void paymentTime(BlackBoard board) {
        for(Bet bet : bets) {
            ;
        }
    }

}
