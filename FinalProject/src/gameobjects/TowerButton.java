package gameobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.Box;
import components.RenderComponent;
import general.Game;
import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {
	
	Class<? extends Tower> towerClass;
	Constructor<? extends Tower> towerConstructor;
	
	RenderComponent renderComponent;
	
	public TowerButton(GameScene scene, Vector2 pos, Class<? extends Tower> towerClass, String iconName) {
		super(scene, "TowerButton", pos);
		
		renderComponent = new RenderComponent(this);
		sprite = scene.getGame().getSpriteManager().getSprite(iconName);
		bounds = new Box(this, -10, -10, 20, 20);
		
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
			scene.addGameObject(towerConstructor.newInstance(scene, new Vector2(pos)));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
	}
	
	@Override 
	public void update() {
		super.update();
	}

	@Override
	public void render(Graphics2D g) {

		renderComponent.render(g, sprite);
		
		if (Game.DEBUG) {
			int gameScale = scene.getGame().getScale();
			g.setColor(Color.GREEN);
			g.setStroke(new BasicStroke(gameScale));
			g.drawRect(
					(int)(pos.x + bounds.x) * gameScale, 
					(int)(pos.y + bounds.y)* gameScale, 
					bounds.width * gameScale, 
					bounds.height * gameScale);
		}
	}

}
