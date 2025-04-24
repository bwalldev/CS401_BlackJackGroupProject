import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		resetDeck();
	}
	
	public void reshuffle() {
		resetDeck();
	}
	
	public Card getCard() {
		Card tempCard = this.cards.getLast();
		this.cards.removeLast();
		return tempCard;
		
	}
	public int getDeckSize() {
		return this.cards.size();
	}
	
	//private helper function
	private void resetDeck() {
		this.cards.clear();
		
		Suit mySuit = Suit.SPADE;
		Symbol mySymbol = Symbol.ACE;
		
		//for the number of different suites
		for(int i=0;i<4;i++) {
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
			//for the number of different symbols
			for(int ii=0;ii<13;ii++) {
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
				this.cards.add(new Card(mySuit,mySymbol));
			}
		}
		
		//this randomly shuffles the items in a list
		Collections.shuffle(this.cards);
	}
}
