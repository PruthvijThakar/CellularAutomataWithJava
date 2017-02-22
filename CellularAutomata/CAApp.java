import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A sample abstract application class
 * 
 *
 */
public abstract class CAApp implements ActionListener, WindowListener {
	protected JFrame frame = null;


	
	public CAApp() {
		initGUI();
	}
    public void initGUI() {
    	frame = new JFrame();
		frame.setTitle("JUIApp2");

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame.DISPOSE_ON_CLOSE)
		
		// Permit the app to hear about the window opening
		frame.addWindowListener(this); 
		
		frame.setLayout(new BorderLayout());
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		
  

    }
    
    public abstract JPanel getNorthPanel() ;

}
