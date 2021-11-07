package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Flame extends Projectile {

	private Vector2 direction;
	private float speed;
	private final float START_SPEED = 0.75f;
	private final float DECELERATION = 0.01f; 
	
	public Flame(GameScene scene, Vector2 pos, Vector2 target, String spritePath, int damage, int pierce) {
		super(scene, "Flame", pos, spritePath, damage, pierce, 5000);
		
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
