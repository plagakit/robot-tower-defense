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

public class Projectile extends GameObject {

	protected PhysicsComponent physicsComponent;
	protected CircleBounds bounds;
	
	public final static int DEFAULT_DESPAWN_TIME = 250;
	protected int despawnTime;
	protected final Timer despawnTimer;
	protected List<String> hitList;
	
	protected ProjectileType type;
	protected int damage;
	protected int pierce;
	protected int currentPierce;
	
	public Projectile(GameScene scene, String name, Vector2 pos, String spritePath, ProjectileType type, int damage, int pierce, int despawnTime) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite(spritePath);
		bounds = new CircleBounds(this, Math.min(sprite.getWidth(), sprite.getHeight()));
		this.type = type;
		this.damage = damage;
		this.pierce = pierce;
		currentPierce = pierce;
		
		despawnTimer = new Timer(scene.getGame(), despawnTime);
		hitList = new ArrayList<String>();
		
		physicsComponent = new PhysicsComponent(this);
	}

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
