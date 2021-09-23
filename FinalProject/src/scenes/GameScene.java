package scenes;

import java.awt.Graphics2D;

import gameobjects.Square;
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
	
	Square square = new Square(this, Vector2.zero());

	@Override
	public void update() {
		square.update();
	}

	@Override
	public void render(Graphics2D g) {
		square.render(g);
	}

	@Override
	public void onStart() {
		currentLives = maxLives;
	}

	@Override
	public void onStop() {

	}

}
