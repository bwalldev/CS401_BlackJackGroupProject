import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LobbyPanel extends JPanel {
	public LobbyPanel(GUI gui, Client client) {
		this.setBackground(Color.GREEN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Lobby Menu!");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		this.add(title);
	}
}
