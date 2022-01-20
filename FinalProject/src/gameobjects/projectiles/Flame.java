package gameobjects.projectiles;

import game.Vector2;
import scenes.GameScene;

public class Flame extends Projectile {

	private Vector2 direction;
	private float speed;
	private final float START_SPEED = 0.8f;
	private final float DECELERATION = 0.012f; 
	
	public Flame() {
		super();
	}
	
	@Override
	public Projectile init(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		Flame copy = new Flame(scene, pos, target, data);
		return copy;
	}
	
	public Flame(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		super(scene, "Flame", pos, data);
		
		direction = Vector2.direction(pos, target);
		rotation = Vector2.lookAtAngle(pos, target);
		
		speed = START_SPEED;
	}
	
	@Override
	public void update() {
		super.update();
		
		vel = Vector2.multiply(direction, speed);
		speed -= DECELERATION;
		
		if (speed <= 0)
			despawn();
	}

}
