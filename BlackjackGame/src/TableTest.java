import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
	private Table table;
	private Player player1;
	private Player player2;
	private Dealer dealer;
	
	@BeforeEach
	void setUp();
		table = new Table();
		player1 = new Player("Alice");
		player2 = new Player("Bob");
		dealer - new Dealer("John");
	}
	
	@Test
	void testAddPlayer() {
		table.addPlayer(player1);
		assertEquals(1, table.getNumPlayers());
		assertTrue(table.getPlayers().contains(player1));
	}
	
	@Test
	void testAddPlayerExceedLimit() {
		for (int i = 0; i < table.getMaxPlayers() + i; i++) {
			table.addPlayer(new Player("Player" + i));
		}
		assertEquals(table.getMaxPlayers(), table.getNumPlayers());
	}
	
	@Test
	void testRemovePlayer() {
		table.addPlayer(player1);
		table.removePlayer(player1);
		assertEquals(0, table.getNumPlayers());
		assertFalse(table.getPlayers().contains(player1));
	}
	
	@Test
	void testSetAndGetDealer() {
		table.setDealer(dealer);
		assertEquals(dealer, table.getDealer());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(table.isEmpty());
		table.addPlayer(player1);
		assertFalse(table.isEmpty());
	}
	
	@Test
	void testGameNotNull() {
		assertNotNull(table.getGame());
	}
}
