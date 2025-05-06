import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablePanel extends JPanel {
	private GUI gui;
	JLabel tableIDLabel;
	
    public TablePanel(GUI gui) {
    	this.gui = gui;
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());

       this.tableIDLabel = new JLabel("");
       
       this.add(tableIDLabel);
       
       updatePanel();
    }
    
    public void updatePanel() {
    	this.removeAll();
    	this.setLayout(new BorderLayout());
    	
    	this.tableIDLabel.setText("Table ID: " + gui.getTableID());
    	this.add(this.tableIDLabel, BorderLayout.PAGE_START);
    	
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
    	
    	menuPanel.add(hitButton);
    	menuPanel.add(stayButton);

    	JButton leaveButton;
    	
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
    	
    	menuPanel.add(leaveButton);
    	
    	this.add(handPanel, BorderLayout.PAGE_END);
    	this.add(menuPanel, BorderLayout.EAST);
    	this.revalidate();
    	this.repaint();
    }
}