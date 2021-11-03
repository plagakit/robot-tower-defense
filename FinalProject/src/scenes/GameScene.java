package scenes;

import java.awt.Font;
import java.awt.Graphics2D;

import gameobjects.Bloon;
import gameobjects.BloonType;
import gameobjects.ObjectGroup;
import general.Difficulty;
import general.Game;
import general.Timer;
import projectiles.Projectile;
import towers.Tower;
import tracks.Track;
import tracks.TrackLoader;
import ui.Shop;

public class GameScene extends Scene {

	private Difficulty difficulty;
	private int maxRounds;
	private int currentRound;
	private int maxLives;
	private int currentLives;
	private int startingMoney;

	private Shop shop;
	private TrackLoader trackLoader;
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
			startingMoney = 4000;
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
		trackLoader = new TrackLoader();
		track = new Track(game, trackLoader.get("testTrack.track"));
	}
	
	@Override
	public void onStop() {

	}
	
	Timer bspawntimer = new Timer(game, 500);
	
	@Override
	public void update() {
		shop.update();
		
		bspawntimer.update();
		if (bspawntimer.isDone()) {
			Bloon b = new Bloon(this, track.getPoints()[0], BloonType.ZEBRA);
			bloons.add(b);
			bspawntimer.restart();
		}
		bloons.getList().sort((b1, b2) -> Float.compare(b2.getDistanceTravelled(), b1.getDistanceTravelled()));
		
		towers.update();
		projectiles.update();
		
		bloons.update();
	}
	
	@Override
	public void render(Graphics2D g) {
		track.render(g);
		
		towers.render(g);
		bloons.render(g);
		projectiles.render(g);
		
		shop.render(g);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString(String.format("Round: %d/%d  Lives: %d", currentRound, maxRounds, currentLives), 5, 15);
	}
	
	public void onBloonPopped() {
		shop.addMoney(1);
	}
	
	public void onLeak(int RBE) {
		currentLives -= RBE;
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
