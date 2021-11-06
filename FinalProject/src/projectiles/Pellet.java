package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Pellet extends Projectile {

	public static final float DEFAULT_SPEED = 5;
	private float speed;
	
	public Pellet(GameScene scene, Vector2 pos, Vector2 target, String spritePath, int damage, int pierce, float speed, int despawnTime) {
		super(scene, "Pellet", pos, spritePath, damage, pierce, despawnTime);
		this.speed = speed;
		
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, speed);
		rotation = Vector2.lookAtAngle(pos, target);
	}

}
