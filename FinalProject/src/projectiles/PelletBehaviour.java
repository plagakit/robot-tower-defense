package projectiles;

import general.Vector2;

public class PelletBehaviour extends ProjectileBehaviour {

	private float speed;
	
	public PelletBehaviour(int speed) {
		this.speed = speed;
	}
	
	@Override
	public void start(Projectile parent, Vector2 target) {
		Vector2 pos = parent.getPos();
		Vector2 direction = Vector2.direction(pos, target);
		parent.setVel(Vector2.multiply(direction, speed));
		parent.setRotation(Vector2.lookAtAngle(pos, target));
	}

	@Override
	public void move(Projectile parent) {}

}
