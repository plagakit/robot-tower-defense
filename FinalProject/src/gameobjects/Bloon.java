package gameobjects;

import java.awt.Graphics2D;
import java.util.UUID;

import components.CircleBounds;
import components.PhysicsComponent;
import general.Game;
import general.Vector2;
import graphics.Sprite;
import projectiles.Projectile;
import scenes.GameScene;

public class Bloon extends GameObject {

	private CircleBounds bounds;
	private PhysicsComponent physicsComponent;
	
	private final BloonType type;
	private final String id;
	
	private Sprite damagedSprite1;
	private Sprite damagedSprite2;
	
	private final int health;
	private int currentHealth;
	private final float speed;
	
	public Bloon(GameScene scene, Vector2 pos, BloonType type, String id) {
		super(scene, "Bloon", pos);
		this.type = type;
		this.id = id;
		
		name = type.name;
		health = type.health;
		currentHealth = health;
		speed = type.speed;
		
		sprite = scene.getGame().getSpriteManager().getSprite(type.spritePath);
		bounds = new CircleBounds(this, (int)(sprite.getWidth() * 0.5));
		physicsComponent = new PhysicsComponent(this);
		
		if (type == BloonType.CERAMIC) {
			damagedSprite1 = scene.getGame().getSpriteManager().getSprite("ceramicblooncracked1.png");
			damagedSprite2 = scene.getGame().getSpriteManager().getSprite("ceramicblooncracked2.png");
		}
		
		vel.x = speed;
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
		currentHealth -= p.getDamage();
		
		if (type == BloonType.CERAMIC) {
			if (currentHealth >= 3)
				sprite = damagedSprite1;
			else if (currentHealth < 3)
				sprite = damagedSprite2;
		}
		
		if (currentHealth <= 0)
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
		scene.onBloonPopped();
		// startAnimTimer
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
