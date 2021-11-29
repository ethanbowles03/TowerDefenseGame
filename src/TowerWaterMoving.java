package game;

import java.awt.Graphics;

public class TowerWaterMoving implements Animatable {
	private GameState state;

	public TowerWaterMoving(GameState state) {
		this.state = state;
	}

	@Override
	public void update(double timeElapsed) {
		if (state.isMouseClicked()) {
			state.removeGameObject(this);

			// Check to make sure it out of the menu area before placing
			if (state.validMousePos()) {
				state.addGameObject(new TowerWater(state, state.getMouseX(), state.getMouseY()));
				state.addCredits(-5);
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ResourceLoader.getLoader().getImage("WaterBottle.png"), state.getMouseX(), state.getMouseY(), 20,
				50, null);
	}

}
