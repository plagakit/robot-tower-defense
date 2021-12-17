package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Pellet extends Projectile {

	private float speed;
	
	public Pellet(float speed) {
		this.speed = speed;
	}
	
	@Override
	public Projectile init(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {

		Pellet copy = new Pellet(speed);
		
		Vector2 direction = Vector2.direction(pos, target);
		copy.vel = Vector2.multiply(direction, speed);
		copy.rotation = Vector2.lookAtAngle(pos, target);
		
		return copy;
	}

}
