package general;

public class Timer {
	
	private Game game;
	
	private long goalTime;
	private long lastTime;
	private long timer;
	private boolean done;
	
	public Timer(Game game, long timeInMS) {
		this.game = game;
		restart(timeInMS);
	}
	
	public void update() {
		if (done)
			return;
		
		timer += game.getTime() - lastTime;
		lastTime = game.getTime();
		
		if (timer >= goalTime)
			done = true;
	}
	
	public void restart() {
		restart(goalTime / 1000000);
	}
	
	public void restart(long timeInMS) {
		goalTime = timeInMS * 1000000;
		lastTime = game.getTime();
		timer = 0;
		done = false;
	}

	public boolean isDone() { return done; }
}
