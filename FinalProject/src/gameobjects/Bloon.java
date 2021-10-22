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
	
	private final BloonType type;
	
	private final float SPEED;
	
	public Bloon(GameScene scene, Vector2 pos, BloonType type) {
		super(scene, "Bloon", pos);
		this.type = type;
		
		name = type.name;
		sprite = scene.getGame().getSpriteManager().getSprite(type.spritePath);
		bounds = new CircleBounds(this, sprite.getWidth());
		physicsComponent = new PhysicsComponent(this);
		this.SPEED = type.speed;
		
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
