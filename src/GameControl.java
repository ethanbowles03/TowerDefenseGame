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
	int count, carCount, gameOverCount;
	long previousTime;

	public GameControl() {
		// I moved all the code into a function named 'run' below.
	}

	public void run() {

		// Build the game state.

		state = new GameState();

		// Build a view. Note that the view builds it's own frame, etc. All the work is
		// there.

		view = new GameView(state);

		state.addGameObject(new Menu(state));
		state.addGameObject(new EnemyHaasCar(0, state));

		// Start the animation loop.

		// Starts timer

		Timer timer;
		timer = new Timer(16, this);
		timer.start();

		// Keep track of the start time of the first tick
		previousTime = System.nanoTime();

		count = 0;
	}

	// Called whenever an action event happens, and we are
	// listening to that event. The timer automatically sets
	// us 'this' up as the listener above.
	@Override
	public void actionPerformed(ActionEvent e) {
		// Checks to see if the game is over
		if (state.getLives() < 0 && gameOverCount < 1) {
			state.addGameObject(new GameOver(state));

			long currentTime = System.nanoTime();
			double elapsedTime = (currentTime - previousTime) / 1_000_000_000.0;

			state.updateAll(elapsedTime);

			view.repaint();

			gameOverCount = 1;
		} else { // Continues if it is not
			if (gameOverCount != 1) {
				count++;
				if (count % 150 == 0) {
					// Adds a truck 25% of the time
					if (carCount % 3 == 0) {
						state.addGameObject(new EnemyHaasTruck(0, state));
						carCount++;
					} else {
						state.addGameObject(new EnemyHaasCar(0, state));
						carCount++;
					}
				}
			}

			// Our animation 'loop' -- not an actual loop,
			// but we recognize that this function get called
			// repeatedly.

			// Calculate elapsed time since last update

			long currentTime = System.nanoTime();
			double elapsedTime = (currentTime - previousTime) / 1_000_000_000.0;

			previousTime = currentTime;

			// Update the game objects

			state.updateAll(elapsedTime);

			// Make sure the mouse click is consumed
			state.consumeMouseClick();

			// Draw the game objects

			view.repaint();

		}
	}
}