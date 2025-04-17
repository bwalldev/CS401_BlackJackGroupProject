import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static int idCount = 0;
	private final int id;
	private final String type;
	private final String text;
	private final Date date;
	
	public Message(String text, String type) {
		this.id = ++this.idCount;
		this.type = type;
		this.text = text;
		this.date = new Date();
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Date getDate() {
		return this.date;
	}
}
