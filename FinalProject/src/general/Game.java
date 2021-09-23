package general;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import graphics.Display;

public class Game {

	private boolean running;
	public static final boolean DEBUG = true;
	
	private int width;
	private int height;
	private int scale;
	private final String title = "Tower Defense";
	
	private final int FPS = 60;
	
	private Display display;
	private Graphics2D g;
	private BufferStrategy bs;
	
	private InputManager inputManager;
	
	public Game(int width, int height, int scale) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		init();
		run();
	}
	
	
	
	private void init() {
		
		inputManager = new InputManager();
		
		display = new Display(width, height, scale, title);
		display.getJFrame().addKeyListener(inputManager);
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
			deltaTime += (currentTime - lastTime) / timePerUpdate;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			if (deltaTime >= 1) {
				updates++;
				deltaTime--;
				
				update();
				render();
			}
			
			if (timer >= 1000000000) {
				if (DEBUG) {
					System.out.format("FPS: %d\n", updates);
					updates = 0;			
				}
				timer = 0;
			}
		}
	}
	
	
	private void update() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = (Graphics2D)bs.getDrawGraphics();
		
		g.clearRect(0, 0, (int)((width) * scale), (int)((height) * scale));
		
		//if (currentScene != null)
			//currentScene.render(g);

		bs.show();
		g.dispose();
	}

	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	public int getScale() { return scale; }
	
}
