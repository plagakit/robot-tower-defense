package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Pellet extends Projectile {

	private float speed;
	
	public Pellet(float speed) {
		this.speed = speed;
	}
	
	@Override
	public Projectile copy(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {

		Pellet copy = new Pellet(scene, pos, target, data);
		
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, speed);
		rotation = Vector2.lookAtAngle(pos, target);
	}
	
	private Pellet(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		super(scene, "Pellet", pos, target, data);

		System.out.println(speed);
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, speed);
		rotation = Vector2.lookAtAngle(pos, target);
	}
	
}
