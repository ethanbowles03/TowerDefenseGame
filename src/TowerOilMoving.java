package game;

import java.awt.Graphics;

public class TowerOilMoving implements Animatable {
	private GameState state;
	private boolean creditsSubtracted;

	public TowerOilMoving(GameState state) {
		this.state = state;
		creditsSubtracted = false;
	}

	@Override
	public void update(double timeElapsed) {
		if (state.isMouseClicked()) {
			state.removeGameObject(this);

			// Check to make sure it out of the menu area before placing
			if (state.validMousePos()) {
				state.addGameObject(new TowerOil(state, state.getMouseX(), state.getMouseY()));
				state.addCredits(-10);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ResourceLoader.getLoader().getImage("OilBarrel.png"), state.getMouseX(), state.getMouseY(), 30, 45,
				null);
	}

}
