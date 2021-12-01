package ui;

import java.awt.Color;
import java.awt.Font;

import gameobjects.BuyInfo;
import general.Vector2;
import graphics.Renderer;
import scenes.GameScene;
import towers.FireRobot;
import towers.Robot;
import towers.ScissorRobot;
import towers.Tower;

public class Shop {
	
	private long money;
	private float costModifier;
	
	private TowerButton[] towerButtons;
	private BuyInfo tbInfo;
	
	private Tower selectedTower;
	private UpgradePanel upgradePanel;
	
	private PlayButton playButton; 
	private FastForwardButton ffButton;
	private SettingsButton settingsButton;
	
	public Shop(GameScene scene, long startingMoney, float costModifier) {
		this.money = startingMoney;
		this.costModifier = costModifier;
		
		towerButtons = new TowerButton[] {
			new TowerButton(scene, this, new Vector2(515, 24), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(560, 24), new ScissorRobot(scene, null), "scissoricon.png"),
			new TowerButton(scene, this, new Vector2(605, 24), new FireRobot(scene, null), "fireicon.png"),
			new TowerButton(scene, this, new Vector2(515, 68), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(560, 68), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(605, 68), new Robot(scene, null), "roboticon.png")
		};

		upgradePanel = new UpgradePanel(scene, this);
		
		playButton = new PlayButton(scene, new Vector2(540, 340));
		ffButton = new FastForwardButton(scene, new Vector2(500, 340));
		settingsButton = new SettingsButton(scene, new Vector2(580, 340));
	}
	
	public void update() {
		for (Button b : towerButtons)
			b.update();
		upgradePanel.update();
		playButton.update();
		ffButton.update();
		settingsButton.update();
	}
	
	public void render(Renderer r) {
		// TODO remove magic numbers
		// Bg & outline
		r.setColor(new Color(207, 168, 114));
		r.fillRect(480, 0, 160, 360);
		
		r.setStroke(2);
		r.setColor(Color.BLACK);
		r.drawLine(480, 0, 480, 360);
		r.drawLine(480, 140, 640, 140);
		
		// Buttons
		for (Button b : towerButtons)
			b.render(r);
		upgradePanel.render(r);
		playButton.render(r);
		ffButton.render(r);
		settingsButton.render(r);
		
		
		// Text
		r.setColor(Color.BLACK);
		if (tbInfo != null) {
			r.setFont(new Font("Arial", Font.BOLD, 15));
			r.drawString(tbInfo.getTitle(), 485, 105);
			
			String costStr = "$" + (int)(tbInfo.getBaseCost() * costModifier);
			int costStrWidth = r.getFontMetrics().stringWidth(costStr);
			r.drawString(costStr, 635 - costStrWidth, 105);
			
			r.setFont(new Font("Arial", Font.PLAIN, 10));
			r.drawWrappedString(tbInfo.getDescription(), 490, 120, 150);
 		}
		
		r.setFont(new Font("Arial", Font.BOLD, 15));
		if (selectedTower != null)
			r.drawString(selectedTower.getName(), 485, 155);
		
		
		String moneyStr = "$" + money;
		int moneyStrWidth = r.getFontMetrics().stringWidth(moneyStr);
		r.drawString(moneyStr, 475 - moneyStrWidth, 15);
		
	}
	
	public void selectTower(Tower t) { 
		if (selectedTower != null)
			selectedTower.setSelected(false);
		selectedTower = t;
		upgradePanel.selectTower(t);
	}
	
	public void setCurrentTowerButtonInfo(BuyInfo info) { tbInfo = info; }
	
	public long getMoney() { return money; }
	
	public float getCostModifier() { return costModifier; }
	
	public void addMoney(long add) { money += add; }
	
	public void subtractMoney(long subtract) { money -= subtract; }
	
}
