package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Path {

	// Fields
	private Point[] points = new Point[78];

	/**
	 * This constructor does the following: - It reads a number of coordinates, n,
	 * from the scanner. - It creates new array(s) (or an ArrayList) to hold the
	 * path coordinates, and stores it in the field in 'this' object. - It loops n
	 * times, each time scanning a coordinate x,y pair, creating an object to
	 * represent the coordinate (if needed), and stores the coordinate in the
	 * array(s) or ArrayList.
	 * 
	 * @param s a Scanner set up by the caller to provide a list of coordinates
	 */
	public Path(Scanner s) {
		int count = 0;
		while (s.hasNext()) {
			int x = s.nextInt();
			int y = s.nextInt();
			points[count] = new Point(x, y);
			count++;
		}
	}

	/**
	 * Given a percentage between 0% and 100%, this method calculates the location
	 * along the path that is exactly this percentage along the path. The location
	 * is returned in a Point object (integer x and y), and the location is a screen
	 * coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is returned. If the
	 * percentage is greater than 100%, the final position is returned.
	 * 
	 * If students don't want to use Point objects, they may write or use another
	 * object to represent coordinates.
	 *
	 * Caution: Students should never directly return a Point object from a path
	 * list. It could be changed by the outside caller. Instead, always create and
	 * return new point objects as the result from this method.
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point getPathPosition(double percentTraveled) {
		
		double distanceFromStart = percentTraveled * getPathLength();
		
		double distanceToSpot = 0;
		double useDistance = 0;
		
		int segmentCount = 0;
		
		Point currentPoint = null;
		Point nextPoint = null;
		
		int x = (int) points[0].getX();
		int y = (int) points[0].getY();
		for (int i = 0; i < points.length - 1; i++) {
			int newX = (int) points[i + 1].getX();
			int newY = (int) points[i + 1].getY();
			useDistance = distanceToSpot;
			distanceToSpot += Math.sqrt((newX - x) * (newX - x) + (newY - y) * (newY - y));
			if(distanceFromStart < distanceToSpot) {
				currentPoint = points[i];
				nextPoint = points[i+1];
				break;
			}	
			x = newX;
			y = newY;
		}
		
		double numVal = distanceFromStart - useDistance;
		double denomVal = distanceToSpot - useDistance;
		
		double fracPerc = numVal/denomVal;
		
		//Xresult = (1 - 0.4) Xstart + (0.4) Xend  and Yresult = (1 - 0.4) Ystart + (0.4) Yend. 
		
		double xResult, xStart, xEnd, yResult, yStart, yEnd;
		
		xStart = currentPoint.getX();
		yStart = currentPoint.getY();
		
		xEnd = nextPoint.getX();
		yEnd = nextPoint.getY();
				
		xResult = (1 - fracPerc) * xStart + (fracPerc) * xEnd;
		yResult = (1 - fracPerc) * yStart + (fracPerc) * yEnd;
		
		int xResultInt = (int)xResult;
		int yResultInt = (int)yResult;
		
		Point returnPoint = new Point(xResultInt, yResultInt);
		
		System.out.println(returnPoint);
		
		return returnPoint;
	}
		
//		if (percentTraveled <= 0) {
//			Point startingPoint = points[0];
//			return startingPoint;
//		} else if (percentTraveled < .25) {
//			
//		} else if (percentTraveled < .5) {
//			
//		} else if (percentTraveled < .75) {
//			
//		} else if (percentTraveled <= 1.0) {
//			Point endPoint = points[points.length - 1];
//			return endPoint;
//		}
	//}

	/**
	 * Returns the total length of the path. Since the path is specified using
	 * screen coordinates, the length is in pixel units (by default).
	 * 
	 * @return the length of the path
	 */
	public double getPathLength() {
		int x = (int) points[0].getX();
		int y = (int) points[0].getY();
		double length = 0.0;
		for (int i = 0; i < points.length - 1; i++) {
			int newX = (int) points[i + 1].getX();
			int newY = (int) points[i + 1].getY();
			length += Math.sqrt((newX - x) * (newX - x) + (newY - y) * (newY - y));
			x = newX;
			y = newY;
		}
		return length;
	}

	/**
	 * Draws the current path to the screen (to wherever the graphics object points)
	 * using a highly-visible color.
	 * 
	 * @param g a graphics object
	 */

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		int x = (int) points[0].getX();
		int y = (int) points[0].getY();
		for (int i = 0; i < points.length - 1; i++) {
			int newX = (int) points[i + 1].getX();
			int newY = (int) points[i + 1].getY();
			g.drawLine(x, y, newX, newY);
			x = newX;
			y = newY;
		}
	}

}
