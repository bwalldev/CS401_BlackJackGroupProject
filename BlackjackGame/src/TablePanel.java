import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JPanel {
	private GUI gui;
	JLabel tableIDLabel;
	private List<String> pendingHitRequests = new ArrayList<>();
	private JPanel hitRequestPanel = new JPanel();
	private BufferedImage background;
	
    public TablePanel(GUI gui) {
       this.gui = gui; 
   		try {
   			background = ImageIO.read(getClass().getResource("backgound_1.jpg")); 
   		} catch (IOException e) {
   			e.printStackTrace();
   		}

   		setOpaque(false); 
       this.setLayout(new BorderLayout());
       hitRequestPanel.setLayout(new BoxLayout(hitRequestPanel, BoxLayout.Y_AXIS));
       hitRequestPanel.setBackground(new Color(0, 51, 0));
       this.tableIDLabel = new JLabel("");
       
       this.add(tableIDLabel);
       
       updatePanel();
    }
    
    public void updatePanel() {
    	this.removeAll();
    	this.setLayout(new BorderLayout());
    	
    	this.tableIDLabel.setText("Table ID: " + gui.getTableID());
    	this.add(this.tableIDLabel, BorderLayout.PAGE_START);
    	tableIDLabel.setFont(new Font("Ariel", Font.BOLD, 15));
    	tableIDLabel.setForeground(Color.black);
    	
    	JPanel playersPanel = new JPanel();
    	playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
    	playersPanel.setPreferredSize(new Dimension(120, 200));
    	playersPanel.setOpaque(true);
    	playersPanel.setBackground(Color.GRAY);
    	
    	JLabel playersLabel = new JLabel("Players: ");
    	playersLabel.setFont(new Font("Ariel", Font.BOLD, 20));
    	playersLabel.setForeground(Color.WHITE);
    	
    	playersPanel.add(playersLabel);
    	
    	JPanel handPanel = new JPanel();
    	handPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	handPanel.setBackground(new Color(0, 61, 2));
    	
    	JLabel yourHandLabel = new JLabel("Your Hand: " + gui.getPlayer().getHandValue());
    	yourHandLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
    	yourHandLabel.setForeground(Color.WHITE);
    	
    	handPanel.add(yourHandLabel, BorderLayout.PAGE_END);
    	
    	JPanel menuPanel = new JPanel();
    	menuPanel.setLayout(new GridLayout(6, 1, 10, 13));
    	menuPanel.setPreferredSize(new Dimension(120, HEIGHT));
    	menuPanel.setOpaque(false);
    	
    	if (!(gui.getPlayer() instanceof Dealer)) {
    	    JLabel balanceLabel = new JLabel("Balance: $" + gui.getPlayer().getBalance());
    	    balanceLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    	
    	    menuPanel.add(balanceLabel);
    	}
    	
    	JButton hitButton = new JButton("Hit");
    	hitButton.setPreferredSize(new Dimension(120, 30));
    	
    	JButton stayButton = new JButton("Stay");
    	stayButton.setPreferredSize(new Dimension(120, 30));
    	
    	//JButton clearButton = new JButton("Clear Hands");
    	//clearButton.setPreferredSize(new Dimension(120, 30));
    	
    	menuPanel.add(hitButton);
    	menuPanel.add(stayButton);
    	//menuPanel.add(clearButton);

    	JButton leaveButton;
    	
    	// Checking which version of the leave table button to display depending if it's a Player or Dealer viewing the panel
    	if (gui.getPlayer() instanceof Dealer) {
    	    // Show Close Table only for Dealer
    	    leaveButton = new JButton("Close Table");
    	    
    	    leaveButton.addActionListener(e -> {
    	        String username = gui.getPlayer().getUsername();
    	        int tableID = gui.getTableID();
    	        gui.getClient().sendCloseTableMessage(username, tableID);
    	    });
    	} else {
    	    // Show Leave Table only for Player
    	    leaveButton = new JButton("Leave Table");
    	    
    	    leaveButton.addActionListener(e -> {
    	        String username = gui.getPlayer().getUsername();
    	        int tableID = gui.getTableID();
    	        gui.getClient().sendLeaveTableMessage(username, tableID);
    	    });
    	}
    	
    	this.add(hitRequestPanel, BorderLayout.CENTER);
    	updateHitRequestPanel();
    	
    	hitButton.addActionListener(e -> {
    		String username = gui.getPlayer().getUsername();
    		int tableID = gui.getTableID();
    		gui.getClient().sendRequestHitMessage(username, tableID);
    	});
    	
    	stayButton.addActionListener(e -> {
    		
    	});
    	
    	/*clearButton.addActionListener(e -> {
    		gui.getClient().sendClearHandMessage(gui.getPlayer().getUsername(), gui.getTableID());
    		gui.getPlayer().clearPlayerHand();
    		
    		//updatePanel();
    	});*/
    	
    	menuPanel.add(leaveButton);
    	
    	this.add(handPanel, BorderLayout.PAGE_END);
    	this.add(menuPanel, BorderLayout.EAST);
    	this.add(playersPanel, BorderLayout.WEST);
    	this.revalidate();
    	this.repaint();
    }
    
    
    public void addPendingHitRequest(String playerName) {
        pendingHitRequests.add(playerName);
        updateHitRequestPanel();
    }
    
    public void addCardToPlayer(Card card) {
    	 gui.getPlayer().addCardToHand(card);

    	   
    	 this.removeAll();
    	 updatePanel();
    }
    
    public void updateHitRequestPanel() {
        hitRequestPanel.removeAll();

        for (int i = 0; i < pendingHitRequests.size(); i++) {
        	String playerName = pendingHitRequests.get(i);
        	
            JButton dealCardButton = new JButton("Give card to " + playerName);
            int index = i;
            dealCardButton.addActionListener(e -> {
                if (gui.getPlayer() instanceof Dealer && gui.getPlayer().getHandValue() < 17) {
	                Card card = gui.getTable().getGame().getShoe().getCard();
	                gui.getClient().sendHitMessage(playerName, card, gui.getTableID());
	
	                // Remove from pending list
	                pendingHitRequests.remove(index);
	                updateHitRequestPanel();
                }
                else if(gui.getPlayer() instanceof Player) {
                	Card card = gui.getTable().getGame().getShoe().getCard();
	                gui.getClient().sendHitMessage(playerName, card, gui.getTableID());
	
	                // Remove from pending list
	                pendingHitRequests.remove(index);
	                updateHitRequestPanel();
                }
            });
            hitRequestPanel.add(dealCardButton);
        }

        hitRequestPanel.revalidate();
        hitRequestPanel.repaint();
    }
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
	
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		}
	}
    
}