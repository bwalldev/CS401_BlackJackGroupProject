package main;
import java.util.ArrayList;
import java.util.Collections;

public class Shoe extends Deck {
	private int numDecks;
	
	public Shoe(int numDecks) {
		this.numDecks = numDecks;
		this.cards = new ArrayList<Card>();
		
		for (int i = 0; i < this.numDecks; i++)
			fillDeck();
		
		Collections.shuffle(this.cards);
	}
	
	public int getNumDecks() {
		return this.numDecks;
	}
	
	public Card getCard() {
		Card drawCard = null;
		
		if (this.cards.size() == 0)
			reset();
		
		drawCard = this.cards.removeLast();
		
		return drawCard;
	}

	protected void reset() {
		//clear the deck
		this.cards.clear();
		
		//call helper function to fill the deck full of cards three times
		for(int i = 0; i < this.numDecks; i++) {
			fillDeck();	
		}
		
		//randomly shuffle the items in the deck
		Collections.shuffle(this.cards);
	}
}
