package game;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import graphics.Display;
import graphics.Renderer;
import graphics.SpriteManager;
import scenes.MainMenuScene;
import scenes.Scene;

/** The main Game class where the game is initialized and played. */
public class Game {

	private boolean running;
	public static boolean DEBUG = false; // if on, allows various debug options
	
	// window options
	private final int DEFAULT_WIDTH = 640;
	private final int DEFAULT_HEIGHT = 360;
	private final int DEFAULT_SCALE = 2;
	private int width;
	private int height;
	private int scale;
	private final String title = "Tower Defense";
	
	// time options
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
	
	/** Initializes the game, its components, and its files. To be used only in the 
	 * constructor. */
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
	
	/** Runs the game in a main loop that is timed to update and render every frame of 
	 * time. If debug is on, the FPS will be printed to the console. Only to be used in 
	 * the constructor after init() has been called. */
	private void run() {
		running = true;
		
		double timePerUpdate = 1000000000/FPS; // Duration of 1 frame in nanoseconds
		long currentTime;
		long lastTime = System.nanoTime();
		double deltaTime = 0;
		
		long timer = 0;
		int updates = 0;
		
		// main game loop
		while (running) {
			currentTime = System.nanoTime();
			long diff = currentTime - lastTime;
			
			deltaTime += diff / timePerUpdate; // how long the loop took. perfect loop equals a delta of 1, but laggy loop might be something like 1.6 (0.6 extra delta time taken to complete loop)
			timer += diff;
			currentNanoTime += (long)(diff * timeScale); // timeScale adjusts the speed of the loop, allows for fastforward or pausing
			
			lastTime = currentTime;
			
			if (deltaTime >= 1) {
				updates++;
				deltaTime--;
				
				//System.out.format("%d %d\n", updates, currentNanoTime);
				
				update();
				render();
			}
			
			// if the timer exceeds 1 second in nanoseconds, print FPS
			if (timer >= 1000000000) {
				if (DEBUG)
					System.out.format("FPS: %d\n", updates);			
				updates = 0;
				timer = 0;
			}
		}
	}
	
	/** Updates the Game's components and, if it exists, the current scene. */
	private void update() {
		inputManager.update();
		
		if (inputManager.isDebugPressed())
			DEBUG = !DEBUG;
		
		if (currentScene != null)
			currentScene.update();
	}
	
	/** Creates a Renderer and, if it exists, renders the current scene. */
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			/* a buffer strategy prevents flickering, because it makes it so that the 
			 * renderer draws images onto its buffers before displaying to the screen 
			 * the first part of this article goes into more depth about frame buffering:
			 * https://gameprogrammingpatterns.com/double-buffer.html */
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

	/** Sets the current scene, calls its onStart method, and calls the previous scene's onStop method. 
	 * @param scene The new scene to be set to.
	 */
	public void setCurrentScene(Scene scene) {
		if (currentScene != null)
			currentScene.onStop();
		currentScene = scene;
		currentScene.onStart();
		if (DEBUG)
			System.out.println("Switched scene to " + scene);
	}
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	public int getScale() { return scale; }
	
	/** Sets the scale but also sets the display's scale automatically. */
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
