import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private Client client;
	private Player player;
	private int tableID;
	
	public GUI() throws IOException {
		this.tableID = -1;
		this.player = new Player("Player 1", "", 0);
		this.client = new Client(this);
		this.setTitle("G2 BlackJack");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		mainPanel.add(new LoginPanel(this), "login");
		mainPanel.add(new LobbyPanel(this), "lobby");
		mainPanel.add(new TablePanel(this), "table");

		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setTableID(int id) {
		this.tableID = id;
	}
	
	public int getTableID() {
		return this.tableID;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(String username, String password, int balance) {
		this.player = new Player(username, password, balance);
	}
	
	public void setDealer(String username, String password) {
		this.player = new Dealer(username, password);
	}
	
	public CardLayout getCardLayout() {
		return this.cardLayout;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public void showLobby() {
		mainPanel.remove(mainPanel.getComponent(1));
		mainPanel.add(new LobbyPanel(this), "lobby");
		cardLayout.show(mainPanel, "lobby");
	}
	
	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
	}
}
