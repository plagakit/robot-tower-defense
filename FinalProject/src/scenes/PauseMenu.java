package scenes;

import java.awt.Color;

import general.Game;
import graphics.Renderer;

public class PauseMenu {

	GameScene scene;
	
	public PauseMenu(GameScene scene) {
		this.scene = scene;
	}

	public void update() {

	}

	public void render(Renderer r) {		
		r.setColor(Color.BLACK);
		r.setTransparency(0.5f);
		r.fillRect(0, 0, scene.getGame().getWidth(), scene.getGame().getHeight());
	}

}
