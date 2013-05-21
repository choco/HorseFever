package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class BlackBoard {
    public Map<Stable, Integer> stableQuotations;


    /**
     * @param stablesArray : an array of Stable
     *                     <p/>
     *                     Given an array of stables, this constructor creates
     *                     the random quotations associated to these.
     */

    public BlackBoard(Stable stablesArray[]) {
        stableQuotations = new HashMap<Stable, Integer>();

        int size = stablesArray.length;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random generator = new Random();

        while (numbers.size() < size) {
            int random = generator.nextInt(size);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        for (int i = 0; i < size && !numbers.isEmpty(); i++) {
            stableQuotations.put(stablesArray[i], numbers.remove(0));
        }
    }

    void updateQuotations(Standing s) {
    }


}
