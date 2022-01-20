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

public abstract class Projectile extends GameObject {

	protected PhysicsComponent physicsComponent;
	protected CircleBounds bounds;
	
	protected final Timer despawnTimer;
	protected List<String> hitList;
	
	protected int damage;
	protected int pierce;
	protected int currentPierce;
	
	public Projectile() {
		super(null, "EmptyProjectile", null);
		despawnTimer = null;
	}
	
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
		
		b.handleCollision(damage);
		
		if (currentPierce == 0)
			despawn();
	}

	protected void despawn() {
		active = false;
		scene.getProjectiles().remove(this);
	}
	
	public int getDamage() { return damage; }
}
