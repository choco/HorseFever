package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.*;

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
    private Map<StableColor, Integer> betMarkPool;

    private static final int WINNING_BET_VICTORY_POINTS = 3;
    private static final int PLACED_BET_VICTORY_POINTS = 1;
    private static final int PLACED_BET_MONEY_MULTIPLIER = 2;

    /**
     * Constructor of a bet manager object
     *
     * @param betMarkPool hash map representing the bet mark pool
     */

    public BetManager(Map<StableColor, Integer> betMarkPool) {
        bets = new ArrayList<Bet>();
        this.betMarkPool = betMarkPool;
    }

    /**
     * Inserts bet class objects in the bets array list of bets, attribute of the class bet manager
     *
     * @param bet bet to add to bets
     * @throws InvalidBetException exception thrown if the bet parameter isn't valid
     */

    public void insertBet(Bet bet) throws InvalidBetException {
        checkBetValidity(bet);
        bets.add(bet);
        betMarkPool.put(bet.getBettingStable().getColor(), betMarkPool.get(bet.getBettingStable().getColor()) - 1);
    }

    /**
     * Remove all bets made by a specific player (usefull when this player has lost the game)
     *
     * @param player The player whose bets will be removed
     */

    void removeBetsMadeByPlayer(Player player) {
        ArrayList<Bet> betsBin = new ArrayList<Bet>();

        for (Bet bet : bets) {
            if (bet.getBettingPlayer() == player) {
                betsBin.add(bet);
            }
        }

        for (Bet bet : betsBin) {
            bets.remove(bet);
        }
    }


    /**
     * Check the validity of the bet
     *
     * @param bet bet to check
     * @throws InvalidBetException exception thrown if the bet parameter isn't valid
     */

    public void checkBetValidity(Bet bet) throws InvalidBetException {
        System.out.println(bet);
        if (betMarkPool.get(bet.getBettingStable().getColor()) < 1)
            throw new InvalidBetException(InvalidBetExceptionType.NOT_ENOUGH_BET_MARKS);
        for (Bet temp : bets) {
            if (bet.getBettingPlayer() == temp.getBettingPlayer()) {
                if (bet.getBettingStable() == temp.getBettingStable()) {
                    if (bet.getType() == temp.getType())
                        throw new InvalidBetException(InvalidBetExceptionType.SAME_BET);
                }
            }
        }
    }

    /**
     * Check all the bets in the bets array list for the winning ones
     *
     * @param bet      bet to check
     * @param position final standing position of the stable referred by the bet
     * @return true if it's a winning bet, false otw
     */

    public boolean checkWinningBet(Bet bet, int position) {
        if (bet.getType() == BetType.WINNING) {
            if (position == 1)
                return true;
        } else if (bet.getType() == BetType.PLACED) {
            if (position >= 1 && position <= 3)
                return true;
        }

        return false;
    }

    /**
     * Pays the player who's bet is a winning one by updating his money stack
     *
     * @param bet bet to pay
     */

    public void payBet(Bet bet) {
        Player winner = bet.getBettingPlayer();
        if (bet.getType() == BetType.WINNING) {
            winner.setMoney(winner.getMoney() + bet.getAmount() * bet.getBettingStable().getQuotation());
            winner.setVictoryPoints(winner.getVictoryPoints() + WINNING_BET_VICTORY_POINTS);
        } else if (bet.getType() == BetType.PLACED) {
            winner.setMoney(winner.getMoney() + bet.getAmount() * PLACED_BET_MONEY_MULTIPLIER);
            winner.setVictoryPoints(winner.getVictoryPoints() + PLACED_BET_VICTORY_POINTS);
        }
    }

    /**
     * Check the stable final position in the race with the one guessed by the player through the bet and pay those who win a bet
     *
     * @param standing hash map which contains the final position of every stable after the race
     */

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
                    earningMoney = stable.getStableCard().getPlacementEarning().get(0);
                    break;
                case 2:
                    earningMoney = stable.getStableCard().getPlacementEarning().get(1);
                    break;
                case 3:
                    earningMoney = stable.getStableCard().getPlacementEarning().get(2);
                    break;
                default:
                    break;
            }
            if (stable.getStableOwner() != null) {
                stable.getStableOwner().setMoney(stable.getStableOwner().getMoney() + earningMoney);
            }
        }

        bets.clear();

    }

}
