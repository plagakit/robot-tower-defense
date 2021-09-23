package scenes;

import java.awt.Graphics2D;

import general.Game;

public abstract class Scene {

	protected Game game;
	
	public Scene(Game game) {
		this.game = game;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
	
	public abstract void onStart();
	
	public abstract void onStop();
	
	public Game getGame() { return game; }
}
