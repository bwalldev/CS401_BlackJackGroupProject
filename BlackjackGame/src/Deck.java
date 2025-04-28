import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	protected List<Card> cards;
	protected boolean empty;
	
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
		Suit mySuit = null;
		Symbol mySymbol = null;
		
		//loop for the number of different suites
		for(int i = 0; i < Suit.values().length; i++) {
			//set up our suit based on the loop counter
			switch(i) {
				case(0):
					mySuit = Suit.SPADE;
					break;
				case(1):
					mySuit = Suit.CLOVER;
					break;
				case(2):
					mySuit = Suit.HEART;
					break;
				case(3):
					mySuit = Suit.DIAMOND;
					break;
				default:
					System.out.println("suit not properly determined in deck");
			}
			
			//loop for the number of different symbols
			for(int ii = 0; ii < Symbol.values().length; ii++) {
				switch(ii) {
					case(0):
						mySymbol = Symbol.ACE;
						break;
					case(1):
						mySymbol = Symbol.TWO;
						break;
					case(2):
						mySymbol = Symbol.THREE;
						break;
					case(3):
						mySymbol = Symbol.FOUR;
						break;
					case(4):
						mySymbol = Symbol.FIVE;
						break;
					case(5):
						mySymbol = Symbol.SIX;
						break;
					case(6):
						mySymbol = Symbol.SEVEN;
						break;
					case(7):
						mySymbol = Symbol.EIGHT;
						break;
					case(8):
						mySymbol = Symbol.NINE;
						break;
					case(9):
						mySymbol = Symbol.TEN;
						break;
					case(10):
						mySymbol = Symbol.JACK;
						break;
					case(11):
						mySymbol = Symbol.QUEEN;
						break;
					case(12):
						mySymbol = Symbol.KING;
						break;
					default:
						System.out.println("symbol not properly determined in deck");
				}
				
				//add a new card to the list based on the suit, symbol pair
				this.cards.add(new Card(mySuit, mySymbol));
			}
		}
	}
}