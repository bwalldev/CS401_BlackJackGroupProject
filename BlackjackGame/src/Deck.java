import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	protected List<Card> cards;
	protected boolean empty;
	
	// Constructor
	public Deck() {
		this.cards = new ArrayList<Card>();
		
		fillDeck();
		
		this.empty = false;
	}
	
	public Card getCard() {
		Card tempCard = this.cards.getLast();
		
		this.cards.removeLast();
		
		if (this.cards.size() == 0)
			this.empty = true;
		
		return tempCard;
	}
	
	public int getNumCards() {
		return this.cards.size();
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	protected void fillDeck() {
		// Creates every card with every symbol variant
		for(Suit suit : Suit.values()) {
			for(Symbol symbol : Symbol.values()) {
				this.cards.add(new Card(suit, symbol));
			}
		}
	}
}