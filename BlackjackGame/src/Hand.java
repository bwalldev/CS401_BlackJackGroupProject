import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
	private List<Card> cards;
	private int handTotal;
	private boolean busted;
	private boolean hasBlackjack;
	
	public Hand() {
		this.cards = new ArrayList<Card>();
		this.handTotal = 0;
		this.busted = false;
		this.hasBlackjack = false;
	}
	
	public Hand(Card card1, Card card2) {
		this.cards = new ArrayList<Card>(Arrays.asList(card1, card2));
		this.handTotal = 0;
		this.busted = false;
		this.hasBlackjack = false;
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public int getHandTotal() {
		return this.handTotal;
	}
	
	public boolean isBusted() {
		return this.busted;
	}
	
	public boolean isBlackjack() {
		return this.hasBlackjack;
	}
}
