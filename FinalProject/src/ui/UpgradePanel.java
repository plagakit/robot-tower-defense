package ui;

import java.awt.Color;
import java.awt.Font;

import components.BoxBounds;
import gameobjects.BuyInfo;
import general.Vector2;
import graphics.Renderer;
import scenes.GameScene;
import towers.Tower;

public class UpgradePanel {

	private Shop shop;
	private Tower selectedTower;
	
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
		System.out.println("upgraded left");
	}
	
	private void upgradeRight() { 
		System.out.println("upgraded right");
	}

	public void update() {
		upgradeButtonLeft.update();
	}

	public void render(Renderer r) {
		
		// TODO remove magic numbers
		r.setStroke(1);
		r.setColor(Color.BLACK);
		r.drawRect(485, 160, 150, 75);
		r.drawRect(485, 240, 150, 75);
		
		r.setFont(new Font("Arial", Font.BOLD, 13));
		if (selectedTower != null) {
			int leftState = selectedTower.getUpgradePath().getNextLeftState();
			
			if (leftState == 0) {
				BuyInfo info = selectedTower.getUpgradePath().getNextLeftUpgrade().getBuyInfo();
				boolean canBuy = shop.getMoney() >= info.getBaseCost() * shop.getCostModifier();
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
			
		}
		
	}
	
	public void setCurrentTowerSelection(Tower t) {
		selectedTower = t;
	}

}
