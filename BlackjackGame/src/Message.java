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
	
	public Message(MessageType type, String username, String password, String text, String from, Card card) {
		this.id = ++Message.idCount;
		this.type = type;
		this.username = username;
		this.password = password;
		this.text = text;
		this.from = from;
		this.date = new Date();
		this.card = card;
	}
	
	public int getID() {
		return this.id;
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
