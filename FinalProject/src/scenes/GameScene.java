package scenes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gameobjects.Button;
import gameobjects.GameObject;
import gameobjects.ObjectGroup;
import gameobjects.Robot;
import gameobjects.Tower;
import gameobjects.TowerButton;
import general.Difficulty;
import general.Game;
import general.Vector2;
import tracks.Track;

public class GameScene extends Scene {

	private Difficulty difficulty;
	private int maxRounds;
	private int currentRound;
	private int maxLives;
	private int currentLives;
	
	private Button[] shopButtons;

	private Track track;
	
	private ObjectGroup<GameObject> gameObjects;
	private ObjectGroup<Tower> towers;
	private Tower currentTowerSelection;
	
	public GameScene(Game game, Difficulty difficulty) {
		super(game);
		this.difficulty = difficulty;
		
		switch (difficulty) {
		case EASY:
			maxLives = 100;
			maxRounds = 50;
			break;
		case MEDIUM:
			maxLives = 50;
			maxRounds = 75;
			break;
		case HARD:
			maxLives = 1;
			maxRounds = 100;
			break;
		}
	}
	
	@Override
	public void onStart() {
		currentLives = maxLives;
		currentRound = 0;
		
		shopButtons = new Button[] {
				new TowerButton(this, new Vector2(505, 24), Robot.class, "roboticon.png")
		};

		track = new Track(game, "testTrack.png", "testMask.png");
		
		gameObjects = new ObjectGroup<GameObject>();
		towers = new ObjectGroup<Tower>();
	}
	
	@Override
	public void update() {

		for (Button b : shopButtons)
			b.update();
		
		towers.update();
	}
	
	@Override
	public void render(Graphics2D g) {
		track.render(g);

		for (Button b : shopButtons)
			b.render(g);
		
		towers.render(g);
		
		//Divider & text temporary
		int scale = game.getScale();
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke());
		g.drawLine(480 * scale, 0, 480 * scale, 360 * scale);
		
		g.setFont(new Font("Arial", Font.BOLD, 15 * scale));
		//g.drawString(String.format("Round %d/%d", currentRound, maxRounds), 0, 15 * scale);
		if (currentTowerSelection != null)
			g.drawString(currentTowerSelection.getName(), 480 * scale, 15 * scale);
	}

	@Override
	public void onStop() {

	}
	
	public ObjectGroup<GameObject> getGameObjects() { return gameObjects; }
	
	public ObjectGroup<Tower> getTowers() { return towers; }

	public void setTowerSelection(Tower t) { 
		if (currentTowerSelection != null)
			currentTowerSelection.setSelected(false);
		currentTowerSelection = t; 
	}
	
	public Track getTrack() { return track; }
	
}
