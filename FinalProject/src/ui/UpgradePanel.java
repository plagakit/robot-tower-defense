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
	
	private Button upgradeButton1;
	
	public UpgradePanel(GameScene scene, Shop shop) {
		this.shop = shop;
		
		upgradeButton1 = new Button(scene, "UpgradeButton1", new Vector2(485, 160)) {
			Button init() {
				bounds = new BoxBounds(this, 0, 0, 150, 75);
				return this;
			}
			@Override
			protected void onClick() {
				upgrade();
			}
			@Override
			protected void onMouseEnter() {}
			@Override
			protected void onMouseExit() {}
		}.init();
	}
	
	private void upgrade() {
		System.out.println("upgraded");
	}

	public void update() {
		upgradeButton1.update();
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
			
				if (upgradeButton1.hovering) {
					r.setColor(canBuy ? Color.GREEN : Color.RED);
					r.setTransparency(0.3f);
					r.fillRect(485, 160, 150, 75);
					r.setTransparency(1f);
				}
			}
			
		}
		
	}
	
	public void setCurrentTowerSelection(Tower t) {
		selectedTower = t;
	}

}
