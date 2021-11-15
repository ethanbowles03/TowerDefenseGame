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

	// Soon to be used.

	public GameState() {
		gameObjects = new ArrayList<Animatable>();
		objectsToRemove = new ArrayList<Animatable>();
		objectsToAdd = new ArrayList<Animatable>();
	}

	public void addGameObject(Animatable a) {
		objectsToAdd.add(a);
	}
	
	public void removeGameObject(Animatable a) {
		objectsToRemove.add(a);
	}

	public void updateAll() {
		for (Animatable a : gameObjects) {
			a.update(0);
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
