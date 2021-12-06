/**
 * Empty, not yet used.
 */
package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	// Fields

	List<Animatable> gameObjects;
	List<Animatable> objectsToRemove;
	List<Animatable> objectsToAdd;

	int mouseX, mouseY, score, credits, lives, oilCreditSubtract;
	boolean mouseClicked, validMousePos;
	
	private PlacementDetection pd;

	// Soon to be used.

	public GameState() {
		gameObjects = new ArrayList<Animatable>();
		objectsToRemove = new ArrayList<Animatable>();
		objectsToAdd = new ArrayList<Animatable>();

		mouseX = mouseY = 0;
		mouseClicked = false;
		score = 0;
		lives = 3;
		credits = 50;
		pd = new PlacementDetection("TrackMask.png");
	}

	/**
	 * Adds a game object to the list. The game then adds the game objects in the
	 * list every time the timer updates
	 * 
	 * @param a -> the object being added
	 */
	public void addGameObject(Animatable a) {
		objectsToAdd.add(a);
	}

	/**
	 * Adds a game object to the list of objects to be removed. The game then
	 * removes the game objects in the list every time the timer updates
	 * 
	 * @param a -> the object being removed
	 */
	public void removeGameObject(Animatable a) {
		objectsToRemove.add(a);
	}

	/**
	 * Sets the mouseX and mouseY variables to correspond with the current mouse
	 * position.
	 * 
	 * @param x -> x coordinate the mouse is currently on
	 * @param y -> y coordinate the mouse is currently on
	 */
	public void setMouseLocation(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	/**
	 * Returns the value stored in the variable mouseX
	 * 
	 * @return
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * Returns the value stored in the variable mouseY
	 * 
	 * @return
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * Returns the boolean value corresponding to the mouse being clicked.
	 * 
	 * @return
	 */
	public boolean isMouseClicked() {
		return mouseClicked;
	}

	/**
	 * Sets the mouseClicked boolean variable to the boolean value given in the
	 * parameter.
	 * 
	 * @param a -> boolean value that mouseClicked will be updated to
	 */
	public void setMouseClicked(boolean a) {
		mouseClicked = a;
	}

	/**
	 * Consumes the mouse click. Makes the current value for the mouseClicked
	 * boolean false.
	 */
	public void consumeMouseClick() {
		mouseClicked = false;
	}

	/**
	 * Returns a boolean on whether the mouse is in a valid spot to place a tower.
	 * Currently only restricts towers from being placed back on the menu
	 * 
	 * @return
	 */
	public boolean validMousePos() {
		if (this.getMouseY() < 491 && !pd.checkColor(this.getMouseX(), this.getMouseY())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds a certain amount to the score variable
	 * 
	 * @param x -> integer value that gets added to the score
	 */
	public void addScore(int x) {
		score = score + x;
	}

	public int getScore() {
		return score;
	}

	public void addLives(int x) {
		lives = lives + x;
	}

	public int getLives() {
		return lives;
	}

	public void addCredits(int x) {
		credits = credits + x;
	}

	public int getCredits() {
		return credits;
	}

	public void editCredts(int x) {
		credits = x;
	}

	public void updateAll(double elapsedTime) {
		for (Animatable a : gameObjects) {
			a.update(elapsedTime);
		}

		gameObjects.removeAll(objectsToRemove);
		objectsToRemove.clear();

		gameObjects.addAll(objectsToAdd);
		objectsToAdd.clear();
	}

	public void drawAll(Graphics g) {
		for (Animatable a : gameObjects) {
			a.draw(g);
		}
	}
}
