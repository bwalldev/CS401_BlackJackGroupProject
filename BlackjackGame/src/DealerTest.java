
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {
	private Dealer dealer;
	private Table table;
	private Player player;
	private Card card1, card2, card3;
	private Suit suit;
	private Symbol symbol;
	
	@BeforeEach
	void setUp() {
		dealer = new Dealer("dealer1", "password");
		table = new Table();
		player = new Player("alice","Password!", 1000);
		table.addPlayer(player);
		table.setDealer(dealer);
		
		card1 = new Card(Suit.HEART, Symbol.ACE);
		card2 = new Card(Suit.CLOVER, Symbol.FIVE);
		card3 = new Card(Suit.DIAMOND, Symbol.KING);
	}
	
	@Test
	void testDealerConstructor() {
		assertEquals(0, dealer.getBalance());
		assertEquals("dealer1", dealer.getUsername());
	}
	
	@Test
	void testGetHandValue() {
		dealer.setHand(card1, card2); // = ACE (11), card2 = FIVE(5)
		assertEquals(16, dealer.getHandValue());
	}
	
	@Test
	void testHitPlayer() {
		dealer.hitPlayer(player, card3);
		assertTrue(player.getHand().getCards().contains(card3));
	}
	
	@Test
	void testStartGameDealsCards() {
		dealer.startGame(table);
		
		for (Player p: table.getPlayers()) {
			assertEquals(2, p.getHand().getCards().size(), "Player has 2 cards");
		}
		assertEquals(2, dealer.getHand().getCards().size(), "Dealer has 2 cards");
	}
	
	@Test
	void testClearDealerHand() {
		dealer.setHand(card1, card2);
		dealer.clearDealerHand();
		assertEquals(0, dealer.getHand().getCards().size(), "Dealer's hand is empty");
	}
}
