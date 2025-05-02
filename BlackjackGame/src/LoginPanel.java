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
	public LoginPanel(GUI gui) {
		this.setBackground(Color.GREEN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Welcome to G2 BlackJack!", JLabel.CENTER);
		title.setFont(new Font("broadway", Font.BOLD, 40));
		title.setForeground(Color.WHITE);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel subtitle = new JLabel("Login to start");
		subtitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		subtitle.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel usernameLabel = new JLabel("Enter Username: ");
		JTextField usernameField = new JTextField(10);
		usernameField.setMaximumSize(new Dimension(200, 25));
		usernameLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel passwordLabel = new JLabel("Enter password: ");
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setMaximumSize(new Dimension(200, 25));
		passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton loginButton = new JButton("Login");
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		
		loginButton.addActionListener( e -> {
			boolean successConnection = gui.getClient().connectToServer("localhost", 7777);
			
			if (successConnection == false) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				String loginStatus = gui.getClient().sendLoginMessage(username, password);
				
				JOptionPane.showMessageDialog(null, loginStatus, "Login", JOptionPane.INFORMATION_MESSAGE);
				
				if (loginStatus.equals("Login successful.")) {
					gui.setPlayer(username, password, 0);
					
					gui.getMainPanel().remove(gui.getMainPanel().getComponent(1));
					gui.getMainPanel().add(new LobbyPanel(gui), "lobby");
					
					gui.getCardLayout().show(gui.getMainPanel(), "lobby");
				}
				else if (loginStatus.equals("Dealer login successful.")) {
					gui.setDealer(username, password);
					
					gui.getMainPanel().remove(gui.getMainPanel().getComponent(1));
					gui.getMainPanel().add(new LobbyPanel(gui),  "lobby");
					
					gui.getCardLayout().show(gui.getMainPanel(), "lobby");
				}
			}
		});
		
		this.add(title);
		this.add(subtitle);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
	}
}
