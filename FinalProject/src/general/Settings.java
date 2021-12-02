package general;

public class Settings {

	private boolean autostart;
	private int volume;
	
	public Settings() {
		this.autostart = false;
		this.volume = 50;
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
	}
	
}
