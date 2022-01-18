package general;

public class Settings {

	private Game game;
	private boolean autostart;
	private int volume;
	private int displaySize;
	
	public Settings(Game game) {
		this.game = game;
		this.autostart = false;
		this.volume = 50;
		this.displaySize = game.getScale();
	}
	
	public boolean getAutostart() { return autostart; }
	public void setAutostart(boolean autostart) { this.autostart = autostart; }
	
	public int getVolume() { return volume; }
	public void setVolume(int volume) {
		if (volume < 0)
			this.volume = 0;
		else if (volume > 100)
			this.volume = 100;
		else
			this.volume = volume;
		
		game.getAudioManager().setVolume(volume);
	}
	
	public int getDisplaySize() { return displaySize; }
	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
		game.setScale(displaySize);
	}
	
}
