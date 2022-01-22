package gameobjects.bloons;

import java.util.UUID;

import game.Game;
import game.Timer;
import game.Vector2;
import gameobjects.GameObject;
import gameobjects.components.CircleBounds;
import gameobjects.components.PhysicsComponent;
import graphics.Renderer;
import graphics.Sprite;
import scenes.GameScene;

/** The Bloon class, representing a balloon and the main enemy
 * of the game. */
public class Bloon extends GameObject {
	
	private final BloonType type;
	private final int rank;
	private final String id;
	
	private final int RBE; // red bloon equivalent
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
	
	/** Creates a bloon with the specified parameters. */
	public Bloon(GameScene scene, Vector2 pos, BloonType type) {
		super(scene, "Bloon", pos);
		this.type = type;
		this.rank = type.rank;
		this.id = UUID.randomUUID().toString(); // gives a random id
		
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
	
	/** Creates a child bloon, with the same position as its parent.*/
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

	/** Handles collsion with a projectile by the amount of damage
	 * that the projectile would have dealt to it. Usually pops
	 * the bloon. */
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
			
			String damageSound = type == BloonType.CERAMIC ? "ceramicdamage.wav" : "moabdamage.wav";
			scene.getGame().getAudioManager().playSound(damageSound);
		}
		
		if (currentHealth <= 0)
			pop();
		
		return true;
	}
	
	/** Spawns child bloons at its position. */
	private void spawnChildren() {
		for (BloonType childType : type.children) {
			Bloon newBloon = new Bloon(this, childType);
			scene.getBloons().add(newBloon);
		}
	}
	
	/** Pops the bloon and plays all its related animations and sounds. */
	private void pop() {
		sprite = popSprite;
		popAnimTimer = new Timer(scene.getGame(), 100);
		invulnerable = true;
		vel = Vector2.zero();
		playRandomPopSound();
		spawnChildren();
		scene.onBloonPopped();
	}
	
	/** Plays one of three random pop sounds. */
	private void playRandomPopSound() {
		int randomNumFromOneToFour = (int)(Math.random() * 4 + 1);
		String sound = "pop" + randomNumFromOneToFour + ".wav";
		
		if (type == BloonType.MOAB)
			sound = "moabpop.wav";
		
		scene.getGame().getAudioManager().playSound(sound);
	}

	/** Removes the bloon from the game scene. */
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
