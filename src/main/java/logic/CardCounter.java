package logic;

import java.util.Objects;

public class CardCounter {
	
	private UnitCard card;
	private int count;

	public CardCounter(UnitCard card, int count) {

		if (count < 0) count = 0;

		this.card = card;
		this.count = count;
	}

	public UnitCard getCard() {
		return card;
	}

	public void setCard(UnitCard card) {
		this.card = card;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if(count < 0) {
			count = 0;
		}
		this.count = count;
	}

	public String toString() {
		return  this.getCard() + " x " + this.getCount();
	}

}
