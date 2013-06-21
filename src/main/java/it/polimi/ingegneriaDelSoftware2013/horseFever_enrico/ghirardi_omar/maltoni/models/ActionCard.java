package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 17/05/13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */

public class ActionCard extends Card {
    private String cardName;
    private String action;
    private String cardDescription;
    private ActionType type;
    private int actionValue;
    private Character cardLetter;

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }

    public Character getCardLetter() {
        return cardLetter;
    }

    public void setCardLetter(Character cardLetter) {
        this.cardLetter = cardLetter;
    }


    @Override
    public String toString() {
        return "ActionCard [name=" + cardName + ", cardLetter=" + cardLetter + ", actionType=" + type + ", action=" + action + ", actionValue=" + actionValue + ", imagePath=" + imagePath + ", cardDescription=" + cardDescription + "]\n";
    }
}
