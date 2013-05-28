package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */

enum StableColor {
    BLACK, BLUE, GREEN, RED, YELLOW, WHITE
}

public class Stable implements Comparable<Stable> {

    private StableColor color;

    private Horse horse;
    private StableCard stableCard;
    private int quotation;

        /*temporary constructor for testing*/

    public Stable(StableColor color) {
        this.color = color;
    }

    public void setQuotation(int quotation) {
        this.quotation = quotation;
    }

    public int getQuotation() {
        return quotation;
    }

    Horse getHorse() {
        return horse;
    }

    public int compareTo(Stable stable) {
        if (this.horse.getCurrentPosition() > stable.horse.getCurrentPosition())
            return 1;
        else if (this.horse.getCurrentPosition() == stable.horse.getCurrentPosition())
            return 0;
        else
            return -1;
    }

    /*    Stable interfaccia di StableCard? Non ricordo come volevamo implementare

    private String stableName; //quello sulla carta, giusto per l'interfaccia
    private StableColor color; //vero ID che nella logica contraddistingue una scuderia, univoco (static final?)


    public StableColor getColor (){
        return this.color;
    }

    */
}
