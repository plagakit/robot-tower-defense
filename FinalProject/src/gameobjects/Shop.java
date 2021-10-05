package gameobjects;

import java.awt.Graphics2D;

import general.Vector2;
import scenes.GameScene;

public class Shop {

	private GameScene scene;
	
	private TowerButton[] towerButtons;
	
	public Shop(GameScene scene) {
		this.scene = scene;
		
		towerButtons = new TowerButton[] {
				new TowerButton(scene, new Vector2(505, 32), Robot.class, "roboticon.png")
		};
	}
	
	public void update() {
		for (TowerButton tb : towerButtons)
			tb.update();
	}
	
	public void render(Graphics2D g) {
		for (TowerButton tb: towerButtons)
			tb.render(g);
	}
	
}
