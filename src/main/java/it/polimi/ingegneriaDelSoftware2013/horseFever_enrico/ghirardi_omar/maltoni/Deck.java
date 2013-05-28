package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public class Deck {
    private ArrayList<Card> cards;
    private static final int numOfShuffles = 100;

    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    void shuffle() {
        Random generator = new Random();
        int firstIndex, secondIndex;
        Card temp;

        for (int i = 0; i < numOfShuffles; i++) {
            firstIndex = generator.nextInt(cards.size() - 1);
            secondIndex = generator.nextInt(cards.size() - 1);
            temp = cards.get(firstIndex);
            cards.set(firstIndex, cards.get(secondIndex));
            cards.set(secondIndex, temp);
        }
    }

    Card draw() {
        return cards.remove(0);
    }

    void putBottom(Card card) {
        cards.add(card);
    }
}
