package projectiles;

import general.Vector2;
import graphics.Renderer;

public class ScissorBehaviour extends ProjectileBehaviour {

	private final float SPEED = 0.05f;
	private final float RADIUS;
	private final float ROT_SPEED = 10;
	
	private Vector2 direction;
	float counter = 0;
	
	public ScissorBehaviour() {
		// arbitrary numbers that were trial & errored by me
		// multiplying speed by 60 gives a good-looking radius
		RADIUS = 60 * SPEED;
	}
	
	@Override
	public ProjectileBehaviour connect(Projectile parent) {
		ScissorBehaviour copy = new ScissorBehaviour();
		copy.parent = parent;
		return copy;
	}
	
	
	@Override
	public void start(Vector2 target) {

		// dividing 42.5 by speed gives time it takes for scissor to make 1 full circle
		// ie. with speed of 0.05 (normal speed), 850 ms is about the time it takes for 1 full circle
		parent.setDespawnTime((int)(42.5 / SPEED));
		
		Vector2 pos = parent.getPos();
		direction = Vector2.direction(pos, target);
		direction.x = Math.signum(direction.x);
		direction.y = Math.signum(direction.y);
		parent.setRotation(Vector2.lookAtAngle(pos, target));
		
		float angle = Vector2.lookAtAngle(pos, target);

	}
	
	@Override
	public void move() {
		
		float timeScale = parent.getGameScene().getGame().getTimeScale();
		
		float rot = parent.getRotation();
		parent.setRotation(rot + (ROT_SPEED * timeScale));
		
		counter += SPEED * timeScale;
		float sin = (float)Math.sin(counter);
		float cos = (float)Math.cos(counter);
		
		Vector2 vel = new Vector2(direction.x * sin * RADIUS, direction.y * cos * RADIUS);
		parent.setVel(vel);
	}
	
	@Override
	public void render(Renderer r) {}
	
}
