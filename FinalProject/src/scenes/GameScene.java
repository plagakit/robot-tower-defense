package scenes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gameobjects.GameObject;
import gameobjects.ObjectGroup;
import gameobjects.Robot;
import gameobjects.Tower;
import gameobjects.TowerButton;
import general.Difficulty;
import general.Game;
import general.Vector2;

public class GameScene extends Scene {

	private Difficulty difficulty;
	private int maxLives;
	private int currentLives;
	
	private ObjectGroup<GameObject> gameObjects;
	private ObjectGroup<Tower> towers;
	
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
	public void onStart() {
		currentLives = maxLives;
		
		gameObjects = new ObjectGroup<GameObject>();
		towers = new ObjectGroup<Tower>();
	}
	
	TowerButton tb = new TowerButton(this, new Vector2(300, 100), Robot.class, "roboticon.png");
	
	@Override
	public void update() {
		tb.update();
		
		towers.update();
	}

	@Override
	public void render(Graphics2D g) {
		tb.render(g);
		
		towers.render(g);
		
		//Divider
		int scale = game.getScale();
		g.setStroke(new BasicStroke());
		g.drawLine(480 * scale, 0, 480 * scale, 360 * scale);
	}

	@Override
	public void onStop() {

	}
	
	public ObjectGroup<GameObject> getGameObjects() { return gameObjects; }
	
	public ObjectGroup<Tower> getTowers() { return towers; }

}
