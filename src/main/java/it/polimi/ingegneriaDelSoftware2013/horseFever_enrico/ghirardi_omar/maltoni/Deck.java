package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
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

    public Deck(String cardsFile, CardType type) throws IOException {
        ArrayList cards = new ArrayList();

        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createParser(new FileInputStream(cardsFile));
        ObjectMapper mapper = new ObjectMapper();
        // advance stream to START_ARRAY first:
        jp.nextToken();
        // and then each time, advance to opening START_OBJECT

        while (jp.nextToken() == JsonToken.START_OBJECT) {
            Card card = null;

            switch (type) {
                case ACTION:
                    card = (ActionCard) (mapper.readValue(jp, ActionCard.class));
                    break;

                case MOVEMENT:
                    card = (MovementCard) (mapper.readValue(jp, MovementCard.class));
                    break;

                case CHARACTER:
                    card = (CharacterCard) (mapper.readValue(jp, CharacterCard.class));
                    break;

                case STABLE:
                    card = (StableCard) (mapper.readValue(jp, StableCard.class));
                    break;

                case HORSE:
                    break;

                case EMPLOYER:
                    break;

                case GOAL:
                    break;

                default:
                    break;
            }

            cards.add(card);
        }

        this.cards = cards;
    }

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
