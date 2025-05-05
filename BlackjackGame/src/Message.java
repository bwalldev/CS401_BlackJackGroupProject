import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int idCount = 0;
	private final int id;
	private final MessageType type;
	private final String text;
	private final String from;
	private final String username;
	private final String password;
	private final Date date;
	private final Card card;
	private final int balance;
	private final int tableID;
	
	public Message(MessageType type, String username, String password, int balance, String text, String from, Card card, int tableID) {
		this.id = ++Message.idCount;
		this.type = type;
		this.username = username;
		this.password = password;
		this.text = text;
		this.from = from;
		this.date = new Date();
		this.card = card;
		this.balance = balance;
		this.tableID = tableID;
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getBalance() {
	    return this.balance;
	}

	public int getTableID() {
	    return this.tableID;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public MessageType getType() {
		return this.type;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public Card getCard() {
		return this.card;
	}
}
