package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class HaasCar implements Animatable {

	// Fields
	private double position;
	double degree = 0.0;
	double anchorX = 0;
	double anchorY = 0;
	double percentTravelled = 0.0;

	// Builds the HaasCar object with a specified position
	public HaasCar(double p) {
		position = 0;
	}

	/**
	 * Rotates an image a certain number of degrees
	 * 
	 * @param icon   - takes in the picture/icon that needs to be rotated
	 * @param degree - the degree which to rotate the image
	 * @return - returns the rotated image
	 */
	public BufferedImage rotateIcon(BufferedImage icon, Double degree) {
		// Variables
		double sin, cos;
		int width, height, iconWidth, iconHeight;

		// Get Image Width and Height
		iconWidth = icon.getWidth();
		iconHeight = icon.getHeight();

		// Finds the sin and cos value then gets the new width and height of the icon
		sin = Math.abs(Math.sin(Math.toRadians(degree)));
		cos = Math.abs(Math.cos(Math.toRadians(degree)));
		width = (int) Math.round(iconWidth * cos + iconHeight * sin);
		height = (int) Math.round(iconWidth * sin + iconHeight * cos);

		// Creates new image with the new width and height
		BufferedImage rotatedIcon = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = rotatedIcon.createGraphics();

		// Find the point of rotation
		anchorX = (width - iconWidth) / 2;
		anchorY = (height - iconHeight) / 2;

		iconWidth /= 2;
		iconHeight /= 2;

		// Rotates the image around the anchor and draws it on the screen
		AffineTransform a = new AffineTransform();
		a.setToRotation(Math.toRadians(degree), anchorX + iconWidth, anchorY + iconHeight);
		a.translate(anchorX, anchorY);
		g.setTransform(a);
		g.drawImage(icon, 0, 0, null);
		g.dispose();

		return rotatedIcon;
	}

	@Override
	public void update(double timeElapsed) {
		position = position + 0.001;
		if (position > 1) {
			position = 0.0;
		}

	}

	@Override
	public void draw(Graphics g) {

		// Checks the percent the car is at and makes sure it doesn't crash
		if (percentTravelled >= 0.999) {
			percentTravelled = 0.0;
		}
		// Gets the position on the path
		Point objPoint = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(percentTravelled);
		int xPos = (int) objPoint.getX();
		int yPos = (int) objPoint.getY();

		int xPosCalculated, yPosCalculated;

		xPosCalculated = (int) (xPos - anchorX - 8);
		yPosCalculated = (int) (yPos - anchorY - 20);

		degree = ResourceLoader.getLoader().getPath("path.txt").getDegree(percentTravelled);
		// Rotates and draws the icon on the screen
		BufferedImage carImage = rotateIcon(ResourceLoader.getLoader().getImage("HaasCar.png"), degree - 90);
		g.drawImage(carImage, xPosCalculated, yPosCalculated, null);

		percentTravelled = percentTravelled + 0.001;

	}

}
