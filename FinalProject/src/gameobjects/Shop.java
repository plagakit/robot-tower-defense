package gameobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import general.Vector2;
import scenes.GameScene;

public class Shop {

	private GameScene scene;
	
	private long money;
	
	private TowerButton[] towerButtons;
	private BuyInfo tbInfo;
	
	public Shop(GameScene scene, long startingMoney) {
		this.scene = scene;
		this.money = startingMoney;
		
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
		
		g.setFont(new Font("Arial", Font.BOLD, 15 * scale));
		String moneyStr = "$" + money;
		int strWidth = g.getFontMetrics().stringWidth(moneyStr);
		g.drawString(moneyStr, 480 * scale - strWidth, 15 * scale);
	}
	
	public void setCurrentTowerButtonInfo(BuyInfo info) { tbInfo = info; }
	
	
	public long getMoney() { return money; }
	
	public void addMoney(long add) { money += add; }
	
	public void subtractMoney(long subtract) { money -= subtract; }
	
}
