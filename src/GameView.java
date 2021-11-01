/**
 * You will eventually need to add header comments to this file.
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameView extends JPanel implements MouseListener, ActionListener
{
	// This constant is needed to get rid of a warning.  It won't matter to us.
	
	private static final long serialVersionUID = 1L;
	
	// Fields -- These variables will be part of the GameView object (that we make in GameControl).
	Path path;
	Timer timer;
	double percentTravelled;
	
	private BufferedImage backdrop;
	// I have removed the other fields.  Add them back in as part of the first checkpoint.
	
	/**
	 * Our GameView constructor.  The 'view' is the GUI (Graphical User Interface) and
	 * this constructor builds a JFrame (window) so the user can see our 'drawing'.
	 */
    public GameView ()
    {
    	// Load the backdrop image and path from the resources folder.  Feel free to alter this,
    	// but be careful to make sure your resources folder is a java package in the src
    	// portion of your project.
    	
    	try
    	{
	    	ClassLoader loader = this.getClass().getClassLoader();
	    	InputStream is = loader.getResourceAsStream("resources/path_2.jpg");
	    	backdrop = javax.imageio.ImageIO.read(is);
	    	
	    	Scanner pathScanner = new Scanner(loader.getResourceAsStream("resources/path.txt"));
	    	
	    	path = new Path(pathScanner);
	    	double length = path.getPathLength();	  
	    	
	    	percentTravelled = 0.0;
	    	
	    	timer = new Timer(16, this);
	    	timer.start();
	    	
	    	addMouseListener(this);
    	}
    	catch (IOException e)
    	{
    		System.out.println("Could not load the backdrop or path.");
    		System.exit(0);
    	}

    	// Build the frame.  The frame object represents the application 'window'.
    	
    	JFrame frame = new JFrame ("Tower Defense 2021");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	// Add a drawing area to the frame (a panel).  Note that 'this' object IS the
    	// panel that we need, so we add it.
    	
    	JPanel p = this;
    	frame.setContentPane(p);
    	
    	// Set the size of 'this' panel to match the size of the backdrop.    	
    	
    	Dimension d = new Dimension(600, 600);
    	this.setMinimumSize(d);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    	
    	// Allow the JFrame to layout the window (by 'packing' it) and make it visible.
    	
    	frame.pack();
    	frame.setVisible(true);
    	
    	// This panel can send mouse events to any object that wants to 'listen' to those
    	// events.  I've removed the lines of code for the mouse listener and timer,
    	// feel free to re-add them as needed.
    }
    
    /**
     * Draws our game.  This function will be called automatically when Java needs to
     * repaint our window.  Use the repaint() function call (on this object) to cause
     * this function to be executed.
     * 
     * @param Graphics g  the Graphics object to use for drawing
     */
    public void paint (Graphics g)
    {
    	// Draw the backdrop.
    	
    	g.drawImage(backdrop, 0, 0, null);
    	
    	path.paint(g);
    	
    	Point ballPoint = path.getPathPosition(percentTravelled);
    	int xPos, yPos;
    	xPos = (int) ballPoint.getX();
    	yPos = (int) ballPoint.getY();
    	
    	
    	g.setColor(new Color(249,142,29));
    	g.fillRect(xPos - 10, yPos - 10, 20, 20);
    	
    	// I've removed the code that draws the path and the ball.  You will need
    	// to re-add it.
    	
    	// You may also experiment with other drawing here.
    }
    
    /* The following methods are required for mouse events.  I've collapsed some of them to
     * make it easier to see which one you need.  Also note:  You'll need to register
     * 'this' object as a listener to its own events.  See the missing code in the
     * constructor.
     */
    
	public void mousePressed(MouseEvent e) 
	{
		System.out.println(e.getX() + " " + e.getY());
	}
	
    public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }

    /* The following method is required for action events.  You'll need to set up
     * the timer in the constructor in order for this method to be automatically
     * called.  Re-add the missing code in the constructor.
     */
	
	public void actionPerformed(ActionEvent e) 
	{
		if(percentTravelled >= 0.999) {
			percentTravelled = 0.0;
		}
		percentTravelled += 0.001;
		repaint();
	}
}
