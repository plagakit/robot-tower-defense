package ui;

import java.awt.Color;
import java.awt.Font;

import components.BoxBounds;
import gameobjects.BuyInfo;
import general.Vector2;
import graphics.Renderer;
import scenes.GameScene;
import towers.Tower;
import towers.UpgradePath;

public class UpgradePanel {
	
	private Shop shop;
	private UpgradePath path;

	private Button upgradeButtonLeft, upgradeButtonRight;
	
	public UpgradePanel(GameScene scene, Shop shop) {
		this.shop = shop;
		
		upgradeButtonLeft = new Button(scene, "UpgradeButton1", new Vector2(485, 160)) {
			Button init() {
				bounds = new BoxBounds(this, 0, 0, 150, 75);
				return this;
			}
			@Override
			protected void onClick() {
				upgradeLeft();
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
		
		upgradeButtonRight = new Button(scene, "UpgradeButton2", new Vector2(485, 240)) {
			Button init() {
				bounds = new BoxBounds(this, 0, 0, 150, 75);
				return this;
			}
			@Override
			protected void onClick() {
				upgradeRight();
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
	}
	
	private void upgradeLeft() {
		
		
		BuyInfo info = path.getNextLeftUpgrade().getBuyInfo();
		int cost = (int)(info.getBaseCost() * shop.getCostModifier());
		
		if (shop.getMoney() >= cost) {
			shop.subtractMoney(cost);
			path.advanceLeft();
		}
	}
	
	private void upgradeRight() {
		BuyInfo info = path.getNextRightUpgrade().getBuyInfo();
		int cost = (int)(info.getBaseCost() * shop.getCostModifier());
		
		if (shop.getMoney() >= cost) {
			shop.subtractMoney(cost);
			path.advanceRight();
		}
	}
	
	public void update() {
		if (path != null) {
			upgradeButtonLeft.update();
			upgradeButtonRight.update();
		}
	}

	public void render(Renderer r) {
		
		// TODO remove magic numbers
		r.setStroke(1);
		r.setColor(Color.BLACK);
		r.drawRect(485, 160, 150, 75);
		r.drawRect(485, 240, 150, 75);
		
		if (path != null) {
			int leftState = path.getNextLeftState();
			if (leftState == 0) {
				BuyInfo info = path.getNextLeftUpgrade().getBuyInfo();
				boolean canBuy = shop.getMoney() >= info.getBaseCost() * shop.getCostModifier();
				
				r.setFont(new Font("Arial", Font.BOLD, 13));
				r.setColor(Color.BLACK);
				r.drawString(info.getTitle(), 490, 175);
				
				String costStr = "$" + (int)(info.getBaseCost() * shop.getCostModifier());
				int costStrWidth = r.getFontMetrics().stringWidth(costStr);
				r.drawString(costStr, 630 - costStrWidth, 175);
				
				r.setFont(new Font("Arial", Font.PLAIN, 10));
				r.drawWrappedString(info.getDescription(), 490, 190, 100);
			
				if (upgradeButtonLeft.hovering) {
					r.setColor(canBuy ? Color.GREEN : Color.RED);
					r.setTransparency(0.3f);
					r.fillRect(485, 160, 150, 75);
					r.setTransparency(1f);
				}
			} else if (leftState == 1) {
				r.setColor(Color.RED);
				r.setTransparency(0.3f);
				r.fillRect(485, 160, 150, 75);
				r.setTransparency(1f);
			}
			
			
			int rightState = path.getNextRightState();
			if (rightState == 0) {
				BuyInfo info = path.getNextRightUpgrade().getBuyInfo();
				boolean canBuy = shop.getMoney() >= info.getBaseCost() * shop.getCostModifier();
				
				r.setColor(Color.BLACK);
				r.setFont(new Font("Arial", Font.BOLD, 13));
				r.drawString(info.getTitle(), 490, 255);
				
				String costStr = "$" + (int)(info.getBaseCost() * shop.getCostModifier());
				int costStrWidth = r.getFontMetrics().stringWidth(costStr);
				r.drawString(costStr, 630 - costStrWidth, 255);
				
				r.setFont(new Font("Arial", Font.PLAIN, 10));
				r.drawWrappedString(info.getDescription(), 490, 270, 100);
			
				if (upgradeButtonRight.hovering) {
					r.setColor(canBuy ? Color.GREEN : Color.RED);
					r.setTransparency(0.3f);
					r.fillRect(485, 240, 150, 75);
					r.setTransparency(1f);
				}
			} else if (rightState == 1) {
				r.setColor(Color.RED);
				r.setTransparency(0.3f);
				r.fillRect(485, 240, 150, 75);
				r.setTransparency(1f);
			}
			
		}
		
	}
	
	public void selectTower(Tower t) {
		if (t == null) path = null;
		else path = t.getUpgradePath();
	}

}
