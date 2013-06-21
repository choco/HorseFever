package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */

public class Stable implements Comparable<Stable> {

    private StableColor color; //true stable ID...it is unique for each stable
    private Player stableOwner;
    private Horse horse;
    private StableCard stableCard;
    private int quotation;

        /*temporary constructor for testing*/

    /**
     * Constructor of a stable object
     *
     * @param color of the stable to create
     */
    public Stable(StableColor color) {
        this.color = color;
        horse = new Horse(this);
    }

    public Player getStableOwner() {
        return stableOwner;
    }

    public void setStableOwner(Player player) {
        stableOwner = player;
    }

    public void setStableCard(StableCard card) {
        stableCard = card;
    }

    public void setQuotation(int quotation) {
        if (quotation > 7)
            quotation = 7;
        else if (quotation < 2)
            quotation = 2;

        this.quotation = quotation;
    }

    public int getQuotation() {
        return quotation;
    }

    public Horse getHorse() {
        return horse;
    }

    public StableCard getStableCard() {
        return stableCard;
    }

    /**
     * Compares the position of the horse owned by the selected stable with the position of the other horses to infer
     * The standing and if the horse is first or last (needed for some cards)
     *
     * @param stable which owns the horse
     * @return the current position
     */

    public int compareTo(Stable stable) {
        if (this.horse.getCurrentPosition() > stable.horse.getCurrentPosition())
            return -1;
        else if (this.horse.getCurrentPosition() == stable.horse.getCurrentPosition())
            return 0;
        else
            return 1;
    }

    public StableColor getColor() {
        return this.color;
    }


    @Override

    public String toString() {
        return "Stable Info:\nColor=" + color + "\nHorse=" + horse + "\nQuotation=" + quotation + "\nStable card=" + stableCard.getStableName() + "\n";
    }
}
