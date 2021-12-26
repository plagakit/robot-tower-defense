package gameobjects;

import java.util.UUID;

import components.CircleBounds;
import components.PhysicsComponent;
import general.Game;
import general.Timer;
import general.Vector2;
import graphics.Renderer;
import graphics.Sprite;
import scenes.GameScene;

public class Bloon extends GameObject {
	
	private final BloonType type;
	private final int rank;
	private final String id;
	
	private final int RBE;
	private final int health;
	private int currentHealth;
	private final float speed;
	private float distanceTravelled;
	
	private Sprite popSprite;
	private Timer popAnimTimer;
	private Sprite damagedSprite1;
	private Sprite damagedSprite2;
	
	private boolean invulnerable;
	private CircleBounds bounds;
	private PhysicsComponent physicsComponent;
	
	private Vector2[] trackPoints;
	private int goalPointIndex;
	
	public Bloon(GameScene scene, Vector2 pos, BloonType type) {
		super(scene, "Bloon", pos);
		this.type = type;
		this.rank = type.rank;
		this.id = UUID.randomUUID().toString();
		
		name = type.name;
		health = type.health;
		currentHealth = health;
		RBE = type.RBE;
		speed = type.speed * scene.getSpeedModifier();
		
		sprite = scene.getGame().getSpriteManager().getSprite(type.spritePath);
		popSprite = scene.getGame().getSpriteManager().getSprite("pop.png");
		bounds = new CircleBounds(this, (int)(sprite.getWidth() * 0.5));
		physicsComponent = new PhysicsComponent(this);
		
		if (type == BloonType.CERAMIC) {
			damagedSprite1 = scene.getGame().getSpriteManager().getSprite("ceramicblooncracked1.png");
			damagedSprite2 = scene.getGame().getSpriteManager().getSprite("ceramicblooncracked2.png");
		} else if (type == BloonType.MOAB) {
			damagedSprite1 = scene.getGame().getSpriteManager().getSprite("moabbloondamaged1.png");
			damagedSprite2 = scene.getGame().getSpriteManager().getSprite("moabbloondamaged2.png");
		}
		
		trackPoints = scene.getTrack().getPoints();
		goalPointIndex = 0;
		distanceTravelled = 0;
	}
	
	public Bloon(Bloon parent, BloonType type) {
		this(parent.getGameScene(), parent.getPos(), type);
		this.goalPointIndex = parent.goalPointIndex;
		this.distanceTravelled = parent.distanceTravelled;
		
		vel = Vector2.multiply(Vector2.direction(pos, trackPoints[goalPointIndex]), speed);
	}

	
	@Override
	public void update() {	
		
		if (type == BloonType.MOAB)
			rotation = Vector2.lookAtAngle(Vector2.zero(), vel);
		
		if (Vector2.distance(pos, trackPoints[goalPointIndex]) <= 3) {
			goalPointIndex++;
			if (goalPointIndex == trackPoints.length) {
				scene.onLeak(RBE);
				despawn();
			} else {
				vel = Vector2.multiply(Vector2.direction(pos, trackPoints[goalPointIndex]), speed);
			}
		}
		
		if (invulnerable) {
			if (popAnimTimer.isDone())
				despawn();
			else popAnimTimer.update();
		}
		
		distanceTravelled += speed;
		physicsComponent.update();
	}
	
	@Override
	public void render(Renderer r) {
		super.render(r);
		if (Game.DEBUG)
			bounds.debugRender(r);
	}

	public boolean handleCollision(int damage) {
		if (invulnerable || !active)
			return false;
		
		currentHealth -= damage;
		
		if (type == BloonType.CERAMIC || type == BloonType.MOAB) {
			double percent = currentHealth / (double)health;
			if (percent <= 0.66 && percent >= 0.33)
				sprite = damagedSprite1;
			else if (percent < 0.33)
				sprite = damagedSprite2;
		}
		
		if (currentHealth <= 0)
			pop();
		
		return true;
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
	
	public float getDistanceTravelled() { return distanceTravelled; }
	
	public int getRank() { return rank; }
	
	public String getID() { return id; }
	
}
