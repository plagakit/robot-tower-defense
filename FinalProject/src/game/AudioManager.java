package game;

import java.util.HashMap;
import java.util.Scanner;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class AudioManager {

	private HashMap<String, Sound> audio = new HashMap<String, Sound>();
	private final float VOLUME_STEP = 50;
	
	public AudioManager() {
		TinySound.init();
		initAudio();
	}
	
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
	
	public void playSound(String name) {
		audio.get(name).play();
	}
	
	public void setVolume(int volume) {
		float adjustedVolume = volume / VOLUME_STEP;
		TinySound.setGlobalVolume(adjustedVolume);
	}
	
}
