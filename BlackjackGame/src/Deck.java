import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	protected List<Card> cards;
	
	// Constructor
	public Deck() {
		this.cards = new ArrayList<Card>();
		
		fillDeck();
	}
	
	public int getNumCards() {
		return this.cards.size();
	}
	
	public boolean isEmpty() {
		if (this.cards.size() > 0)
			return false;
		else
			return true;
	}
	
	protected void fillDeck() {
		// Creates every card with every symbol variant
		for(Suit suit : Suit.values()) {
			for(Symbol symbol : Symbol.values())
				this.cards.add(new Card(suit, symbol));
		}
	}
}