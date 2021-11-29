package game;

import java.awt.Graphics;

public class GameOver implements Animatable {
	private GameState state;

	public GameOver(GameState state) {
		this.state = state;
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ResourceLoader.getLoader().getImage("GameOver.png"), 0, 0, null);
	}
}
