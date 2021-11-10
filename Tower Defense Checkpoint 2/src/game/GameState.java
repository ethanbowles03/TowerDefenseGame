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

	// Soon to be used.

	public GameState() {
		gameObjects = new ArrayList<Animatable>();
	}

	public void addGameObject(Animatable a) {
		gameObjects.add(a);
	}

	public void updateAll() {
		for (Animatable a : gameObjects) {
			a.update(0);
		}
	}

	public void drawAll(Graphics g) {
		for (Animatable a : gameObjects) {
			a.draw(g);
		}
	}
}
