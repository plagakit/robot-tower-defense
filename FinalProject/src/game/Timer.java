package game;

/** A class used for timing events in real seconds. It waits for
 * a certain amount of milliseconds, updating with the Game's
 * update method, and when that goal time is reached an internal
 * flag is switched. */
public class Timer {
	
	private Game game;
	
	private long goalTime;
	private long lastTime;
	private long timer;
	private boolean done;
	
	/** Creates a timer object and readies it for timing.
	 * @param game The Game object (used for keeping track of updates/time).
	 * @param timeInMS The amount of milliseconds that the timer 
	 * should wait for.
	 */
	public Timer(Game game, long timeInMS) {
		this.game = game;
		restart(timeInMS);
	}
	
	/** Updates the timer with the amount of time that has passed
	 * since the last update. */
	public void update() {
		if (done)
			return;
		
		timer += game.getTime() - lastTime;
		lastTime = game.getTime();
		
		if (timer >= goalTime)
			done = true;
	}
	
	/** Restarts the timer with the same goal time as before. */
	public void restart() {
		restart(goalTime / 1000000);
	}
	
	/** Restarts the timer so that its back at the start.
	 * @param timeInMS The new goal time in milliseconds. */
	public void restart(long timeInMS) {
		goalTime = timeInMS * 1000000;
		lastTime = game.getTime();
		timer = 0;
		done = false;
	}

	public boolean isDone() { return done; }
}
