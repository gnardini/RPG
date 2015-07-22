package setup;

import java.awt.BorderLayout;

import javax.swing.*;


public class mainFrame extends JFrame implements myConstants{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	mainFrame() {
		super("RPG");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		mainCanvas canvas = new mainCanvas();
		add(canvas, BorderLayout.CENTER);
		
		// set it's size and make it visible
		setSize(234+ESCALA*26, 30+ESCALA*21);
		setVisible(true);		
		// now that is visible we can tell it that we will use 2 buffers to do the repaint
		// before being able to do that, the Canvas has to be visible
		canvas.createBufferStrategy(2);
	}
	
	// just to start the application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new mainFrame();
			}
		});
	}
}

