import java.awt.BorderLayout;
import java.awt.Color;

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
    	this.title.setText("Table ID: " + gui.getTableID());
    	this.revalidate();
    	this.repaint();
    }
}