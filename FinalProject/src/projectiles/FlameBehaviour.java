package projectiles;

import general.Vector2;
import graphics.Renderer;

public class FlameBehaviour extends ProjectileBehaviour {

	private Vector2 direction;
	private float speed;
	private final float START_SPEED = 0.8f;
	private final float DECELERATION = 0.012f; 
	
	@Override
	public ProjectileBehaviour connect(Projectile parent) {
		FlameBehaviour copy = new FlameBehaviour();
		copy.parent = parent;
		return copy;
	}
	
	@Override
	public void start(Vector2 target) {
		
		Vector2 pos = parent.getPos();
		direction = Vector2.direction(pos, target);
		parent.setRotation(Vector2.lookAtAngle(pos, target));
		
		speed = START_SPEED;
		
	}
	
	@Override
	public void move() {
		
		parent.setVel(Vector2.multiply(direction, speed));
		speed -= DECELERATION;
		
		if (speed <= 0)
			parent.despawn();
		
	}
	
	@Override
	public void render(Renderer r) {}
	
}
