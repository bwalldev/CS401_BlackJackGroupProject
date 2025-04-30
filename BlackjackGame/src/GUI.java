import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private CardLayout cardLayout;
	private JPanel panel;
	
	public GUI() {
		this.setTitle("Welcome to G2 BlackJack");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
