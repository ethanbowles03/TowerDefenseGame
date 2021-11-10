/**
 * You will eventually need to add header comments to this file.
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameControl implements Runnable, ActionListener {

	// Fields
	GameView view;
	GameState state;
	double degree = 0.0;
	double percentTravelled = 0.0;

	public GameControl() {
		// I moved all the code into a function named 'run' below.
	}

	public void run() {

		// Build the game state.

		state = new GameState();

		// Build a view. Note that the view builds it's own frame, etc. All the work is
		// there.

		view = new GameView(state);

		// Add the Haas Car

		state.addGameObject(new HaasCar(0.0));
		// Start the animation loop.

		// Starts timer
		ActionListener a = this;

		Timer timer;
		timer = new Timer(16, this);
		timer.start();

	}

	// Called whenever an action event happens, and we are
	// listening to that event. The timer automatically sets
	// us 'this' up as the listener above.
	@Override
	public void actionPerformed(ActionEvent e) {

		// Gets the degree of the object should be at

		// Checks the percent the car is at and makes sure it doesn't crash
		if (percentTravelled >= 0.999) {
			percentTravelled = 0.0;
		}
		// Our animation 'loop' -- not an actual loop,
		// but we recognize that this function get called
		// repeatedly.

		// Update the game objects

		state.updateAll();

		// Draw the game objects

		view.repaint();

	}
}