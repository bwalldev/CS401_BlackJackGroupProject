import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI extends JFrame {
	private CardLayout cardLayout;
	private Client client;
	private JPanel panel;
	private Game game;
	private JTextArea messageArea;
	private JButton hitButton, stayButton, startButton;
	
	public GUI() {
		//create the window 
		this.setTitle("BlackJack Game"); //title
		this.setSize(800, 500); //size of panel 
		this.setLocationRelativeTo(null); //relative to change
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the window when you hit close
		this.setLayout(new BorderLayout());
		
		
			
			try {
				client = new Client("localhost", 7777);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Could not connect.");
			}
			
	
		
		//create message panel 
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		messageArea.setFont(new Font("Times New Roman", Font.PLAIN, 16)); //this is cool ngl
		JScrollPane scrollPane = new JScrollPane(messageArea);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		startButton = new JButton("Start");
		hitButton = new JButton("Hit");
		stayButton = new JButton("Stay");

		buttonPanel.add(startButton);
		buttonPanel.add(hitButton);
		buttonPanel.add(stayButton);

		add(buttonPanel, BorderLayout.SOUTH);

		game = new Game();

		startButton.addActionListener(e -> {
		    game.startGame();
		    appendMessage("BlackJack game has started.\n"); //Example for message class to send between server to client
		});
		startButton.addActionListener(e -> {
		    game.hit();
		    appendMessage("Player hits.\n"); //Example for message class to send between server to client
		});
		startButton.addActionListener(e -> {
		    game.stay();
		    appendMessage("Player stays.\n"); //Example for message class to send between server to client
		});
		
		setVisible(true);
	}
	private void appendMessage(String msg) { //Example for message class to send between server to client
		messageArea.append(msg);	 //can delete this method once message class works
	}	
	
	public static void main(String[] args) {
		
	}
}
