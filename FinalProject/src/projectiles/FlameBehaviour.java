package projectiles;

import general.Vector2;

public class FlameBehaviour extends ProjectileBehaviour {

	private Vector2 direction;
	private float speed;
	private final float START_SPEED = 0.75f;
	private final float DECELERATION = 0.01f; 
	
	public void start(Projectile parent, Vector2 target) {
		
		Vector2 pos = parent.getPos();
		direction = Vector2.direction(pos, target);
		parent.setRotation(Vector2.lookAtAngle(pos, target));
		
		speed = START_SPEED;
	}
	
	@Override
	public void move(Projectile parent) {

		parent.setPos(Vector2.multiply(direction, speed));
		speed -= DECELERATION;
		
		if (speed <= 0)
			parent.despawn();
	}
	
}
