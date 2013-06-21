package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models;

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

    /**
     * Constructor of a player object
     *
     * @param name the id tag associated with the player
     */
    public Player(String name) {
        idTag = name;
        money = 0;
        turnOrder = 0;
        victoryPoints = 1;
        remainingBets = 2;
        firstPlayer = false;
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

    /**
     * Makes a bet receiving a bet type parameter
     *
     * @param bet bet received
     * @return the bet updated with the other makebet method
     * @throws InvalidBetException exception thrown if the bet isn't valid
     */

    public Bet makeBet(Bet bet) throws InvalidBetException {
        return makeBet(bet.getAmount(), bet.getType(), bet.getBettingStable());
    }

    /**
     * Makes a proper bet receiving from the current player the amount of money he's willing to bet, the type of bet
     * And the stable he wants to bet on
     *
     * @param amount amount of money on th plate
     * @param type   type of bet to make
     * @param stable stable to bet on
     * @return the bet properly initialized
     * @throws InvalidBetException exception thrown if the bet isn't valid
     */

    public Bet makeBet(int amount, BetType type, Stable stable) throws InvalidBetException {
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

    /**
     * Checks if the player can make a minimum bet (a suf. and nec. condition for the player to lose the game)
     *
     * @return true if the player can actually make a minimum bet, false if the player loses the game
     */

    public boolean canMakeMinimumBet() {
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

    /**
     * Adds a card to the action cards deck of the player
     *
     * @param card card to add
     */

    public void addActionCard(ActionCard card) {
        actionCardPile.add(card);
    }

    /**
     * Play the card on the horse
     *
     * @param card  card to assign
     * @param horse horse to assign the card to
     */

    public void playActionCard(ActionCard card, Horse horse) {
        horse.addActionCard(actionCardPile.remove(actionCardPile.indexOf(card)));
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


