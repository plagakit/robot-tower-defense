package gameobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.BoxBounds;
import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {
	
	private Shop shop;
	
	private Tower tower;
	private Constructor<? extends Tower> towerConstructor;
	
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
		try {
			Tower tower = towerConstructor.newInstance(scene, new Vector2(pos));
			scene.getTowers().add(tower);
			scene.setTowerSelection(null);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
	}

}
