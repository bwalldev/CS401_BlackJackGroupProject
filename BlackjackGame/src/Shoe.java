import java.util.Collections;

public class Shoe extends Deck {

	protected void resetDeck() {
		//clear the deck
		this.cards.clear();
		//call helper function to fill the deck full of cards three times
		for(int i=0;i<3;i++) {
			fillDeck();	
		}
		//randomly shuffle the items in the deck
		Collections.shuffle(this.cards);
	}
}
