package scenes;

import game.Game;
import graphics.Renderer;

public abstract class Scene {

	protected Game game;
	
	public Scene(Game game) {
		this.game = game;
	}
	
	public abstract void update();
	
	public abstract void render(Renderer r);
	
	public abstract void onStart();
	
	public abstract void onStop();
	
	public Game getGame() { return game; }
}
