package general;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import graphics.Display;
import graphics.Renderer;
import graphics.SpriteManager;
import scenes.GameScene;
import scenes.Scene;

public class Game {

	private boolean running;
	public static boolean DEBUG = true;
	
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
	
	private Scene currentScene;
	private GameScene gameScene;
	
	public Game(int width, int height, int scale) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		init();
		run();
	}
	
	
	private void init() {
		
		inputManager = new InputManager(this);
		spriteManager = new SpriteManager();
		
		display = new Display(width, height, scale, title);
		display.getJFrame().addKeyListener(inputManager);
		//display.getJFrame().addMouseListener(inputManager);
		//display.getJFrame().addMouseMotionListener(inputManager);
		display.getCanvas().addMouseListener(inputManager);
		display.getCanvas().addMouseMotionListener(inputManager);
		
		gameScene = new GameScene(this, Difficulty.HARD);
		setCurrentScene(gameScene);
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
	}
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	public int getScale() { return scale; } 
	
	public long getTime() { return currentNanoTime; }
	
	public float getTimeScale() { return timeScale; }
	
	public void setTimeScale(float timeScale) { this.timeScale = timeScale; }
	
	public InputManager getInputManager() { return inputManager; }
	
	public SpriteManager getSpriteManager() { return spriteManager; }
}
