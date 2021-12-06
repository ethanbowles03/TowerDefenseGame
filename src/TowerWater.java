package game;

import java.awt.Graphics;

public class TowerWater implements Animatable {
	private GameState state;
	private int x, y, waterWidth, waterHeight;

	public TowerWater(GameState state, int x, int y) {
		this.state = state;
		this.x = x;
		this.y = y;
		waterWidth = 12;
		waterHeight = 30;
	}

	@Override
	public void update(double timeElapsed) {
		// Shoot oil at nearby enemies
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ResourceLoader.getLoader().getImage("WaterBottle.png"), x, y, waterWidth, waterHeight, null);
	}

}
