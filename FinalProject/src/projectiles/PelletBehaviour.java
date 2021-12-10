package projectiles;

import general.Vector2;
import graphics.Renderer;

public class PelletBehaviour extends ProjectileBehaviour {

	private float speed;
	
	public PelletBehaviour(float speed) {
		this.speed = speed;
	}
	
	@Override
	public ProjectileBehaviour connect(Projectile parent) {
		PelletBehaviour copy = new PelletBehaviour(speed);
		copy.parent = parent;
		return copy;
	}
	
	@Override
	public void start(Vector2 target) {
		Vector2 pos = parent.getPos();
		Vector2 direction = Vector2.direction(pos, target);
		parent.setVel(Vector2.multiply(direction, speed));
		parent.setRotation(Vector2.lookAtAngle(pos, target));
	}

	@Override
	public void move() {}
	
	@Override
	public void render(Renderer r) {}

}
