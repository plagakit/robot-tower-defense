package gameobjects;

import java.awt.Graphics2D;

import components.CircleBounds;
import components.PhysicsComponent;
import general.Game;
import general.Vector2;
import projectiles.Projectile;
import scenes.GameScene;

public class Bloon extends GameObject {

	private CircleBounds bounds;
	private PhysicsComponent physicsComponent;
	
	private final float SPEED = 0.25f;
	
	public Bloon(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite("redbloon.png");
		bounds = new CircleBounds(this, 16);
		physicsComponent = new PhysicsComponent(this);
		
		vel.x = SPEED;
	}

	
	@Override
	public void update() {	
		physicsComponent.update();
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (Game.DEBUG)
			bounds.debugRender(g);
	}
	
	public void handleCollision(Projectile p) {
		pop();
	}
	
	private void spawnChildren() {
		
	}
	
	private void pop() {
		spawnChildren();
		// playSfx();
		despawn();
	}

	public void despawn() {
		active = false;
		scene.getBloons().remove(this);
	}
	
	public CircleBounds getBounds() { return bounds; }
	
}
