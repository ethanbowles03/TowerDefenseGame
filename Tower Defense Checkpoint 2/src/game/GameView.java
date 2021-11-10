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

public class GameView extends JPanel implements MouseListener {
	// This constant is needed to get rid of a warning. It won't matter to us.
	private static final long serialVersionUID = 1L;

	// Fields -- These variables will be part of the GameView object (that we make
	// in GameControl).
	private Path path;
	private double percentTravelled, degree;
	private int anchorX, anchorY;
	private GameState state;

	/**
	 * Our GameView constructor. The 'view' is the GUI (Graphical User Interface)
	 * and this constructor builds a JFrame (window) so the user can see our
	 * 'drawing'.
	 */
	public GameView(GameState state) {
		this.state = state;
		// Load the backdrop image and path from the resources folder. Feel free to
		// alter this,
		// but be careful to make sure your resources folder is a java package in the
		// src
		// portion of your project.

		ResourceLoader loader = ResourceLoader.getLoader();
		path = loader.getPath("path.txt");

		// Initializes fields
		percentTravelled = 0.0;
		degree = 0.0;
		anchorX = 0;
		anchorY = 0;

		// Build the frame. The frame object represents the application 'window'.
		JFrame frame = new JFrame("Tower Defense 2021");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// Add a drawing area to the frame (a panel). Note that 'this' object IS the
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

		// Adds mouse listener
		addMouseListener(this);
	}

	/**
	 * Draws our game. This function will be called automatically when Java needs to
	 * repaint our window. Use the repaint() function call (on this object) to cause
	 * this function to be executed.
	 * 
	 * @param Graphics g the Graphics object to use for drawing
	 */
	public void paint(Graphics g) {
		// Variables
		int xPos, yPos;

		// Draw the backdrop.
		g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"), 0, 0, null);

		// Draws the path on the panel
		path.paint(g);

		// Draw everything else
		state.drawAll(g);
	}

	/*
	 * The following methods are required for mouse events. I've collapsed some of
	 * them to make it easier to see which one you need. Also note: You'll need to
	 * register 'this' object as a listener to its own events. See the missing code
	 * in the constructor.
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}