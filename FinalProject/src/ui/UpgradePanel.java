package ui;

import java.awt.Color;
import java.awt.Font;

import components.BoxBounds;
import gameobjects.BuyInfo;
import general.Game;
import general.Vector2;
import graphics.Renderer;
import scenes.GameScene;
import towers.Tower;
import towers.UpgradePath;

public class UpgradePanel {
	

	private UpgradePath path;
	private Subpanel panel1, panel2;
	
	public UpgradePanel(GameScene scene, Shop shop) {
		panel1 = new Subpanel(scene, shop, this, 0, new Vector2(485, 160));
		panel2 = new Subpanel(scene, shop, this, 1, new Vector2(485, 240));
	}
	
	public void update() {
		if (path != null) {
			panel1.update();
			panel2.update();
		}
	}

	public void render(Renderer r) {
		if (path != null) {
			panel1.render(r);
			panel2.render(r);
		}
	}
	
	public void selectTower(Tower t) {
		if (t == null) 
			path = null;
		else 
			path = t.getUpgradePath();
		
		panel1.selectPath(path);
		panel2.selectPath(path);
	}

}


class Subpanel {
	
	private Shop shop;
	
	private UpgradePath path;
	private int branchNum;
	
	private Button button;
	private Vector2 pos;
	private final int WIDTH = 150;
	private final int HEIGHT = 75;
	
	public Subpanel(GameScene scene, Shop shop, UpgradePanel parentPanel, int branchNum, Vector2 pos) {
		this.shop = shop;
		this.branchNum = branchNum;
		this.pos = pos;
		
		button = new Button(scene, "UpgradeButton" + branchNum, pos) {
			Button init() {
				bounds = new BoxBounds(this, 0, 0, WIDTH, HEIGHT);
				return this;
			}
			@Override
			protected void onClick() {
				upgrade();
			}
			protected void onMouseEnter() {}
			protected void onMouseExit() {}
		}.init();
	}
	
	private void upgrade() {
		boolean success = path.advanceToNextUpgrade(branchNum);
		if (Game.DEBUG)
			System.out.format("Upgrade in branch %d successful: %b\n", branchNum, success);
	}
	
	public void update() {
		button.update();
	}
	
	public void render(Renderer r) {
		
		r.setStroke(1);
		r.setColor(Color.BLACK);
		r.drawRect(pos.x, pos.y, WIDTH, HEIGHT);
		
		UpgradePath.State state = path.getNextState(branchNum);
		if (state == UpgradePath.State.OPEN) {
			BuyInfo info = path.getNextUpgrade(branchNum).getBuyInfo();
			boolean canBuy = shop.getMoney() >= info.getBaseCost() * shop.getCostModifier();
			
			r.setFont(new Font("Arial", Font.BOLD, 13));
			r.setColor(Color.BLACK);
			r.drawString(info.getTitle(), pos.x + 5, pos.y + 15);
			
			String costStr = "$" + (int)(info.getBaseCost() * shop.getCostModifier());
			int costStrWidth = r.getFontMetrics().stringWidth(costStr);
			r.drawString(costStr, pos.x + 145 + costStrWidth, pos.y + 15);
			
			r.setFont(new Font("Arial", Font.PLAIN, 10));
			r.drawWrappedString(info.getDescription(), pos.x + 5, pos.y + 30, 100);
		
			if (button.hovering) {
				r.setColor(canBuy ? Color.GREEN : Color.RED);
				r.setTransparency(0.3f);
				r.fillRect(pos.x, pos.y, WIDTH, HEIGHT);
				r.setTransparency(1f);
			}
		} 
		else if (state == UpgradePath.State.LOCKED) {
			r.setColor(Color.RED);
			r.setTransparency(0.3f);
			r.fillRect(pos.x, pos.y, WIDTH, HEIGHT);
			r.setTransparency(1f);
			
			r.setColor(Color.BLACK);
			r.setFont(new Font("Arial", Font.BOLD, 13));
			r.drawString("Path Locked", pos.x, pos.y);
		}
	}

	public void selectPath(UpgradePath path) {
		this.path = path;
	}
}