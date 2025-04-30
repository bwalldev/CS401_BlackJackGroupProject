import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
	private CardLayout cardLayout;
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
		
		//create message panel 
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		messageArea.setFont(new Font("Times New Roman", Font.PLAIN, 16)); //this is cool ngl
		JScrollPane scrollPane = new JScrollPane(messageArea);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
	}
}
