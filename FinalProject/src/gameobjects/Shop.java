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
	private Tower currentSelectedTower;
	
	public Shop(GameScene scene, long startingMoney) {
		this.scene = scene;
		this.money = startingMoney;
		
		towerButtons = new TowerButton[] {
			new TowerButton(scene, this, new Vector2(515, 24), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(560, 24), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(605, 24), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(515, 68), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(560, 68), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(605, 68), new Robot(scene, null), "roboticon.png")
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
			g.drawString(tbInfo.getTitle(), 485, 105);
			
			String costStr = "$" + tbInfo.getBaseCost();
			int costStrWidth = g.getFontMetrics().stringWidth(costStr);
			g.drawString(costStr, 635 - costStrWidth, 105);
			
			g.setFont(new Font("Arial", Font.PLAIN, 10 ));
			g.drawString(tbInfo.getDescription(), 490, 120);
 		}
		
		g.drawLine(480, 130, 640, 130);
		
		if (currentSelectedTower != null)
			g.drawString(currentSelectedTower.getName(), 485, 165);
		
		
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		String moneyStr = "$" + money;
		int moneyStrWidth = g.getFontMetrics().stringWidth(moneyStr);
		g.drawString(moneyStr, 475 - moneyStrWidth, 15);
		
	}
	
	public void setCurrentTowerButtonInfo(BuyInfo info) { tbInfo = info; }
	
	public void setTowerSelection(Tower tower) { currentSelectedTower = tower; }
	
	public long getMoney() { return money; }
	
	public void addMoney(long add) { money += add; }
	
	public void subtractMoney(long subtract) { money -= subtract; }
	
}
