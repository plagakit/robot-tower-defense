package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import gameobjects.BuyInfo;
import general.Vector2;
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
	}
	
	public void update() {
		for (Button b : towerButtons)
			b.update();
		upgradePanel.update();
		playButton.update();
		ffButton.update();
	}
	
	public void render(Graphics2D g) {
		// TODO remove magic numbers
		// Bg & outline
		g.setColor(new Color(207, 168, 114));
		g.fillRect(480, 0, 160, 360);
		
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawLine(480, 0, 480, 360);
		g.drawLine(480, 140, 640, 140);
		
		// Buttons
		for (Button b : towerButtons)
			b.render(g);
		upgradePanel.render(g);
		playButton.render(g);
		ffButton.render(g);
		
		
		// Text
		g.setColor(Color.BLACK);
		if (tbInfo != null) {
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(tbInfo.getTitle(), 485, 105);
			
			String costStr = "$" + (int)(tbInfo.getBaseCost() * costModifier);
			int costStrWidth = g.getFontMetrics().stringWidth(costStr);
			g.drawString(costStr, 635 - costStrWidth, 105);
			
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			drawWrappedString(g, tbInfo.getDescription(), 490, 120, 150);
 		}
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		if (selectedTower != null)
			g.drawString(selectedTower.getName(), 485, 155);
		
		
		String moneyStr = "$" + money;
		int moneyStrWidth = g.getFontMetrics().stringWidth(moneyStr);
		g.drawString(moneyStr, 475 - moneyStrWidth, 15);
		
	}
	
	public void selectTower(Tower t) { 
		if (selectedTower != null)
			selectedTower.setSelected(false);
		selectedTower = t;
		upgradePanel.setCurrentTowerSelection(t);
	}
	
	public void setCurrentTowerButtonInfo(BuyInfo info) { tbInfo = info; }
	
	public long getMoney() { return money; }
	
	public float getCostModifier() { return costModifier; }
	
	public void addMoney(long add) { money += add; }
	
	public void subtractMoney(long subtract) { money -= subtract; }
	
	public void drawWrappedString(Graphics2D g, String s, int x, int y, int maxWidth) {
		FontMetrics fm = g.getFontMetrics();
		int height = fm.getHeight();
		int currentX = x;
		int currentY = y;
		
		for (String word : s.split(" ")) {
			word += " ";
			int width = fm.stringWidth(word);
			if (currentX + width > x + maxWidth) {
				currentY += height;
				currentX = x;
			}
			g.drawString(word, currentX, currentY);
			currentX += width;
		}
	}
	
}
