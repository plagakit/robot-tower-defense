package gameobjects.projectiles;

import game.Vector2;
import scenes.GameScene;

/** The basic projectile type. Moves in a straight line at
 * a constant velocity. */
public class Pellet extends Projectile {

	private float speed;
	
	public Pellet(float speed) {
		super();
		this.speed = speed;
	}

	@Override
	public Projectile init(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		Pellet copy = new Pellet(scene, pos, target, data, speed);
		return copy;
	}
	
	public Pellet(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data, float speed) {
		super(scene, "Pellet", pos, data);
		
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, speed);
		rotation = Vector2.lookAtAngle(pos, target);
	}

}
