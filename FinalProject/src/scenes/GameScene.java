package scenes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import gameobjects.Robot;
import gameobjects.TowerButton;
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
	
	TowerButton tb = new TowerButton(this, new Vector2(300, 100), Robot.class, "smileicon.png");

	@Override
	public void update() {
		tb.update();
	}

	@Override
	public void render(Graphics2D g) {
		tb.render(g);
		
		//Divider
		int scale = game.getScale();
		g.setStroke(new BasicStroke());
		g.drawLine(480 * scale, 0, 480 * scale, 360 * scale);
	}

	@Override
	public void onStart() {
		currentLives = maxLives;
	}

	@Override
	public void onStop() {

	}

}
