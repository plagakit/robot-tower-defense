package scenes;

import java.awt.Color;
import java.awt.Font;

import gameobjects.Bloon;
import gameobjects.BloonSender;
import gameobjects.ObjectGroup;
import general.Difficulty;
import general.Game;
import general.Timer;
import graphics.Renderer;
import projectiles.Projectile;
import towers.Tower;
import tracks.Track;
import ui.Shop;
import ui.Tip;

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

	private Tip tip;
	private Shop shop;
	private float costModifier;
	
	private Track track;
	
	private BloonSender bloonSender;
	
	private ObjectGroup<Tower> towers;
	private ObjectGroup<Projectile> projectiles;
	private ObjectGroup<Bloon> bloons;
	
	private boolean inRound;
	
	enum State {
		PLAYING,
		WON,
		LOST
	}
	private State gameState;
	private Timer gameEndTimer = new Timer(game, 5000);
	
	public GameScene(Game game, Track track, Difficulty difficulty) {
		super(game);
		this.track = track;
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
			startingMoney = 300;
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
		gameState = State.PLAYING;
		
		towers = new ObjectGroup<Tower>();
		projectiles = new ObjectGroup<Projectile>();
		bloons = new ObjectGroup<Bloon>();
		
		tip = new Tip(this);
		tip.showTip(currentRound);
		shop = new Shop(this, startingMoney, costModifier);
		
		bloonSender = new BloonSender(this);
	}
	
	@Override
	public void onStop() {

	}
	
	
	@Override
	public void update() {
		
		if (gameState != State.PLAYING) {
			
			gameEndTimer.update();
			if (gameEndTimer.isDone())
				game.setCurrentScene(new MainMenuScene(game));
			
			return;
		} 
		else if (paused) {
			pauseMenu.update();
			return;
		}
		
		if (gameState == State.PLAYING && currentLives <= 0)
			lose();
		
		if (inRound && !bloonSender.isSending() && bloons.getList().size() == 0)
			finishRound();
		
		tip.update();
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
		
		tip.render(r);
		shop.render(r);
		
		r.setFont(new Font("Arial", Font.BOLD, 15));
		String info = String.format("Round: %d/%d  Lives: %d", currentRound, maxRounds, currentLives);
		r.drawOutlinedString(info, 5, 15, Color.WHITE, 2);
	
		if (gameState != State.PLAYING) {
			
			r.setFont(new Font("Arial", Font.BOLD, 30));
			String message = gameState == State.WON ? "You win!" : "Game Over!";
			Color c = gameState == State.WON ? Color.GREEN : Color.RED;
			r.setColor(Color.BLACK);
			r.drawOutlinedString(message, 200, 170, c, 3);
			
		} 
		else if (paused)
			pauseMenu.render(r);
	}
	
	public void startNextRound() { 
		inRound = true;
		currentRound++;
		bloonSender.sendRound(currentRound);
		tip.startFade();
	}
	
	public void finishRound() {
		inRound = false;
		shop.addMoney(currentRound + 99);
		
		if (gameState == State.PLAYING && currentRound >= maxRounds)
			win();
		else
			tip.showTip(currentRound);
		
		if (game.getSettings().getAutostart())
			startNextRound();
	}
	
	public void onBloonPopped() {
		shop.addMoney(1);
	}
	
	public void onLeak(int RBE) {
		currentLives -= RBE;
	}
	
	private void win() {
		System.out.println("Game won!");
		gameState = State.WON;
		gameEndTimer.restart();
	}
	
	private void lose() {
		System.out.println("Game lost!");
		gameState = State.LOST;
		gameEndTimer.restart();
		
	}
	
	public float getSpeedModifier() { return speedModifier; }
	
	public void setPaused(boolean paused) { this.paused = paused; }
	
	public Shop getShop() { return shop; }
	
	public Track getTrack() { return track; }
	
	public ObjectGroup<Tower> getTowers() { return towers; }
	
	public ObjectGroup<Projectile> getProjectiles() { return projectiles; }
	
	public ObjectGroup<Bloon> getBloons() { return bloons; }
	
	public State getGameState() { return gameState; }
	
	public boolean inRound() { return inRound; }
	
}
