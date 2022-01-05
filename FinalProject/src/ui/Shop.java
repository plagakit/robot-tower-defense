package ui;

import java.awt.Color;
import java.awt.Font;

import components.BoxBounds;
import gameobjects.BuyInfo;
import general.Vector2;
import graphics.Renderer;
import graphics.Sprite;
import scenes.GameScene;
import towers.FireRobot;
import towers.IceRobot;
import towers.LightningRobot;
import towers.Robot;
import towers.ScissorRobot;
import towers.Tower;

public class Shop {
	
	private long money;
	private float costModifier;
	
	private TowerButton[] towerButtons;
	private BuyInfo tbInfo;
	
	private Tower selectedTower;
	private Button sellButton;
	private UpgradePanel upgradePanel;
	
	private Button playButton; 
	private Button ffButton;
	private Button settingsButton;

	private final Color BG_COLOUR = new Color(207, 168, 114);
		
	public Shop(GameScene scene, long startingMoney, float costModifier) {
		this.money = startingMoney;
		this.costModifier = costModifier;
		
		// Tower buttons
		towerButtons = new TowerButton[] {
			new TowerButton(scene, this, new Vector2(515, 24), new Robot(scene, null), "roboticon.png"),
			new TowerButton(scene, this, new Vector2(560, 24), new ScissorRobot(scene, null), "scissoricon.png"),
			new TowerButton(scene, this, new Vector2(605, 24), new FireRobot(scene, null), "fireicon.png"),
			new TowerButton(scene, this, new Vector2(535, 68), new IceRobot(scene, null), "iceicon.png"),
			new TowerButton(scene, this, new Vector2(580, 68), new LightningRobot(scene, null), "lightningicon.png"),
			//new TowerButton(scene, this, new Vector2(605, 68), new Robot(scene, null), "roboticon.png")
		};

		// Sell button & upgrade panel
		sellButton = new Button(scene, "SellButton", new Vector2(597, 323)) {
			Button init() {
				bounds = new BoxBounds(this, 0, 0, 38, 34);
				return this;
			}
			@Override
			public void render(Renderer r) {
				r.setColor(new Color(255, 103, 92));
				r.fillRect(pos.x, pos.y, 38, 34);
				r.setColor(Color.WHITE);
				r.setFont(new Font("Arial", Font.BOLD, 13));
				r.drawString("SELL", pos.x + 2, pos.y + 13);
				r.setFont(new Font("Arial", Font.BOLD, 10));
				r.drawString("$"+selectedTower.getSellPrice(), pos.x + 1, pos.y + 28);
			}
			@Override
			protected void onClick() {
				selectedTower.sell();
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
		
		upgradePanel = new UpgradePanel(scene, this);
		
		// Lower buttons
		playButton = new Button(scene, "PlayButton", new Vector2(538, 340)) {
			private boolean on;
			private Sprite onSprite, offSprite;
			Button init() {
				onSprite = scene.getGame().getSpriteManager().getSprite("playon.png");
				offSprite = scene.getGame().getSpriteManager().getSprite("playoff.png");
				sprite = onSprite;
				bounds = new BoxBounds(this, sprite);
				on = true;
				return this;
			}
			@Override
			public void update() { 
				super.update();
				if (!on && !scene.inRound()) {
					on = true;
					sprite = onSprite;
				}
			}
			@Override
			protected void onClick() {
				if (on) {
					sprite = offSprite;
					on = false;
					scene.startNextRound();
				}
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
		
		ffButton = new Button(scene, "FastForwardButton", new Vector2(500, 340)) {
			private boolean on;
			private final int DEFAULT_SPEED = 1, FF_SPEED = 2;
			private Sprite sprite1, sprite2;
			Button init() {
				sprite1 = scene.getGame().getSpriteManager().getSprite("fastforwardoff.png");
				sprite2 = scene.getGame().getSpriteManager().getSprite("fastforwardon.png");
				sprite = sprite1;
				bounds = new BoxBounds(this, sprite);
				return this;
			}
			@Override
			protected void onClick() {
				sprite = on ? sprite1 : sprite2;
				scene.getGame().setTimeScale(on ? DEFAULT_SPEED : FF_SPEED);
				on = !on;
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
		
		settingsButton = new Button(scene, "SettingsButton", new Vector2(576, 340)) {
			Button init() {
				sprite = scene.getGame().getSpriteManager().getSprite("settings.png");
				bounds = new BoxBounds(this, sprite);
				return this;
			}
			@Override
			protected void onClick() {
				scene.setPaused(true);
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
	}
	
	public void update() {
		for (Button b : towerButtons)
			b.update();
		
		if (selectedTower != null) {
			sellButton.update();
			upgradePanel.update();
		}
		
		playButton.update();
		ffButton.update();
		settingsButton.update();
	}
	
	public void render(Renderer r) {
		// TODO remove magic numbers
		// Bg & outline
		r.setColor(BG_COLOUR);
		r.fillRect(480, 0, 160, 360);
		
		r.setStroke(2);
		r.setColor(Color.BLACK);
		r.drawLine(480, 0, 480, 360);
		r.drawLine(480, 140, 640, 140);
		
		// Buttons
		for (Button b : towerButtons)
			b.render(r);
		
		if (selectedTower != null) {
			sellButton.render(r);
			upgradePanel.render(r);
		}
		
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
