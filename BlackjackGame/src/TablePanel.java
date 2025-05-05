import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablePanel extends JPanel {
	private GUI gui;
	JLabel title;
	
    public TablePanel(GUI gui) {
    	this.gui = gui;
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());

       this.title = new JLabel("");
       
       this.add(title);
       
       updatePanel();
    }
    
    public void updatePanel() {
    	this.removeAll();
    	this.setLayout(new BorderLayout());

    	if (gui.getPlayer() instanceof Dealer) {
    	    // Show Close Table only for Dealer
    	    JButton closeButton = new JButton("Close Table");
    	    
    	    closeButton.addActionListener(e -> {
    	        String username = gui.getPlayer().getUsername();
    	        int tableID = gui.getTableID();
    	        gui.getClient().sendCloseTableMessage(username, tableID);
    	    });
    	    this.add(closeButton, BorderLayout.NORTH);
    	} else {
    	    // Show Leave Table only for Player
    	    JButton leaveButton = new JButton("Leave Table");
    	    leaveButton.addActionListener(e -> {
    	        String username = gui.getPlayer().getUsername();
    	        int tableID = gui.getTableID();
    	        gui.getClient().sendLeaveTableMessage(username, tableID);
    	    });
    	    this.add(leaveButton, BorderLayout.SOUTH);
    	}
    	
    	this.title.setText("Table ID: " + gui.getTableID());
    	this.add(title);
    	this.revalidate();
    	this.repaint();
    }
}