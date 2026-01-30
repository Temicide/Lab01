package logic;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Objects;

public class UnitDeck {
    String deckName;
    private ArrayList<CardCounter> cardsInDeck;

    public UnitDeck(String deckName) {
        this.deckName = deckName;
        this.cardsInDeck = new ArrayList<CardCounter>();
    }

    public void addCard(UnitCard newCard, int count) {
        if (count < 1) return;

        CardCounter tempCardCounter = new CardCounter(newCard, count);
        boolean foundCard = false;
        for (CardCounter card : cardsInDeck) {
            if (newCard == card.getCard()) {
                card.setCount(card.getCount() + 1);
                foundCard = true;
                break;
            }
        }
        if (!foundCard) cardsInDeck.add(tempCardCounter);

    }

    public void removeCard(UnitCard toRemove, int count) {

        if (count < 1) return;

        for (CardCounter card : cardsInDeck) {
            if (toRemove == card.getCard()) {
                int remains = card.getCount() - count;

                if (remains > 0) {
                    card.setCount(card.getCount() - count);
                }
                else {
                    cardsInDeck.remove(card);
                }

                break;
            }
        }

    }

    public int cardCount() {
        int total = 0;
        for (CardCounter card : cardsInDeck) {
            total += card.getCount();
        }
        return total;
    }

    public boolean existsInDeck(UnitCard card) {
        for (CardCounter storedCard : cardsInDeck) {
            if (card == storedCard.getCard()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnitDeck unitDeck = (UnitDeck) o;
        return Objects.equals(deckName, unitDeck.deckName);
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        if (deckName.isBlank()) {
            deckName = "Untitled Deck";
        }
        this.deckName = deckName;
    }

    public ArrayList<CardCounter> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<CardCounter> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }
}
