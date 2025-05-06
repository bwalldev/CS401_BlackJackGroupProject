
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class LobbyTest {
	private Lobby lobby;
	
	@BeforeEach
	void setUp() {
		lobby = new Lobby();
	}
	
	@Test
	void testAddTableWithinLimit() {
		lobby.addTable(new Dealer("dealer1", "pass1"));
        lobby.addTable(new Dealer("dealer2", "pass2"));
        lobby.addTable(new Dealer("dealer3", "pass3"));

        // Attempting to add more than the limit of 3
        lobby.addTable(new Dealer("dealer4", "pass4"));
		
		assertEquals(3, lobby.getTableCount(), "As long as you dont exceed (3)");
	}
	
	@Test
	void testRemoveTable() {
		 lobby.addTable(new Dealer("dealer1", "pass1"));
	     lobby.addTable(new Dealer("dealer2", "pass2"));
		
		ArrayList<Table> tables = lobby.getTables();
		assertEquals(2, tables.size());
		
		Table toRemove = tables.get(0);
		lobby.removeTable(toRemove);
		
		assertEquals(1, lobby.getTableCount(), "If you remove one table");
		assertFalse(lobby.getTables().contains(toRemove), "Removed table should be gone");
	}
	
	@Test
	void testRemoveTableThatDoesNotExist() {
		Table externalTable = new Table();
		 lobby.addTable(new Dealer("dealer1", "pass1"));
		
		int before = lobby.getTableCount();
		lobby.removeTable(externalTable);
		
		assertEquals(before, lobby.getTableCount(), "Removing a non existent table nothing changes");
	}
	
	@Test
	void testGetTablesReturnsCopy() {
	    lobby.addTable(new Dealer("dealer1", "pass1"));
		ArrayList<Table> retrieved = lobby.getTables();
		
		//clear the list 
		retrieved.clear();
		assertEquals(1, lobby.getTableCount(), "getTables() should return a copy, not the original list");
	}
}

