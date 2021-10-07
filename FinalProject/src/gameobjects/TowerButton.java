package gameobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.BoxBounds;
import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {
	
	Class<? extends Tower> towerClass;
	Constructor<? extends Tower> towerConstructor;
	
	public TowerButton(GameScene scene, Vector2 pos, Class<? extends Tower> towerClass, String iconName) {
		super(scene, "TowerButton", pos);
		
		sprite = scene.getGame().getSpriteManager().getSprite(iconName);
		bounds = new BoxBounds(this, sprite);
		
		this.towerClass = towerClass;
		try {
			
			towerConstructor = towerClass.getConstructor(
					new Class[]{GameScene.class, Vector2.class});
			
		} catch (NoSuchMethodException | SecurityException e) { e.printStackTrace(); }
	
	}
	
	@Override
	protected void onHover() {
		try {
			
			String towerName = (String)towerClass.getMethod("getTowerName").invoke(null);
			String description = (String)towerClass.getMethod("getDescription").invoke(null);
			Integer baseCost = (Integer)towerClass.getMethod("getBaseCost").invoke(null);
			System.out.format("%s %s %d\n", towerName, description, baseCost);
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
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
