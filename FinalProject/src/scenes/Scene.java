package scenes;

import game.Game;
import graphics.Renderer;

/** An abstract Scene object that allows for different parts 
 * of the game, for example the main menu, game area, pause menu, 
 * etc. The game uses a scene switcher in the Game object, and has 
 * methods for when it starts and stops.*/
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
