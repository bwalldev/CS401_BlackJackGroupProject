import java.awt.BorderLayout;
import java.awt.Color;
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
    	
    	// Redo, just using to test
    	if (gui.getPlayer() instanceof Dealer) {
    	    
    	    JButton closeButton = new JButton("Close Table");
    	    closeButton.addActionListener(e -> {
    	        String username = gui.getPlayer().getUsername();
    	        int tableID = gui.getTableID();
    	        gui.getClient().sendCloseTableMessage(username, tableID);
    	    });
    	    this.add(closeButton, BorderLayout.NORTH);
    	} else {
    	    
    	    JButton leaveButton = new JButton("Leave Table");
    	    leaveButton.addActionListener(e -> {
    	        String username = gui.getPlayer().getUsername();
    	        int tableID = gui.getTableID();
    	        gui.getClient().sendLeaveTableMessage(username, tableID);
    	    });
    	    this.add(leaveButton, BorderLayout.SOUTH);
    	}
    	
    	
    	this.title.setText("Table ID: " + gui.getTableID());
    	this.revalidate();
    	this.repaint();
    }
}