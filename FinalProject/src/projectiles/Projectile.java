package projectiles;

import components.CircleBounds;
import components.PhysicsComponent;
import gameobjects.GameObject;
import general.Timer;
import general.Vector2;
import scenes.GameScene;

public class Projectile extends GameObject {

	protected PhysicsComponent physicsComponent;
	protected CircleBounds bounds;
	
	protected int despawnTime;
	protected final Timer despawnTimer;
	
	protected int damage;
	protected int pierce;
	
	public Projectile(GameScene scene, String name, Vector2 pos, String spritePath, int damage, int pierce) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite(spritePath);
		this.damage = damage;
		this.pierce = pierce;
		
		despawnTimer = new Timer(scene.getGame(), 3000);
		
		physicsComponent = new PhysicsComponent(this);
	}

	@Override
	public void update() {
		
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
