package gameobjects;

import java.awt.Graphics2D;

import components.CircleBounds;
import components.PhysicsComponent;
import general.Game;
import general.Vector2;
import scenes.GameScene;

public class Bloon extends GameObject {

	private CircleBounds bounds;
	private PhysicsComponent physicsComponent;
	
	public Bloon(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite("redbloon.png");
		bounds = new CircleBounds(this, 16);
		physicsComponent = new PhysicsComponent(this);
	}

	int updateNum = 0;
	
	@Override
	public void update() {
		
		updateNum += scene.getGame().getTimeScale();
		vel.x = (float)Math.sin(updateNum / 50.0);
		vel.y = (float)Math.cos(updateNum / 50.0);
		
		physicsComponent.update();
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (Game.DEBUG)
			bounds.debugRender(g);
	}

	public void despawn() {
		active = false;
		scene.getBloons().remove(this);
	}
	
	public CircleBounds getBounds() { return bounds; }
	
}
