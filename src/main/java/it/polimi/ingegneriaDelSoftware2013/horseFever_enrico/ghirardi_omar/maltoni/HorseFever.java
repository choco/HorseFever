package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 18/05/13
 * Time: 17.54
 * To change this template use File | Settings | File Templates.
 */
public class HorseFever {
    //launches the game
    public static void main(String[] args) {

        MatchController newMatch = new MatchController();
        ArrayList<String> nomi = new ArrayList<String>();
        nomi.add("Prova");
        nomi.add("Test");
        nomi.add("Ancora");
        newMatch.setPlayers(nomi);


    }

}
