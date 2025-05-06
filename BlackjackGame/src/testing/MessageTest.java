package testing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.junit.*;
import main.Message;
import main.MessageType;
import main.Shoe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MessageTest {

	@Test
	void a_testGetID() {
		//make a message with as few fields as possible
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that this is message 1
		assertEquals(1,newMessage.getID());
		//make another message, test that this is message 2
		newMessage = new Message(null, null, null, 0, null, null, null, 0);
		assertEquals(2,newMessage.getID());
		//make another message, test that this is message 3
		newMessage = new Message(MessageType.WIN, null, null, 0, null, null, null, 0);
		assertEquals(3,newMessage.getID());
		//test that we get the same result
		assertEquals(3,newMessage.getID());
		//make another message with different data types.
		newMessage = new Message(MessageType.BUSTED, null, null, 1, null, null, null, 1);
		//test that its message 4
		assertEquals(4,newMessage.getID());
	}

	@Test
	void testGetBalance() {
		//make a message with as few fields as possible
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that our balance is zero
		assertEquals(0,newMessage.getBalance());
		//make a message with a different balance
		newMessage = new Message(null, null, null, 500, null, null, null, 0);
		//test that our balance is what we set it to
		assertEquals(500,newMessage.getBalance());
		//make another message with a different balance
		newMessage = new Message(null, null, null, 1, null, null, null, 0);
		//test that our balance is what we set it to
		assertEquals(1,newMessage.getBalance());
		//test the same test again
		assertEquals(1,newMessage.getBalance());
	}

	@Test
	void testGetUsername() {
		//make a message with as few fields as possible
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that our username is null because we didnt set it
		assertNull(newMessage.getUsername());
		//test with a username this time
		newMessage = new Message(null, "name", null, 0, null, null, null, 0);
		//test that our username is "name"
		assertEquals("name",newMessage.getUsername());
		//test with a different name
		newMessage = new Message(null, "BillyBobJoe", null, 0, null, null, null, 0);
		//test that our username is what it was set to
		assertEquals("BillyBobJoe",newMessage.getUsername());
		//test the same test again
		assertEquals("BillyBobJoe",newMessage.getUsername());
	}

	@Test
	void testGetPassword() {
		//make a message with as few fields as possible
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that our password is null because we didnt set it
		assertNull(newMessage.getPassword());
		//test with a password this time
		newMessage = new Message(null, null, "pass", 0, null, null, null, 0);
		//test that our password is "pass"
		assertEquals("pass",newMessage.getPassword());
		//test with a different password
		newMessage = new Message(null, null, "thisismysecretpassword", 0, null, null, null, 0);
		//test that our password is what it was set to
		assertEquals("thisismysecretpassword",newMessage.getPassword());
		//test the same test again
		assertEquals("thisismysecretpassword",newMessage.getPassword());
	}

	@Test
	void testGetType() {
		//make a message with as few fields as possible
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that our type is null because we didnt set it
		assertNull(newMessage.getType());
		//make a for loop that iterates through each message type and tests that the type is set correctly at each
		for(MessageType types : MessageType.values()) {
			newMessage = new Message(types, null, null, 0, null, null, null, 0);
			assertEquals(types, newMessage.getType());
		}
	}

	@Test
	void testGetText() {
		//make a message with as few fields as possible
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that our text is null because we didnt set it
		assertNull(newMessage.getText());
		//make a message with text in it
		newMessage = new Message(null, null, null, 0, "This Is The Text Field!", null, null, 0);
		//check that the text was set
		assertEquals("This Is The Text Field!", newMessage.getText());
		//make a message with different text in it
		newMessage = new Message(null, null, null, 0, "insert test text here", null, null, 0);
		//check that the text was set
		assertEquals("insert test text here", newMessage.getText());
		//do the same test a seconed time
		assertEquals("insert test text here", newMessage.getText());
	}

	@Test
	void testGetCard() {
		//make a deck and a message for testing
		Shoe newShoe = new Shoe(1);
		Message newMessage = new Message(null, null, null, 0, null, null, null, 0);
		//test that our message doesn't contain a card
		assertNull(newMessage.getCard());
		//test that for each card in a deck a message can be generated with a card.
		for(int i=0;i<52;i++) {
			newMessage = new Message(null, null, null, 0, null, null, newShoe.getCard(), 0);
			assertNotNull(newMessage.getCard());
		}
	}

}
