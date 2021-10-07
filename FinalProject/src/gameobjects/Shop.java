package gameobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import general.Vector2;
import scenes.GameScene;

public class Shop {

	private GameScene scene;
	
	private TowerButton[] towerButtons;
	private BuyInfo tbInfo;
	
	public Shop(GameScene scene) {
		this.scene = scene;
		
		towerButtons = new TowerButton[] {
			new TowerButton(scene, this, new Vector2(505, 24), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(550, 24), new Robot(scene, null), "roboticon.png")
		};
	}
	
	public void update() {
		for (Button b : towerButtons)
			b.update();
	}
	
	public void render(Graphics2D g) {
		for (Button b : towerButtons)
			b.render(g);
		
		int scale = scene.getGame().getScale();
		g.setColor(Color.BLACK);
		
		if (tbInfo != null) {
			g.setFont(new Font("Arial", Font.BOLD, 15 * scale));
			g.drawString(tbInfo.getTitle(), 485 * scale, 65 * scale);
			g.drawString("$" + tbInfo.getBaseCost(), 590 * scale, 65 * scale);
			
			g.setFont(new Font("Arial", Font.PLAIN, 10 * scale));
			g.drawString(tbInfo.getDescription(), 490 * scale, 80 * scale);
 		}
		
		
	}
	
	public void setCurrentTowerButtonInfo(BuyInfo info) { tbInfo = info; }
}
