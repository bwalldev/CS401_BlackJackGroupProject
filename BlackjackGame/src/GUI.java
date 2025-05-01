import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private Client client;
	
	public GUI() throws IOException {
		this.client = new Client();
		this.setTitle("Welcome to G2 BlackJack");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		mainPanel.add(new LoginPanel(this, this.client), "login");
		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
	}
}
