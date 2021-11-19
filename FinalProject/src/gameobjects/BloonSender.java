package gameobjects;

import java.util.Scanner;

import general.Timer;
import general.Vector2;
import scenes.GameScene;

public class BloonSender {

	private GameScene scene;
	
	private boolean sending;
	private Wave[] waves;
	private Wave currentWave;
	
	private Timer bloonSpawnTimer;
	private Vector2 spawnPoint;
	
	public BloonSender(GameScene scene) { 
		this.scene = scene;
		
		sending = false;
		initWaves();
		spawnPoint = scene.getTrack().getPoints()[0];
	}
	
	private void initWaves() { 
		final String dataPath = "wavedata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		int counter = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			
			System.out.println(line);
			
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
