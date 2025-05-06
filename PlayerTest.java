import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
	private Player player;
	private Card card1;
	private Card card2;
	private Card extraCard;
	
	@BeforeEach
	void setUp() {
		player = new Player("BrvNwWrld", "NmlFrm", 5000); //initialize balance of 5000
		card1 = new  Card(Suit.HEART, Symbol.FIVE);
		card2 = new  Card(Suit.CLOVER, Symbol.SIX);
		extraCard = new  Card(Suit.DIAMOND, Symbol.TWO);
	}
	
	@Test
	void testConstructorAndGetters() {
		assertEquals("BrvNwWrld", player.getUsername());
		assertEquals("NmlFrm", player.getPassword());
		assertEquals(5000, player.getBalance());
		assertNotNull(player.getHand());
		assertEquals(-1, player.getTableID());
	}
	
	//testing getters and setters for ID
	@Test
	void testSetAndGetTableID() {
		player.setTableID(3);
		assertEquals(3, player.getTableID());
	}
	
	//k
	@Test
	void testSetBalanceValid() {
		player.setBalance(3000);
		assertEquals(3000, player.getBalance());
	}
	
	@Test
	void testSetBalanceInvalidNegative() {
		player.setBalance(-1000);
		assertEquals(5000, player.getBalance()); //should not change
	}
	
	@Test
	void testSetCurrBetWithinBalance() {
		player.setCurrBet(200);
		assertEquals(200, player.getCurrBet());
	}
	
	@Test
	void testSetCurrBetExceedingBalance() {
		player.setCurrBet(60000);
		assertEquals)0, player.getCurrBet()); //should remain 0
	}
	
	@Test
	void testWithdrawalMoney() {
		player.withdrawalMoney(1000);
		assertEquals(1000), player.getBalance();
	}
	
	@Test
	void testWithdrawalMoneyTooMuch() {
		player.withdrawalMoney(10000);
		assertEquals(5000, player.getBalance()); //should remain unchanged
	}
	
	@Test
	void testDepositMoneyValid() {
		player.depositMoney(250);
		assertEquals(750, player.getBalance());
	}
	
	@Test 
	void setHandAndGetHandValue() {
		player.setHand(card1, card2); //5+6 = 11
		assertEquals(11, player.getHandValue());
	}
	
	@Test
	void testAddCardToHand() {
		player.setHand(card1, card2);
		player.addCardtoHand(extraCard);
		assertEquals(3, player.getHand().getCards().size());
		assertEquals(22, player.getHandValue());
	}
	
	@Test
	void testStayAndHasStayed() {
		assertFalse(player.hasTStayed());
		player.stay();
		assertTrue(player.hasStayed());
	}
	
	@Test
	void testClearPlayerHand() {
		player.setHand(card1, card20;
		player.stay();
		player.clearPlayerHand();
		assertEquals(0, player.getHand().getCards.size());
		assertFalse(player.hasStayed());
	}
	
	@Test
	void testUniqueIDs() {
		Player p2 = new Player("Another", "Password", 200);
		assertNotEquals(players.getId(), p2.getID());
	}
}
