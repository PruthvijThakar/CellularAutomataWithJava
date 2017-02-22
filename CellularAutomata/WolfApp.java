

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingWorker;



/**
 * A Test application for the Wolfram Celular Autonomon application
 * 
 *
 */
public class WolfApp extends CAApp {

	private static Logger log = Logger.getLogger(WolfApp.class.getName());

	protected JPanel northPanel = null;
	protected JButton startBtn = null;
	protected JButton stopBtn = null;
	protected JButton pauseBtn = null;
	protected JButton resumeBtn = null;
    private CACanvas caPanel = null;
    private int cellSize = 20;
    private Color col = null;
    MyBlankWorker BW = null;
	public JComboBox combo=null;
    /**
     * Sample app constructor
     */
    

    public WolfApp() {
    	frame.setSize(1200, 1000);
		frame.setTitle("WolfApp");
		
    	frame.add(getNorthPanel(), BorderLayout.NORTH); // Add to the center of our frame
    	caPanel = new CACanvas();
    	frame.add(caPanel, BorderLayout.CENTER); // Add to the center of our frame
		frame.setVisible(true); // The UI is built, so display it
		
    }
   

    public JPanel getNorthPanel() {
    	northPanel = new JPanel();
    	northPanel.setLayout(new FlowLayout());
    	
    	startBtn = new JButton("Start");
    	startBtn.addActionListener(this); // Allow the app to hear about button pushes
    	northPanel.add(startBtn);
    	
    	stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
    	stopBtn.addActionListener(this);
    	northPanel.add(stopBtn);
    	
    	pauseBtn = new JButton("Pause"); // Allow the app to hear about button pushes
    	pauseBtn.addActionListener(this);
    	northPanel.add(pauseBtn);
    	
    	resumeBtn = new JButton("Resume"); // Allow the app to hear about button pushes
    	resumeBtn.addActionListener(this);
    	northPanel.add(resumeBtn);
    	
    	combo=new JComboBox();
    	combo.setModel(new DefaultComboBoxModel(new String[] {"30","126","54","150","60","158","62","182"}));
    	northPanel.add(combo);

    	return northPanel;
    }
    
	PropertyChangeListener listener = null;

       
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("We received an ActionEvent " + e);
		if (e.getSource() == startBtn)
		{
			BW = new MyBlankWorker();
			BW.addPropertyChangeListener(listener);
			BW.execute();
			caPanel.repaint();
		
			
		}
		else if (e.getSource() == stopBtn){
			System.out.println("Stop pressed");
			BW.cancel(true);
		}   
		else if (e.getSource() == pauseBtn){
			System.out.println("Pause pressed");
		}
		else if (e.getSource() == resumeBtn){
			System.out.println("Resume pressed");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}



	@Override
	public void windowClosing(WindowEvent e) {	
	}



	@Override
	public void windowClosed(WindowEvent e) {
	}



	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}



	@Override
	public void windowDeiconified(WindowEvent e) {	
		log.info("Window deiconified");
	}



	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}



	@Override
	public void windowDeactivated(WindowEvent e) {	
		log.info("Window deactivated");
	}
	
	/**
	 * Sample Wolf application starting point
	 * @param args
	 */
	public static void main(String[] args) {
		WolfApp wapp = new WolfApp();
		log.info("WolfApp started");
	}
	private void paintRect(Graphics2D g2d, int x, int y, int size, Color color) {
		g2d.setColor(color);
		g2d.fillRect(x, y, size, size);
	}
	
	public class MyBlankWorker extends SwingWorker<Void, Void> {

		  @Override
		  protected Void doInBackground() throws Exception {
		    // Start
			  
			  Cellular cellular = new Cellular();
			  int[] row =cellular.FirstrowGeneration();
			  
			  for (int j = 0; j < 100; j++) {
				   
				  for (int i = 0; i < row.length; i++) {
					  
					   if(row[i]==0)
					   col = new Color(255, 255, 255);
					   else
						   col = new Color(1,1,1);
					   // Draw box, one pixel less to create a black outline
					   paintRect((Graphics2D)caPanel.getGraphics() , i*cellSize, j*cellSize + 20, cellSize-1, col); 
				   }
				  Thread.sleep(500);
				   row = cellular.generationofAutomata(row,Integer.valueOf((String)(combo.getSelectedItem())));
				}
			   	  
		   
		    return null;
		  }
	  }  	
	  
	  
	
}
