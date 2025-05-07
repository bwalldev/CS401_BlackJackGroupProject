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
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class LoginPanel extends JPanel {
	private BufferedImage background;

	public LoginPanel(GUI gui) {
		try {
			background = ImageIO.read(getClass().getResource("background.jpg")); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		setOpaque(false); // allow background to show through
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Welcome to G2 BlackJack!  ", JLabel.CENTER);
		title.setFont(new Font("broadway", Font.BOLD, 35));
		title.setForeground(Color.white);
		title.setAlignmentX(CENTER_ALIGNMENT);

		JLabel subtitle = new JLabel("Login to start");
		subtitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		subtitle.setForeground(Color.WHITE);
		subtitle.setAlignmentX(CENTER_ALIGNMENT);

		JLabel usernameLabel = new JLabel("Enter Username: ");
		usernameLabel.setForeground(Color.WHITE);
		JTextField usernameField = new JTextField(10);
		usernameField.setMaximumSize(new Dimension(200, 25));
		usernameLabel.setAlignmentX(CENTER_ALIGNMENT);

		JLabel passwordLabel = new JLabel("Enter password: ");
		passwordLabel.setForeground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setMaximumSize(new Dimension(200, 25));
		passwordLabel.setAlignmentX(CENTER_ALIGNMENT);

		JButton loginButton = new JButton("Login");
		loginButton.setAlignmentX(CENTER_ALIGNMENT);

		loginButton.addActionListener(e -> {
			boolean successConnection = gui.getClient().connectToServer("134.154.38.70", 7777);

			if (!successConnection) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
			} else {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				gui.getClient().startListening();
				gui.getClient().sendLoginMessage(username, password);
			}
		});

		this.add(Box.createVerticalStrut(40));
		this.add(title);
		this.add(Box.createVerticalStrut(10));
		this.add(subtitle);
		this.add(Box.createVerticalStrut(30));
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(Box.createVerticalStrut(20));
		this.add(loginButton);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // smooth image scaling
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); // better quality rendering
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // smoother edges for shapes and text

			g2d.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			g2d.dispose();
		}
	}
}
