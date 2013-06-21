package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 27/05/13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class StableCard extends Card {
    private String stableName;
    private StableColor color;
    private ArrayList<Integer> placementEarning;

    public String getStableName() {
        return stableName;
    }

    public ArrayList<Integer> getPlacementEarning() {
        return placementEarning;
    }

    public StableColor getColor() {
        return color;
    }
}
