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
    private int turnOrder;
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
        turnOrder = 0;
        victoryPoints = 1;
        remainingBets = 2;
        firstPlayer = false;
        //characterCard
        actionCardPile = new ArrayList<ActionCard>();
        ownedStables = new ArrayList<Stable>();
    }

    public ArrayList<ActionCard> getActionCardPile() {
        return actionCardPile;
    }

    public String getIdTag() {
        return idTag;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public void addStable(Stable stable) {
        ownedStables.add(stable);
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    Bet makeBet(Bet bet) throws InvalidBetException {
        return makeBet(bet.getAmount(), bet.getType(), bet.getBettingStable());
    }

    Bet makeBet(int amount, BetType type, Stable stable) throws InvalidBetException {
        if (remainingBets > 0) {
            if (amount <= money) {
                if (amount >= victoryPoints * 100) {
                    money -= amount;
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

    boolean canMakeMinimumBet() {
        if (victoryPoints * 100 > money)
            return false;
        return true;
    }

    public void setCharCard(CharacterCard card) {
        charCard = card;
    }

    public CharacterCard getCharCard() {
        return charCard;
    }

    public void addActionCard(ActionCard card) {
        actionCardPile.add(card);
    }


    void playActionCard(ActionCard card, Horse horse) {
        horse.addActionCard((ActionCard) actionCardPile.remove(actionCardPile.indexOf(card)));
    }

    public ArrayList<Stable> getOwnedStables() {
        return ownedStables;
    }

    @Override

    public String toString() {
        return "Player Info:\nNickname=" + idTag + "\nTurn order=" + turnOrder +
                "\nMoney=" + money + "\nVictory Points=" + victoryPoints + "\nIs First Player:" + firstPlayer +
                "\nCharacter name=" + charCard.getCharName() + "\nAction cards=" + actionCardPile + "\nStables=" + ownedStables + "\n";
    }
}


