package scenes;

import java.awt.Font;

import gameobjects.Bloon;
import gameobjects.BloonSender;
import gameobjects.ObjectGroup;
import general.Difficulty;
import general.Game;
import graphics.Renderer;
import projectiles.Projectile;
import towers.Tower;
import tracks.Track;
import tracks.TrackLoader;
import ui.Shop;

public class GameScene extends Scene {

	private boolean paused;
	private PauseMenu pauseMenu;
	
	private Difficulty difficulty;
	private int maxRounds;
	private int currentRound;
	private int maxLives;
	private int currentLives;
	private int startingMoney;
	private float speedModifier;

	private Shop shop;
	private float costModifier;
	
	private TrackLoader trackLoader;
	private Track track;
	
	private BloonSender bloonSender;
	
	private ObjectGroup<Tower> towers;
	private ObjectGroup<Projectile> projectiles;
	private ObjectGroup<Bloon> bloons;
	
	private boolean inRound;
	
	public GameScene(Game game, Difficulty difficulty) {
		super(game);
		this.difficulty = difficulty;
		
		switch (difficulty) {
		case EASY:
			maxLives = 100;
			maxRounds = 50;
			startingMoney = 500;
			costModifier = 0.75f; 
			speedModifier = 0.8f;
			break;
		case MEDIUM:
			maxLives = 50;
			maxRounds = 50;
			startingMoney = 400;
			costModifier = 1;
			speedModifier = 1;
			break;
		case HARD:
			maxLives = 1;
			maxRounds = 50;
			startingMoney = 30000;
			costModifier = 1.5f;
			speedModifier = 1.2f;
			break;
		}
	}
	
	@Override
	public void onStart() {
		pauseMenu = new PauseMenu(this);
		paused = false;
		
		currentLives = maxLives;
		currentRound = 0;
		inRound = false;
		
		towers = new ObjectGroup<Tower>();
		projectiles = new ObjectGroup<Projectile>();
		bloons = new ObjectGroup<Bloon>();
		
		shop = new Shop(this, startingMoney, costModifier);
		
		trackLoader = new TrackLoader();
		track = new Track(game, trackLoader.get("testTrack.track"));
		bloonSender = new BloonSender(this);
	}
	
	@Override
	public void onStop() {

	}
	
	
	@Override
	public void update() {
		
		if (paused) {
			pauseMenu.update();
			return;
		}
		
		
		if (inRound && !bloonSender.isSending() && bloons.getList().size() == 0)
			finishRound();
		
		shop.update();
		
		bloonSender.update();
		bloons.getList().sort((b1, b2) -> Float.compare(b2.getDistanceTravelled(), b1.getDistanceTravelled()));
		
		towers.update();
		projectiles.update();
		
		bloons.update();
	}
	
	@Override
	public void render(Renderer r) {
		track.render(r);
		
		bloons.render(r);
		projectiles.render(r);
		towers.render(r);
		
		shop.render(r);
		
		r.setFont(new Font("Arial", Font.BOLD, 15));
		r.drawString(String.format("Round: %d/%d  Lives: %d", currentRound, maxRounds, currentLives), 5, 15);
	
		if (paused)
			pauseMenu.render(r);
	}
	
	public void startNextRound() { 
		inRound = true;
		currentRound++;
		bloonSender.sendRound(currentRound);
	}
	
	public void finishRound() {
		inRound = false;
		shop.addMoney(currentRound + 99);
		
		if (game.getSettings().getAutostart())
			startNextRound();
	}
	
	public void onBloonPopped() {
		shop.addMoney(1);
	}
	
	public void onLeak(int RBE) {
		currentLives -= RBE;
	}
	
	public float getSpeedModifier() { return speedModifier; }
	
	public void setPaused(boolean paused) { this.paused = paused; }
	
	public Shop getShop() { return shop; }
	
	public Track getTrack() { return track; }
	
	public ObjectGroup<Tower> getTowers() { return towers; }
	
	public ObjectGroup<Projectile> getProjectiles() { return projectiles; }
	
	public ObjectGroup<Bloon> getBloons() { return bloons; }
	
	public boolean inRound() { return inRound; }
	
}
