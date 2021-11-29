package ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import components.BoxBounds;
import gameobjects.BuyInfo;
import general.Vector2;
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

	public void render(Graphics2D g) {
		
		// TODO remove magic numbers
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		g.drawRect(485, 160, 150, 75);
		g.drawRect(485, 240, 150, 75);
		
		g.setFont(new Font("Arial", Font.BOLD, 13));
		if (selectedTower != null) {
			int leftState = selectedTower.getUpgradePath().getNextLeftState();
			
			if (leftState == 0) {
				BuyInfo info = selectedTower.getUpgradePath().getNextLeftUpgrade().getBuyInfo();
				boolean canBuy = shop.getMoney() >= info.getBaseCost() * shop.getCostModifier();
				g.drawString(info.getTitle(), 490, 175);
				
				String costStr = "$" + (int)(info.getBaseCost() * shop.getCostModifier());
				int costStrWidth = g.getFontMetrics().stringWidth(costStr);
				g.drawString(costStr, 630 - costStrWidth, 175);
				
				g.setFont(new Font("Arial", Font.PLAIN, 10));
				shop.drawWrappedString(g, info.getDescription(), 490, 190, 100);
			
				if (upgradeButton1.hovering) {
					g.setColor(canBuy ? Color.GREEN : Color.RED);
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
					g.fillRect(485, 160, 150, 75);
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				}
			}
			
		}
		
	}
	
	public void setCurrentTowerSelection(Tower t) {
		selectedTower = t;
	}

}
