package game;

/** A container class for holding the settings of the game. */
public class Settings {

	private Game game;
	private boolean autostart;
	private boolean showTips;
	private int volume;
	private int displaySize;
	
	/** Initializes all settings to their default values. */
	public Settings(Game game) {
		this.game = game;
		this.autostart = false;
		this.volume = 50;
		this.displaySize = game.getScale();
		this.showTips = true;
	}
	
	public boolean getAutostart() { return autostart; }
	public void setAutostart(boolean autostart) { this.autostart = autostart; }
	
	public boolean getShowTips() { return showTips; }
	public void setShowTips(boolean showTips) { this.showTips = showTips; }
	
	public int getVolume() { return volume; }
	/** Sets the volume to a value between 0-100 and updates the AudioManager. */
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
