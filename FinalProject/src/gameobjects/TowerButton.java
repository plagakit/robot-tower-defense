package gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {
	
	Class<? extends Tower> towerClass;
	Constructor<? extends Tower> towerConstructor;
	Tower tower;
	
	public TowerButton(GameScene scene, Vector2 pos, Class<? extends Tower> towerClass, String iconName) {
		super(scene, "TowerButton", pos);
		sprite = scene.getGame().getSpriteManager().getSprite(iconName);
		
		this.towerClass = towerClass;
		try {
			
			towerConstructor = towerClass.getConstructor(
					new Class[]{GameScene.class, Vector2.class});
			
		} catch (NoSuchMethodException | SecurityException e) { e.printStackTrace(); }
	}

	@Override
	protected void onClick() {
		System.out.println("Clicked");
		try {
			tower = towerConstructor.newInstance(scene, new Vector2(pos));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
	}
	
	@Override public void update() {
		super.update();
		
		if (tower != null)
			tower.update();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)pos.x, (int)pos.y, 50, 50);
		
		if (tower != null)
			tower.render(g);
	}

}
