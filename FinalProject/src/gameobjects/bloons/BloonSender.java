package gameobjects.bloons;

import java.util.Scanner;

import game.Timer;
import game.Vector2;
import scenes.GameScene;

/** A class that adds bloons to the game scene based on the wave
 * data text file. */
public class BloonSender {

	private GameScene scene;
	
	private boolean sending;
	private Wave[] waves;
	private Wave currentWave;
	
	private Timer bloonSpawnTimer;
	private Vector2 spawnPoint;
	
	/** Creates the bloon sender and initializes the wave data. */
	public BloonSender(GameScene scene) { 
		this.scene = scene;
		
		sending = false;
		initWaves();
		spawnPoint = scene.getTrack().getPoints()[0];
	}
	
	/** Gets the wave data text file and reads its contents, adding
	 * the bloons to its list in Wave objects. */
	private void initWaves() { 
		final String dataPath = "wavedata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		int counter = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			
			if (line.charAt(0) == '#')
				continue;
			else if (waves == null)
				waves = new Wave[Integer.parseInt(line)];
			else {
				waves[counter] = new Wave(line);
				counter++;
			}

		}
		
		sc.close();
	}
	
	/** Adds bloons to the game scene according to the wave data. */
	public void update() { 
		
		if (sending) {
			bloonSpawnTimer.update();
			if (bloonSpawnTimer.isDone()) {
				BloonType type = currentWave.getNextBloon();
				if (type != BloonType.EMPTY && type != null) {
					Bloon b = new Bloon(scene, spawnPoint, type);
					scene.getBloons().add(b);
				}
				bloonSpawnTimer.restart(currentWave.getSpacingTime());
			}
			
			if (currentWave.isDone())
				sending = false;
		}
		
	}
	
	/** Loads the specified round and the wave that it is associated
	 * with, and starts sending the bloons in its wave when updated. */
	public void sendRound(int round) { 
		
		if (round > waves.length || round < 0)
			return;
		
		sending = true;
		currentWave = waves[round - 1];
		
		if (currentWave == null) {
			sending = false;
			return;
		}
		
		bloonSpawnTimer = new Timer(scene.getGame(), currentWave.getSpacingTime());
	}
	
	public boolean isSending() { return sending; }
	
}
