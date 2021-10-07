package gameobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.BoxBounds;
import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {
	
	Tower tower;
	Constructor<? extends Tower> towerConstructor;
	
	public TowerButton(GameScene scene, Vector2 pos, Tower tower, String iconName) {
		super(scene, "TowerButton", pos);
		
		sprite = scene.getGame().getSpriteManager().getSprite(iconName);
		bounds = new BoxBounds(this, sprite);
		
		this.tower = tower;
		try {
			
			towerConstructor = tower.getClass().getConstructor(
					new Class[]{GameScene.class, Vector2.class});
			
		} catch (NoSuchMethodException | SecurityException e) { e.printStackTrace(); }
	
	}
	
	@Override
	protected void onHover() {
		BuyInfo info = tower.getInfo();
		System.out.format("%s %s %d\n", info.getTitle(), info.getDescription(), info.getBaseCost());
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
