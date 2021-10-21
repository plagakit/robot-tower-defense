package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gameobjects.Bloon;
import gameobjects.ObjectGroup;
import general.Difficulty;
import general.Game;
import general.Vector2;
import projectiles.Projectile;
import towers.Tower;
import tracks.Track;
import ui.Shop;

public class GameScene extends Scene {

	private Difficulty difficulty;
	private int maxRounds;
	private int currentRound;
	private int maxLives;
	private int currentLives;
	private int startingMoney;

	private Shop shop;
	private Track track;
	
	private ObjectGroup<Tower> towers;
	private ObjectGroup<Projectile> projectiles;
	private ObjectGroup<Bloon> bloons;
	private Tower currentTowerSelection;
	
	public GameScene(Game game, Difficulty difficulty) {
		super(game);
		this.difficulty = difficulty;
		
		switch (difficulty) {
		case EASY:
			maxLives = 100;
			maxRounds = 50;
			startingMoney = 500;
			break;
		case MEDIUM:
			maxLives = 50;
			maxRounds = 75;
			startingMoney = 400;
			break;
		case HARD:
			maxLives = 1;
			maxRounds = 100;
			startingMoney = 300;
			break;
		}
	}
	
	@Override
	public void onStart() {
		currentLives = maxLives;
		currentRound = 0;

		towers = new ObjectGroup<Tower>();
		projectiles = new ObjectGroup<Projectile>();
		bloons = new ObjectGroup<Bloon>();
		
		shop = new Shop(this, startingMoney);
		track = new Track(game, "testTrack.png", "testMask.png");
	}
	
	@Override
	public void update() {
		shop.update();
		
		towers.update();
		projectiles.update();
		bloons.update();
	}
	
	@Override
	public void render(Graphics2D g) {
		track.render(g);
		
		towers.render(g);
		projectiles.render(g);
		bloons.render(g);
		
		shop.render(g);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString(String.format("R%d/%d", currentRound, maxRounds), 5, 15);
	}

	@Override
	public void onStop() {

	}

	public void setTowerSelection(Tower t) { 
		if (currentTowerSelection != null)
			currentTowerSelection.setSelected(false);
		currentTowerSelection = t; 
		shop.setTowerSelection(t);
	}
	
	public Shop getShop() { return shop; }
	
	public Track getTrack() { return track; }
	
	public ObjectGroup<Tower> getTowers() { return towers; }
	
	public ObjectGroup<Projectile> getProjectiles() { return projectiles; }
	
	public ObjectGroup<Bloon> getBloons() { return bloons; }
	
}
