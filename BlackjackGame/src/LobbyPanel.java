import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LobbyPanel extends JPanel {
	private Image background;
	private GUI gui;
	
	public LobbyPanel(GUI gui) {
	    this.gui = gui;
	    
	    background = new ImageIcon(getClass().getResource("/background.jpg")).getImage();

        this.setOpaque(false);
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	    this.updatePanel();
	}

	
	/*
	public LobbyPanel(GUI gui) {
		this.setBackground(Color.GREEN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Table Lobby");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setAlignmentY(CENTER_ALIGNMENT);
		
		JButton lobbyButton1 = new JButton("Table 1");
		JButton lobbyButton2 = new JButton("Table 2");
		JButton lobbyButton3 = new JButton("Table 3");
		
		
		lobbyButton1.setAlignmentX(CENTER_ALIGNMENT);
		lobbyButton2.setAlignmentX(CENTER_ALIGNMENT);
		lobbyButton3.setAlignmentX(CENTER_ALIGNMENT);
		
		
		JButton createTableButton = new JButton("Create Table");
		
		createTableButton.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton logoutButton = new JButton("Logout");
		
		List<JButton> lobbyButtons = new ArrayList<>();
		lobbyButtons.add(lobbyButton1);
		lobbyButtons.add(lobbyButton2);
		lobbyButtons.add(lobbyButton3);
		
		this.add(title);
		
		if(!(gui.getPlayer() instanceof Dealer)) {
			if (gui.getClient().getLoggedIn()) {
			    int tableCount = gui.getClient().getTableCountMessage();
			
			    for (int i = 0; i < tableCount; i++) {
				    this.add(lobbyButtons.get(i));
			    }
			}
		}
		else {
			if (gui.getClient().getLoggedIn()) {
				this.add(createTableButton);
			}
		}
		
		this.add(Box.createVerticalGlue());
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.setOpaque(false);
		bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		
		bottomPanel.add(logoutButton);
		this.add(bottomPanel);
		
		logoutButton.addActionListener(e -> {
		    String username = gui.getPlayer().getUsername();
		    String password = gui.getPlayer().getPassword();
		    gui.getClient().sendLogoutMessage(username, password);
		});
		
		lobbyButton1.addActionListener(e -> {
			gui.getClient().sendJoinTableMessage(gui.getPlayer().getUsername(), 0);
			
		});
	}*/
	
	public void updatePanel() {
		this.removeAll();
		
		JLabel title = new JLabel("Table Lobby");
		title.setFont(new Font("broadway", Font.BOLD, 35));
		title.setForeground(Color.white);
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setAlignmentY(CENTER_ALIGNMENT);
		
		JButton lobbyButton1 = new JButton("Table 1");
		JButton lobbyButton2 = new JButton("Table 2");
		JButton lobbyButton3 = new JButton("Table 3");
		
		
		lobbyButton1.setAlignmentX(CENTER_ALIGNMENT);
		lobbyButton2.setAlignmentX(CENTER_ALIGNMENT);
		lobbyButton3.setAlignmentX(CENTER_ALIGNMENT);
		
		
		JButton createTableButton = new JButton("Create Table");
		
		createTableButton.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton logoutButton = new JButton("Logout");
		
		List<JButton> lobbyButtons = new ArrayList<>();
		lobbyButtons.add(lobbyButton1);
		lobbyButtons.add(lobbyButton2);
		lobbyButtons.add(lobbyButton3);
		
		this.add(Box.createVerticalStrut(40));
		this.add(title);
		
		if(!(gui.getPlayer() instanceof Dealer)) {
			if (gui.getClient().getLoggedIn()) {
			    int tableCount = gui.getClient().getTableCountMessage();
			
			    for (int i = 0; i < tableCount; i++) {
			    	this.add(Box.createVerticalStrut(30));
				    this.add(lobbyButtons.get(i));
			    }
			}
		}
		else {
			if (gui.getClient().getLoggedIn()) {
				this.add(Box.createVerticalStrut(30));
				this.add(createTableButton);
			}
		}
		
		this.add(Box.createVerticalGlue());
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.setOpaque(false);
		bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		
		bottomPanel.add(logoutButton);
		this.add(bottomPanel);
		
		logoutButton.addActionListener(e -> {
		    String username = gui.getPlayer().getUsername();
		    String password = gui.getPlayer().getPassword();
		    gui.getClient().sendLogoutMessage(username, password);
		});
		
		lobbyButton1.addActionListener(e -> {
			gui.getClient().sendJoinTableMessage(gui.getPlayer().getUsername(), 0);
		});
		
		lobbyButton2.addActionListener(e -> {
			gui.getClient().sendJoinTableMessage(gui.getPlayer().getUsername(), 1);
		});
		
		lobbyButton3.addActionListener(e -> {
			gui.getClient().sendJoinTableMessage(gui.getPlayer().getUsername(), 2);
		});
		
		createTableButton.addActionListener(e -> {
		    String username = gui.getPlayer().getUsername();
		    gui.getClient().sendCreateTableMessage(username);
		});
		
		this.revalidate();
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (background != null) {
	        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	    }
	}

}
