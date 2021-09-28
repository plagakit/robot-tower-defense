package scenes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gameobjects.GameObject;
import gameobjects.Robot;
import gameobjects.TowerButton;
import general.Difficulty;
import general.Game;
import general.Vector2;

public class GameScene extends Scene {

	private Difficulty difficulty;
	private int maxLives;
	private int currentLives;
	
	private List<GameObject> gameObjects;
	
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
		gameObjects = new ArrayList<GameObject>();
	}
	
	TowerButton tb = new TowerButton(this, new Vector2(300, 100), Robot.class, "roboticon.png");

	@Override
	public void update() {
		tb.update();
		
		for (GameObject go : gameObjects)
			go.update();
	}

	@Override
	public void render(Graphics2D g) {
		tb.render(g);
		
		for (GameObject go : gameObjects)
			go.render(g);
		
		//Divider
		int scale = game.getScale();
		g.setStroke(new BasicStroke());
		g.drawLine(480 * scale, 0, 480 * scale, 360 * scale);
	}

	@Override
	public void onStop() {

	}
	
	public void addGameObject(GameObject go) {
		if (!gameObjects.contains(go))
			gameObjects.add(go);
	}

}
