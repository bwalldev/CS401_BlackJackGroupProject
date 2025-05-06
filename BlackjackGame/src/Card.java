import java.io.Serializable;

public class Card implements Serializable {
	private int value;
	private Suit suit;
	private Symbol symbol;
	
	public Card(Suit suit, Symbol symbol) {
		this.suit = suit;
		this.symbol = symbol;
		
		// Assigning the value of the Card based on it's Symbol
		switch(this.symbol) {
			case ACE:
				this.value = 11;
				break;
			case TWO:
				this.value = 2;
				break;
			case THREE:
				this.value = 3;
				break;
			case FOUR:
				this.value = 4;
				break;
			case FIVE:
				this.value = 5;
				break;
			case SIX:
				this.value = 6;
				break;
			case SEVEN:
				this.value = 7;
				break;
			case EIGHT:
				this.value = 8;
				break;
			case NINE:
				this.value = 9;
				break;
			case TEN:
				this.value = 10;
				break;
			case JACK:
				this.value = 10;
				break;
			case QUEEN:
				this.value = 10;
				break;
			case KING:
				this.value = 10;
				break;
			default:
				System.out.println("***ERROR: Card rank error");
		}
	}
	
	public int getValue() {
		return this.value;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Symbol getSymbol() {
		return this.symbol;
	}
}
