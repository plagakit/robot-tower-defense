package general;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import graphics.Display;
import graphics.Renderer;
import graphics.SpriteManager;
import scenes.GameScene;
import scenes.MainMenuScene;
import scenes.Scene;

public class Game {

	private boolean running;
	public static boolean DEBUG = false;
	
	private final int DEFAULT_WIDTH = 640;
	private final int DEFAULT_HEIGHT = 360;
	private final int DEFAULT_SCALE = 2;
	private int width;
	private int height;
	private int scale;
	private final String title = "Tower Defense";
	
	private final int FPS = 144;
	private long currentNanoTime = 0;
	private float timeScale = 1;
	
	private Display display;
	private BufferStrategy bs;
	private Renderer r;
	
	private InputManager inputManager;
	private SpriteManager spriteManager;
	private AudioManager audioManager;
	private Settings settings;
	
	private Scene currentScene;
	
	public Game() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		scale = DEFAULT_SCALE;
		
		init();
		run();
	}
	
	
	private void init() {
		
		inputManager = new InputManager(this);
		spriteManager = new SpriteManager();
		audioManager = new AudioManager();
		settings = new Settings(this);
		
		display = new Display(width, height, scale, title);
		display.getJFrame().addKeyListener(inputManager);
		//display.getJFrame().addMouseListener(inputManager);
		//display.getJFrame().addMouseMotionListener(inputManager);
		display.getCanvas().addMouseListener(inputManager);
		display.getCanvas().addMouseMotionListener(inputManager);
		
		setCurrentScene(new MainMenuScene(this));
	}
	
	private void run() {
		running = true;
		
		double timePerUpdate = 1000000000/FPS; // Duration of 1 frame in nanoseconds
		long currentTime;
		long lastTime = System.nanoTime();
		double deltaTime = 0;
		
		long timer = 0;
		int updates = 0;
		
		while (running) {
			currentTime = System.nanoTime();
			long diff = currentTime - lastTime;
			
			deltaTime += diff / timePerUpdate;
			timer += diff;
			currentNanoTime += (long)(diff * timeScale);
			
			lastTime = currentTime;
			
			if (deltaTime >= 1) {
				updates++;
				deltaTime--;
				
				//System.out.format("%d %d\n", updates, currentNanoTime);
				
				update();
				render();
			}
			
			if (timer >= 1000000000) {
				if (DEBUG)
					System.out.format("FPS: %d\n", updates);			
				updates = 0;
				timer = 0;
			}
		}
	}
	
	
	private void update() {
		inputManager.update();
		
		if (inputManager.isDebugPressed())
			DEBUG = !DEBUG;
		
		if (currentScene != null)
			currentScene.update();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		r = new Renderer((Graphics2D)bs.getDrawGraphics());
		
		r.scale(scale, scale);
		r.clearRect(0, 0, width, height);
		
		if (currentScene != null)
			currentScene.render(r);

		bs.show();
		r.dispose();
	}

	public void setCurrentScene(Scene scene) {
		if (currentScene != null)
			currentScene.onStop();
		currentScene = scene;
		currentScene.onStart();
		System.out.println("Switched scene to " + scene);
	}
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	public int getScale() { return scale; }
	public void setScale(int newScale) {
		scale = newScale;
		display.changeScale(newScale);
	}
	
	public long getTime() { return currentNanoTime; }
	
	public float getTimeScale() { return timeScale; }
	
	public void setTimeScale(float timeScale) { this.timeScale = timeScale; }
	
	public InputManager getInputManager() { return inputManager; }
	
	public SpriteManager getSpriteManager() { return spriteManager; }
	
	public AudioManager getAudioManager() { return audioManager; }
	
	public Settings getSettings() { return settings; }

	public Display getDisplay() { return display; }
}
