import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	public LoginPanel(GUI gui, Client client) {
		this.setBackground(Color.GREEN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Welcome to G2 Blackjack!");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel subtitle = new JLabel("Login");
		subtitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel usernameLabel = new JLabel("Enter Username: ");
		JTextField usernameField = new JTextField(10);
		
		JLabel passwordLabel = new JLabel("Enter password: ");
		JPasswordField passwordField = new JPasswordField(10);
		
		JButton loginButton = new JButton("Login");
		
		loginButton.addActionListener( e -> {
			boolean successConnection = client.connectToServer("localhost", 7777);
			
			if (successConnection == false) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				String loginStatus = client.sendLoginMessage(username, password);
				
				JOptionPane.showMessageDialog(null, loginStatus, "Login", JOptionPane.INFORMATION_MESSAGE);
				
				if (loginStatus.equals("Login successful.")) {
					gui.getCardLayout().show(gui.getMainPanel(), "lobby");
				}
			}
		});
		
		this.add(title);
		this.add(subtitle);
		this.add(Box.createRigidArea(new Dimension(0,300)));
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
	}
}
