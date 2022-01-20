package game;

import java.util.HashMap;
import java.util.Scanner;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

/**
 * The AudioManager class handles the initializing and playing of all types of sound.
 * It uses the TinySound library.
 */
public class AudioManager {

	private HashMap<String, Sound> audio = new HashMap<String, Sound>();
	private final float VOLUME_STEP = 50;
	
	public AudioManager() {
		TinySound.init();
		initAudio();
	}
	
	/** Initializes and stores all the sound files found in audiodata.txt as TinySound sound objects. Only used once and in AudioManager's contstructor. */
	private void initAudio() {
		final String folder = "audio/";
		final String dataPath = folder + "audiodata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		while (sc.hasNextLine()) {
			String path = sc.nextLine();
			String absolutePath = folder + path;
			
			if (path.toCharArray()[0] == '#')
				continue;
			
			System.out.println("Loading " + path);
			Sound s = TinySound.loadSound(absolutePath);
			audio.put(path, s);
		}
		
		sc.close();
	}
	
	/** Plays the sound of the specified name. If the sound is not found, nothing is done.
	 * @param name The name of the sound (with the extension, ex. audio.wav) */
	public void playSound(String name) {
		Sound s = audio.get(name);
		if (s != null)
			audio.get(name).play();
	}
	
	/** Adjusts TinySound's global volume by a scale of AudioManager's volume step (50). */
	public void setVolume(int volume) {
		float adjustedVolume = volume / VOLUME_STEP;
		TinySound.setGlobalVolume(adjustedVolume);
	}
	
}
