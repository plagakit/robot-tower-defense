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
		
		g.setColor(Color.BLACK);
		
		if (tbInfo != null) {
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(tbInfo.getTitle(), 485, 65);
			g.drawString("$" + tbInfo.getBaseCost(), 590, 65);
			
			g.setFont(new Font("Arial", Font.PLAIN, 10 ));
			g.drawString(tbInfo.getDescription(), 490, 80);
 		}
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		String moneyStr = "$" + money;
		int strWidth = g.getFontMetrics().stringWidth(moneyStr);
		g.drawString(moneyStr, 480 - strWidth, 15);
	}
	
	public void setCurrentTowerButtonInfo(BuyInfo info) { tbInfo = info; }
	
	
	public long getMoney() { return money; }
	
	public void addMoney(long add) { money += add; }
	
	public void subtractMoney(long subtract) { money -= subtract; }
	
}
