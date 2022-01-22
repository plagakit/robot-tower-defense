package gameobjects.ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import game.Vector2;
import gameobjects.components.BoxBounds;
import gameobjects.towers.Tower;
import scenes.GameScene;

/** A button used in the shop to purchase and place towers, and
 * also to display their info and price. Uses java reflect to create
 * tower objects from their class.
 * @see https://docs.oracle.com/javase/tutorial/reflect/ */
public class TowerButton extends Button {
	
	private Shop shop;
	
	private Tower tower;
	private Constructor<? extends Tower> towerConstructor;
	
	/** Creates a tower button with the specified tower as its product. */
	public TowerButton(GameScene scene, Shop shop, Vector2 pos, Tower tower, String iconName) {
		super(scene, "TowerButton", pos);
		this.shop = shop;
		
		sprite = scene.getGame().getSpriteManager().getSprite(iconName);
		bounds = new BoxBounds(this, sprite);
		
		this.tower = tower;
		try {
			
			towerConstructor = tower.getClass().getConstructor(
					new Class[]{GameScene.class, Vector2.class});
			
		} catch (NoSuchMethodException | SecurityException e) { e.printStackTrace(); }
	
	}
	
	/** Creates a new tower using java reflect and the tower's constructor,
	 * and subtracts money from the shop. */
	private void spawnTower() {
		if (shop.getMoney() < shop.modifyPrice(tower.getInfo().getBaseCost()))
			return;
		
		try {
			Tower tower = towerConstructor.newInstance(scene, pos);
			scene.getTowers().add(tower);
			scene.getShop().selectTower(null);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
	
	}
	
	@Override
	protected void onMouseEnter() {
		shop.setCurrentTowerButtonInfo(tower.getInfo());
	}
	
	@Override
	protected void onMouseExit() {
		shop.setCurrentTowerButtonInfo(null);
	}

	@Override
	protected void onClick() {
		spawnTower();
	}

}
