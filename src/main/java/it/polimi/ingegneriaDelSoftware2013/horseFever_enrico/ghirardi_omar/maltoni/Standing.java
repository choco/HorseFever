package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class Standing {
    private Map<Lane, Integer> standing;

    public Standing(Lane[] lanes) {
        for (Lane lane : lanes) {
            standing.put(lane, 0);
        }
    }

    /* currently untested!!!!! */
    public void updateStandings() {
        ArrayList<Lane> temp = new ArrayList<Lane>(standing.keySet());
        Collections.sort(temp);
        int rank = 0;
        for (Lane lane : temp) {
            if ((lane.getHorse().hasFinishedRace()))
                rank++;
        }

        int lastPosition = -1;
        for (Lane lane : temp) {
            if (!lane.getHorse().hasFinishedRace()) {
                if ((lane.getHorse()).getCurrentPosition() != lastPosition)
                    rank += 1;
                standing.put(lane, rank);
                lastPosition = lane.getHorse().getCurrentPosition();
            }
        }
    }

    public void fixUpStandingsBasedOnQuotations(BlackBoard board) {
        /* usa le quotazioni salvate dentro la blackboard per determinare la classifica finale */
    }
}
