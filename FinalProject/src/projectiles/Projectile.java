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
	protected int despawnTime = DEFAULT_DESPAWN_TIME;
	protected final Timer despawnTimer;
	protected List<String> hitList;
	
	protected ProjectileBehaviour behaviour;
	protected int damage;
	protected int pierce;
	protected int currentPierce;
	
	public Projectile(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		super(scene, "Projectile", pos);
		
		sprite = scene.getGame().getSpriteManager().getSprite(data.getProjectileSpritePath());
		bounds = new CircleBounds(this, Math.min(sprite.getWidth(), sprite.getHeight()));
		
		behaviour = data.getBehaviour();
		damage = data.getDamage();
		pierce = data.getPierce();
		currentPierce = pierce;
		
		despawnTimer = new Timer(scene.getGame(), despawnTime);
		hitList = new ArrayList<String>();
		
		physicsComponent = new PhysicsComponent(this);
		
		behaviour.start(this, target);
	}

	@Override
	public void update() {
		
		for (Bloon b : scene.getBloons().getList()) {
			if (hitList.contains(b.getID()) || b.isInvulnerable())
				continue;
			
			if (bounds != null && bounds.collides(b.getBounds())) {
				//System.out.format("%s@%d collision w/ %s@%s\n", this.getName(), this.hashCode(), b.getName(), b.getID());
				handleCollision(b);
				//TODO figure out whether to use hitlist or not
				//hitList.add(b.getID());
			}
			
			if (!active)
				return;
		}
		
		despawnTimer.update();
		if (despawnTimer.isDone())
			despawn();
		
		behaviour.move(this);
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

	public void setDespawnTime(int despawnTime) {
		this.despawnTime = despawnTime;
		despawnTimer.restart(despawnTime);
	}
	
}
