package gameobjects;

import java.awt.Graphics2D;
import java.util.UUID;

import components.CircleBounds;
import components.PhysicsComponent;
import general.Game;
import general.Timer;
import general.Vector2;
import graphics.Sprite;
import projectiles.Projectile;
import scenes.GameScene;

public class Bloon extends GameObject {

	private boolean invulnerable;
	private CircleBounds bounds;
	private PhysicsComponent physicsComponent;
	
	private Sprite popSprite;
	private Timer popAnimTimer;
	
	private final BloonType type;
	private final String id;
	
	private Sprite damagedSprite1;
	private Sprite damagedSprite2;
	
	private final int health;
	private int currentHealth;
	private final float speed;
	private int distanceTravelled;
	
	public Bloon(GameScene scene, Vector2 pos, BloonType type, String... id) {
		super(scene, "Bloon", pos);
		this.type = type;
		this.id = id.length == 0 ? UUID.randomUUID().toString() : id[0];
		
		name = type.name;
		health = type.health;
		currentHealth = health;
		speed = type.speed;
		
		sprite = scene.getGame().getSpriteManager().getSprite(type.spritePath);
		popSprite = scene.getGame().getSpriteManager().getSprite("pop.png");
		bounds = new CircleBounds(this, (int)(sprite.getWidth() * 0.5));
		physicsComponent = new PhysicsComponent(this);
		
		if (type == BloonType.CERAMIC) {
			damagedSprite1 = scene.getGame().getSpriteManager().getSprite("ceramicblooncracked1.png");
			damagedSprite2 = scene.getGame().getSpriteManager().getSprite("ceramicblooncracked2.png");
		}
		
		distanceTravelled = 0;
		vel.x = speed;
	}
	
	public Bloon(Bloon parent, BloonType type) {
		this(parent.getGameScene(), parent.getPos(), type, parent.id);
		this.distanceTravelled = parent.distanceTravelled + 1;
	}

	
	@Override
	public void update() {	
		
		if (invulnerable) {
			if (popAnimTimer.isDone())
				despawn();
			else popAnimTimer.update();
		}
		
		// TODO Change distance travelled
		distanceTravelled += speed;
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
			Bloon newBloon = new Bloon(this, childType);
			scene.getBloons().add(newBloon);
		}
	}
	
	private void pop() {
		sprite = popSprite;
		popAnimTimer = new Timer(scene.getGame(), 100);
		invulnerable = true;
		vel = Vector2.zero();
		// playSfx();
		spawnChildren();
		scene.onBloonPopped();
	}

	public void despawn() {
		active = false;
		scene.getBloons().remove(this);
	}
	
	public CircleBounds getBounds() { return bounds; }
	
	public boolean isInvulnerable() { return invulnerable; }
	
	public int getDistanceTravelled() { return distanceTravelled; }
	
	public String getID() { return id; }
	
}
