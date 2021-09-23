package scenes;

import java.awt.Graphics2D;

import general.Difficulty;
import general.Game;
import general.Vector2;

public class GameScene extends Scene {

	private Difficulty difficulty;
	private int maxLives;
	private int currentLives;
	
	public GameScene(Game game, Difficulty difficulty) {
		super(game);
		this.difficulty = difficulty;
		
		switch (difficulty) {
		case EASY:
			maxLives = 100;
			break;
		case MEDIUM:
			maxLives = 50;
			break;
		case HARD:
			maxLives = 1;
			break;
		}
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D g) {
		Vector2 mousePos = game.getInputManager().getMousePos();
		g.fillRect((int)mousePos.x, (int)mousePos.y, 50, 50);
	}

	@Override
	public void onStart() {
		currentLives = maxLives;
	}

	@Override
	public void onStop() {

	}

}
