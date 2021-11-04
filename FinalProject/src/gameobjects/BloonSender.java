package gameobjects;

import general.Timer;
import scenes.GameScene;

public class BloonSender {

	private GameScene scene;
	private Timer bspawntimer;
	
	private boolean sending;
	
	public BloonSender(GameScene scene) { 
		this.scene = scene;
		
		sending = false;
	}
	
	public void update() { 
		if (sending) {
			bspawntimer.update();
			if (bspawntimer.isDone()) {
				Bloon b = new Bloon(scene, scene.getTrack().getPoints()[0], BloonType.GREEN);
				scene.getBloons().add(b);
				bspawntimer.restart();
			}
		}
	}
	
	public void sendRound(int round) { 
		sending = true;
		bspawntimer = new Timer(scene.getGame(), 500);
	}
	
}
