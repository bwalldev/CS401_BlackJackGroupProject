package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Hand;
import main.Card;
import main.Suit;
import main.Symbol;
import main.Shoe;
class HandTest {

	@Test
	void testGetCards() {
		//setup
		Hand newHand = new Hand();
		Card newCard = new Card(Suit.SPADE,Symbol.TWO);
		Card newCard2 = new Card(Suit.CLOVER,Symbol.FIVE);
		//test that hand is empty
		assertTrue(newHand.getCards().isEmpty());
		//add a card to the hand
		newHand.addCard(newCard);
		//check that the card is in the hand
		assertEquals(newCard,newHand.getCards().get(0));
		//add another card to the hand
		newHand.addCard(newCard2);
		//check that the card is in the hand
		assertEquals(newCard2,newHand.getCards().get(1));
		//check the number of cards in the hand
		assertEquals(2,newHand.getCards().size());
		//test alternate constuctor
		newHand = new Hand(newCard,newCard2);
		//test that hand isnt empty
		assertFalse(newHand.getCards().isEmpty());
		//check the number of cards in the hand
		assertEquals(2,newHand.getCards().size());
		//check that our two cards are in the hand in the right spots
		assertEquals(newCard,newHand.getCards().get(0));
		assertEquals(newCard2,newHand.getCards().get(1));
	}

	@Test
	void testGetHandTotal() {
		//setup
		Hand newHand = new Hand();
		Card newCard = new Card(Suit.SPADE,Symbol.TWO);
		Card newCard2 = new Card(Suit.CLOVER,Symbol.FIVE);
		Card newCard3 = new Card(Suit.CLOVER,Symbol.ACE);
		//test that the hand had no value
		assertEquals(0,newHand.getHandTotal());
		//add a card to hand
		newHand.addCard(newCard);
		//check that the total updated
		assertEquals(2,newHand.getHandTotal());
		//add another card to hand
		newHand.addCard(newCard2);
		//check that the total updated
		assertEquals(7,newHand.getHandTotal());
		//add an ace to the hand
		newHand.addCard(newCard3);
		//check that the total updated
		assertEquals(18,newHand.getHandTotal());
		//add another 5 to the hand, which will change the ace from 11 to 1
		newHand.addCard(newCard2);
		//check that the total updated and the ace isn't 11
		assertEquals(13,newHand.getHandTotal());
	}

	@Test
	void testIsBusted() {
		//setup
		Card newCard = new Card(Suit.SPADE,Symbol.TWO);
		Card newCard2 = new Card(Suit.CLOVER,Symbol.TEN);
		Hand newHand = new Hand(newCard,newCard2);
		//our default hand is 12 so we shouldnt bust
		assertFalse(newHand.isBusted());
		//make our hand 21
		newHand.addCard(new Card(Suit.DIAMOND,Symbol.NINE));
		//we still shouldn't bust
		assertFalse(newHand.isBusted());
		//put our hand over 21
		newHand.addCard(newCard);
		//we should now bust
		assertTrue(newHand.isBusted());
	}

	@Test
	void testIsBlackjack() {
		//setup
		Card newCard = new Card(Suit.SPADE,Symbol.TWO);
		Card newCard2 = new Card(Suit.CLOVER,Symbol.TEN);
		Hand newHand = new Hand(newCard,newCard2);
		//our default hand is 12 so we shouldnt have blackjack
		assertFalse(newHand.isBlackjack());
		//make our hand 21
		newHand.addCard(new Card(Suit.DIAMOND,Symbol.NINE));
		//we should now have blackjack
		assertTrue(newHand.isBlackjack());
		//put our hand over 21
		newHand.addCard(newCard);
		//we should no longer have blackjack
		assertFalse(newHand.isBlackjack());
	}

	@Test
	void testAddCard() {
		Shoe newShoe = new Shoe(1);
		Hand newHand = new Hand();
		
		//add all the possible cards from a deck into the hand. the hand size should increase for each card added
		for(int i=0;i<52;i++) {
			newHand.addCard(newShoe.getCard());
			assertEquals((i+1),newHand.getCards().size());
		}
	}

	@Test
	void testClearHand() {
		Hand newHand = new Hand();
		Card newCard = new Card(Suit.SPADE,Symbol.TWO);
		Card newCard2 = new Card(Suit.CLOVER,Symbol.TEN);
		Shoe newShoe = new Shoe(1);
		
		//test that there are no cards in hand
		assertEquals(0,newHand.getCards().size());
		//add a card
		newHand.addCard(newCard);
		//show that hand has a card in it
		assertEquals(1,newHand.getCards().size());
		//clear the hand
		newHand.clearHand();
		//hand should now be empty
		assertEquals(0,newHand.getCards().size());
		//make a new hand with 2 cards
		newHand = new Hand(newCard,newCard2);
		//show that hand has 2 cards in it
		assertEquals(2,newHand.getCards().size());
		//clear the hand
		newHand.clearHand();
		//hand should now be empty
		assertEquals(0,newHand.getCards().size());
		//fill the hand with all possible cards
		for(int i=0;i<52;i++) {
			newHand.addCard(newShoe.getCard());
		}
		//show that the hand has all 52 cards in it
		assertEquals(52,newHand.getCards().size());
		//clear the hand
		newHand.clearHand();
		//hand should now be empty
		assertEquals(0,newHand.getCards().size());
	}
}
