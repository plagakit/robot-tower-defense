package gameobjects;

import java.awt.Graphics2D;
import java.util.UUID;

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
	private final String id;
	
	private final float SPEED;
	
	public Bloon(GameScene scene, Vector2 pos, BloonType type, String id) {
		super(scene, "Bloon", pos);
		this.type = type;
		this.id = id;
		
		name = type.name;
		sprite = scene.getGame().getSpriteManager().getSprite(type.spritePath);
		bounds = new CircleBounds(this, 16);
		physicsComponent = new PhysicsComponent(this);
		this.SPEED = type.speed;
		
		vel.x = SPEED;
	}
	
	public Bloon(GameScene scene, Vector2 pos, BloonType type) {
		this(scene, pos, type, UUID.randomUUID().toString());
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
		for (BloonType childType : type.children) {
			Bloon newBloon = new Bloon(scene, pos, childType, id);
			scene.getBloons().add(newBloon);
		}
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
	
	public String getID() { return id; }
	
}
