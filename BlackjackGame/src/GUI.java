import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


public class GUI extends JFrame {
	private CardLayout cardLayout;
	private JPanel panel;
	private Client client;
	
	public GUI() {
		this.setTitle("Welcome to G2 BlackJack");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			client = new Client("localhost", 7777);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Could not connect.");
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
