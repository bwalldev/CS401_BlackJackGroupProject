import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LobbyPanel extends JPanel {
	public LobbyPanel(GUI gui) {
		this.setBackground(Color.GREEN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Welcome to the lobby " + gui.getPlayer().getUsername());
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JButton lobbyButton1 = new JButton("Table 1");
		JButton lobbyButton2 = new JButton("Table 2");
		JButton lobbyButton3 = new JButton("Table 3");
		
		JButton createtableButton = new JButton("Create Table");
		
		List<JButton> lobbyButtons = new ArrayList<>();
		lobbyButtons.add(lobbyButton1);
		lobbyButtons.add(lobbyButton2);
		lobbyButtons.add(lobbyButton3);
		
		this.add(title);
		
		if(!(gui.getPlayer() instanceof Player)) {
			if (gui.getClient().getLoggedIn()) {
			    int tableCount = gui.getClient().getTableCountMessage();
			
			    for (int i = 0; i < tableCount; i++) {
				    this.add(lobbyButtons.get(i));
			    }
			}
		}
		else {
			if (gui.getClient().getLoggedIn()) {
				this.add(createtableButton);
			}
		}
	}
}
