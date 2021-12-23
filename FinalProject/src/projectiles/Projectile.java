package projectiles;

import java.util.ArrayList;
import java.util.List;

import components.CircleBounds;
import components.PhysicsComponent;
import gameobjects.Bloon;
import gameobjects.GameObject;
import general.Timer;
import general.Vector2;
import scenes.GameScene;

public abstract class Projectile extends GameObject {

	private PhysicsComponent physicsComponent;
	private CircleBounds bounds;
	
	protected final Timer despawnTimer;
	private List<String> hitList;
	
	private int damage;
	private int pierce;
	private int currentPierce;
	
	public Projectile() {
		super(null, "EmptyProjectile", null);
		despawnTimer = null;
	}
	
	public Projectile(GameScene scene, String name, Vector2 pos, ProjectileData data) {
		super(scene, name, pos);
		
		String spriteName = data.getProjectileSpritePath();
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
	
	protected void handleCollision(Bloon b) {
		currentPierce--;
		
		b.handleCollision(this);
		
		if (currentPierce == 0)
			despawn();
	}

	protected void despawn() {
		active = false;
		scene.getProjectiles().remove(this);
	}
	
	public int getDamage() { return damage; }
}
