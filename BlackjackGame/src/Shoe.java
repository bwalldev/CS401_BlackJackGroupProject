import java.util.ArrayList;
import java.util.Collections;

public class Shoe extends Deck {
	private int numDecks;
	
	public Shoe(int numDecks) {
		this.numDecks = numDecks;
		this.cards = new ArrayList<Card>();
		
		for (int i = 0; i < this.numDecks; i++) {
			fillDeck();
		}
		
		Collections.shuffle(this.cards);
		
		this.empty = false;
	}
	
	public int getNumDecks() {
		return this.numDecks;
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
		
		this.empty = false;
	}
}
