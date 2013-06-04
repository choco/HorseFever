package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class BetManager {

    private ArrayList<Bet> bets;

    private static final int WINNING_BET_VICTORY_POINTS = 3;
    private static final int PLACED_BET_VICTORY_POINTS = 1;
    private static final int PLACED_BET_MONEY_MULTIPLIER = 2;

    public BetManager() {
        bets = new ArrayList<Bet>();
    }

    boolean insertBet(Bet bet) {
        if (checkBetValidity(bet)) {
            bets.add(bet);
            return true;
        }

        return false;
    }

    //validità scommessa nelle due bet phases, no doppia scommessa
    boolean checkBetValidity(Bet bet) {
        for (Bet temp : bets) {
            if (bet.getBettingPlayer() == temp.getBettingPlayer()) {
                if (bet.getBettingStable() == temp.getBettingStable()) {
                    if (bet.getType() == temp.getType())
                        return false;
                }
            }
        }

        return true;
    }

    boolean checkWinningBet(Bet bet, int position) {
        if (bet.getType() == BetType.WINNING) {
            if (position == 1)
                return true;
        } else if (bet.getType() == BetType.PLACED) {
            if (position >= 1 && position <= 3)
                return true;
        }

        return false;
    }


    void payBet(Bet bet) {
        Player winner = bet.getBettingPlayer();
        if (bet.getType() == BetType.WINNING) {
            winner.setMoney(winner.getMoney() + bet.getAmount() * bet.getBettingStable().getQuotation());
            winner.setVictoryPoints(winner.getVictoryPoints() + WINNING_BET_VICTORY_POINTS);
        } else if (bet.getType() == BetType.PLACED) {
            winner.setMoney(winner.getMoney() + bet.getAmount() * PLACED_BET_MONEY_MULTIPLIER);
            winner.setVictoryPoints(winner.getVictoryPoints() + PLACED_BET_VICTORY_POINTS);
        }
    }

    void paymentTime(Map<Stable, Integer> standing) {
        for (Bet bet : bets) {
            int position = standing.get(bet.getBettingStable());
            if (checkWinningBet(bet, position)) {
                payBet(bet);
            }
        }

        for (Stable stable : standing.keySet()) {
            int earningMoney = 0;
            switch (standing.get(stable)) {
                case 1:
                    earningMoney = stable.getStableCard().getPlacementEarning().get(1);
                    break;
                case 2:
                    earningMoney = stable.getStableCard().getPlacementEarning().get(2);
                    break;
                case 3:
                    earningMoney = stable.getStableCard().getPlacementEarning().get(3);
                    break;
                default:
                    break;
            }

            stable.getStableOwner().setMoney(stable.getStableOwner().getMoney() + earningMoney);
        }

    }

}
