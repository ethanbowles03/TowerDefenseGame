/**
 * You will eventually need to add header comments to this file.
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameView extends JPanel implements MouseListener, ActionListener
{
	// This constant is needed to get rid of a warning.  It won't matter to us.
	private static final long serialVersionUID = 1L;
	
	// Fields -- These variables will be part of the GameView object (that we make in GameControl).
	private Path path;
	private Timer timer;
	private double percentTravelled, degree;
	private int anchorX, anchorY;
	private BufferedImage backdrop, car;
	
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
    		//Creates backdrop object
	    	ClassLoader loader = this.getClass().getClassLoader();
	    	InputStream is = loader.getResourceAsStream("resources/path_2.jpg");
	    	backdrop = javax.imageio.ImageIO.read(is);
	    	
	    	//Creates Haas car object
	    	is = loader.getResourceAsStream("resources/Cars/HaasCar.png");
	    	car = javax.imageio.ImageIO.read(is);
	    	
	    	//Get file and creates new path
	    	Scanner pathScanner = new Scanner(loader.getResourceAsStream("resources/path.txt"));
	    	path = new Path(pathScanner);
    	}
    	catch (IOException e)
    	{
    		System.out.println("Could not load the backdrop or path.");
    		System.exit(0);
    	}
    	
    	//Initializes fields
    	percentTravelled = 0.0;
    	degree = 0.0;
    	anchorX = 0;
    	anchorY = 0;

    	// Build the frame.  The frame object represents the application 'window'.
    	JFrame frame = new JFrame ("Tower Defense 2021");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);

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
    	
    	//Starts timer
    	timer = new Timer(16, this);
    	timer.start();
    	
    	//Adds mouse listener
    	addMouseListener(this);
    }
    
    /**
     * Rotates an image a certain number of degrees
     * @param icon - takes in the picture/icon that needs to be rotated
     * @param degree - the degree which to rotate the image
     * @return - returns the rotated image
     */
    public BufferedImage rotateIcon(BufferedImage icon, Double degree) {
    	//Variables
        double sin,cos;
        int width,height,iconWidth,iconHeight;
        
        //Get Image Width and Height
        iconWidth = icon.getWidth();
        iconHeight = icon.getHeight();
        
        //Finds the sin and cos value then gets the new width and height of the icon
        sin = Math.abs(Math.sin(Math.toRadians(degree)));
        cos = Math.abs(Math.cos(Math.toRadians(degree)));
        width = (int) Math.round(iconWidth * cos + iconHeight * sin);
        height = (int) Math.round(iconWidth * sin + iconHeight * cos);

        //Creates new image with the new width and height
        BufferedImage rotatedIcon = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedIcon.createGraphics();
        		
        //Find the point of rotation
        anchorX = (width - iconWidth) / 2;
        anchorY = (height - iconHeight) / 2;
        
        iconWidth /= 2;
        iconHeight /= 2;

        //Rotates the image around the anchor and draws it on the screen
        AffineTransform a = new AffineTransform();
        a.setToRotation(Math.toRadians(degree), anchorX + iconWidth, anchorY + iconHeight);
        a.translate(anchorX, anchorY);
        g.setTransform(a);
        g.drawImage(icon, 0, 0, null);
        g.dispose();
        
        return rotatedIcon;
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
    	//Variables
    	int xPos, yPos;
    	
    	// Draw the backdrop.
    	g.drawImage(backdrop, 0, 0, null);
    	
    	//Draws the path on the panel
    	path.paint(g);
    	
    	//Gets the position on the path
    	Point objPoint = path.getPathPosition(percentTravelled);
    	xPos = (int) objPoint.getX();
    	yPos = (int) objPoint.getY();
    	
    	//Rotates and draws the icon on the screen
    	g.drawImage(rotateIcon(car, degree - 90),xPos - anchorX - 8, yPos - anchorY - 20,null );
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
		//Gets the degree of the object should be at
		degree = path.getDegree(percentTravelled);
		
		//Checks the percent the car is at and makes sure it doesnt crash
		if(percentTravelled >= 0.999) {
			percentTravelled = 0.0;
		}
		//Loops and repaints
		percentTravelled += 0.001;
		repaint();
	}
}
