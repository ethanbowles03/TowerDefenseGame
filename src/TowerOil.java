package game;

import java.awt.Graphics;

public class TowerOil implements Animatable {
	private GameState state;
	private int x, y, oilHeight, oilWidth;
	private boolean creditsSubtracted;

	public TowerOil(GameState state, int x, int y) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.oilWidth = 24;
		this.oilHeight = 36;
	}

	@Override
	public void update(double timeElapsed) {
		// Shoot oil at nearby enemies
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ResourceLoader.getLoader().getImage("OilBarrel.png"), x, y, oilWidth, oilHeight, null);
	}

}
