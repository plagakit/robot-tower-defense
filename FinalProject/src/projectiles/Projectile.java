package projectiles;

import components.CircleBounds;
import components.PhysicsComponent;
import gameobjects.GameObject;
import general.Vector2;
import scenes.GameScene;

public class Projectile extends GameObject {

	protected PhysicsComponent physicsComponent;
	protected CircleBounds bounds;
	
	protected int damage;
	protected int pierce;
	
	public Projectile(GameScene scene, String name, Vector2 pos, String spritePath, int damage, int pierce) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite(spritePath);
		this.damage = damage;
		this.pierce = pierce;
		
		physicsComponent = new PhysicsComponent(this);
	}

	@Override
	public void update() {
		physicsComponent.update();
	}

}
