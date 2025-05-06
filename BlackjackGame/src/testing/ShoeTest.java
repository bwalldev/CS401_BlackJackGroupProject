package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Shoe;

class ShoeTest {

	@Test
	void testGetNumDecks() {
		//make a shoe, size of 1 deck
		Shoe newShoe = new Shoe(1);
		//test that there is one deck
		assertEquals(1, newShoe.getNumDecks());
		//make a new shoe, size of 2 decks
		newShoe = new Shoe(2);
		//test for 2 decks
		assertEquals(2, newShoe.getNumDecks());
		//third shoe, 3 decks
		newShoe = new Shoe(3);
		//test for 3 decks
		assertEquals(3, newShoe.getNumDecks());
		//do the same test again to check that we get the same result
		assertEquals(3, newShoe.getNumDecks());
	}

	@Test
	void testGetCard() {
		//make a shoe
		Shoe newShoe = new Shoe(1);
		
		//test getting cards from the deck 52 times
		for(int i=0;i<52;i++) {
			assertNotNull(newShoe.getCard());
		}
		
		//make a new shoe with more decks
		newShoe = new Shoe(3);
		
		//test getting cards from the deck 52*3 times (52 cards in deck * 3 decks)
		for(int i=0;i<(52*3);i++) {
			assertNotNull(newShoe.getCard());
		}
	}

	@Test
	void testGetNumCards() {
		//make a shoe with one deck
		Shoe newShoe = new Shoe(1);
		//test that the shoe has one decks worth of cards
		assertEquals(52,newShoe.getNumCards());
		//make a shoe with 2 decks
		newShoe = new Shoe(2);
		//test for 2 decks worth of cards
		assertEquals(104,newShoe.getNumCards());
		//3 decks in shoe
		newShoe = new Shoe(3);
		//test for 3 decks worth of cards
		assertEquals(156,newShoe.getNumCards());
		//remove a card
		newShoe.getCard();
		//test that we get one less card
		assertEquals(155,newShoe.getNumCards());
	}
	
	@Test
	void testIsEmpty() {
		//make a shoe with one deck
		Shoe newShoe = new Shoe(1);
		//test that the deck is not empty
		assertFalse(newShoe.isEmpty());
		//get all the cards out of the deck
		for(int i=0;i<52;i++) {
			newShoe.getCard();
		}
		//test that the deck is empty
		assertTrue(newShoe.isEmpty());
		
		//same as above but with 2 decks
		newShoe = new Shoe(2);
		//test that the deck is not empty
		assertFalse(newShoe.isEmpty());
		//get all the cards out of the deck
		for(int i=0;i<(52*2);i++) {
			newShoe.getCard();
		}
		//test that the deck is empty
		assertTrue(newShoe.isEmpty());
		
		//same as above but with 3 decks
		newShoe = new Shoe(3);
		//test that the deck is not empty
		assertFalse(newShoe.isEmpty());
		//get all the cards out of the deck
		for(int i=0;i<(52*3);i++) {
			newShoe.getCard();
		}
		//test that the deck is empty
		assertTrue(newShoe.isEmpty());
		
	}

}
