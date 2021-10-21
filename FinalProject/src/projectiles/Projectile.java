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
	
	protected int despawnTime;
	protected final Timer despawnTimer;
	protected List<Integer> hitList;
	
	protected int damage;
	protected int pierce;
	
	public Projectile(GameScene scene, String name, Vector2 pos, String spritePath, int damage, int pierce) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite(spritePath);
		bounds = new CircleBounds(this, Math.min(sprite.getWidth(), sprite.getHeight()));
		this.damage = damage;
		this.pierce = pierce;
		
		despawnTimer = new Timer(scene.getGame(), 500);
		hitList = new ArrayList<Integer>();
		
		physicsComponent = new PhysicsComponent(this);
	}

	@Override
	public void update() {
		
		for (Bloon b : scene.getBloons().getList()) {
			if (hitList.contains(b.hashCode()))
				continue;
			
			if (bounds != null && bounds.collides(b.getBounds())) {
				System.out.format("Collision with %s\n", b);
				hitList.add(b.hashCode());
			}
		}
		
		despawnTimer.update();
		if (despawnTimer.isDone())
			despawn();
		
		physicsComponent.update();
	}

	private void despawn() {
		active = false;
		scene.getProjectiles().remove(this);
	}
	
}
