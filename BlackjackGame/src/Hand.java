import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
	private List<Card> cards;
	private int handTotal;
	private boolean busted;
	private boolean hasBlackjack;
	private int aceAsEleven;
	
	public Hand() {
		this.cards = new ArrayList<Card>();
		this.handTotal = 0;
		this.busted = false;
		this.hasBlackjack = false;
		this.aceAsEleven = 0;
	}
	
	public Hand(Card card1, Card card2) {
		this.cards = new ArrayList<Card>(Arrays.asList(card1, card2));
		this.handTotal = card1.getValue() + card2.getValue();
		this.busted = false;
		this.aceAsEleven = 0;
		
		
		// Treats ace as Eleven
		if (card1.getValue() == 11) this.aceAsEleven++;
		if (card2.getValue() == 11) this.aceAsEleven++;
		
		// If hand total is over 21, treat ace as 1 so long as there is ace in hand
		while (this.handTotal > 21 && this.aceAsEleven > 0) {
			this.handTotal -= 10;
			this.aceAsEleven--;
		}
		
		// Determining if the Player's Hand is a Blackjack
		if (this.handTotal == 21)
			this.hasBlackjack = true;
		else
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
	
	public void addCard(Card card) {
		if (card != null) {
			this.cards.add(card);
			this.handTotal += card.getValue();
			
			// If the card is Ace we track it
			if (card.getValue() == 11) {
				this.aceAsEleven++;
			}
			
			// When adding card, if total is over 21 and have an ace, we treat ace as 1
			while (this.handTotal > 21 && this.aceAsEleven > 0) {
				this.handTotal -= 10;
				this.aceAsEleven--;
			}
			
			if (this.handTotal == 21) {
				this.hasBlackjack = true;
			}
			if (this.handTotal > 21) {
				this.busted = true;
				this.hasBlackjack = false;
			}
		}
	}
	
	public void clearHand() {
		this.cards.clear();
		this.handTotal = 0;
		this.busted = false;
		this.hasBlackjack = false;
		this.aceAsEleven = 0;
	}
}
