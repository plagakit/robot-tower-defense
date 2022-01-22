package gameobjects.projectiles;

import java.util.ArrayList;
import java.util.List;

import game.Timer;
import game.Vector2;
import gameobjects.GameObject;
import gameobjects.bloons.Bloon;
import gameobjects.components.CircleBounds;
import gameobjects.components.PhysicsComponent;
import scenes.GameScene;

/** An abstract class that defines projectiles, which are moving
 * objects that pop bloons. */
public abstract class Projectile extends GameObject {

	protected PhysicsComponent physicsComponent;
	protected CircleBounds bounds;
	
	protected final Timer despawnTimer;
	protected List<String> hitList;
	
	protected int damage;
	protected int pierce;
	protected int currentPierce;
	
	/** The constructor for an empty projectile, only for cloning. */
	public Projectile() {
		super(null, "EmptyProjectile", null);
		despawnTimer = null;
	}
	
	/** The constructor for a projectile. */
	public Projectile(GameScene scene, String name, Vector2 pos, ProjectileData data) {
		super(scene, name, pos);
		
		String spriteName = data.getSpritePath();
		if (spriteName != null) {
			sprite = scene.getGame().getSpriteManager().getSprite(spriteName);
			bounds = new CircleBounds(this, Math.min(sprite.getWidth(), sprite.getHeight()));
		} else {
			sprite = null;
			bounds = new CircleBounds(this, 0);
		}
		
		damage = data.getDamage();
		pierce = data.getPierce();
		currentPierce = pierce;
		
		despawnTimer = new Timer(scene.getGame(), data.getDespawnTime());
		hitList = new ArrayList<String>();
		
		physicsComponent = new PhysicsComponent(this);
	}
	
	/** An abstract copy constructor for a projectile to initialize it. */
	public abstract Projectile init(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data);

	@Override
	public void update() {
		
		for (Bloon b : scene.getBloons().getList()) {
			if (hitList.contains(b.getID()) || b.isInvulnerable())
				continue;
			
			if (bounds != null && bounds.collides(b.getBounds())) {
				//System.out.format("%s@%d collision w/ %s@%s\n", this.getName(), this.hashCode(), b.getName(), b.getID());
				handleCollision(b);
				hitList.add(b.getID());
			}
			
			if (!active)
				return;
		}
		
		despawnTimer.update();
		if (despawnTimer.isDone())
			despawn();

		physicsComponent.update();
	}
	
	/** Handles collision with a bloon by calling its method and
	 * decrementing the projectile's pierce. */
	protected void handleCollision(Bloon b) {
		currentPierce--;
		
		b.handleCollision(damage);
		
		if (currentPierce == 0)
			despawn();
	}

	/** Removes the projectile from the game scene. */
	protected void despawn() {
		active = false;
		scene.getProjectiles().remove(this);
	}
	
	public int getDamage() { return damage; }
}
