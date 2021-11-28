package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Scissor extends Projectile {

	private final float SPEED = 0.05f;
	private final float RADIUS;
	private final float ROT_SPEED = 10;
	
	private Vector2 direction;
	float counter = 0;
	
	public Scissor(GameScene scene, Vector2 pos, Vector2 target, String spritePath, int damage, int pierce) {
		super(scene, "Scissor", pos, spritePath, damage, pierce, 850);
	
		// below are arbitrary numbers that were trial & errored by me
		// multiplying speed by 60 gives a good-looking radius
		RADIUS = 60 * SPEED;
		
		// dividing 42.5 by speed gives time it takes for scissor to make 1 full circle
		// ie. with speed of 0.05 (normal speed), 850 ms is about the time it takes for 1 full circle
		despawnTime = (int)(42.5 / SPEED);
		
		direction = Vector2.direction(pos, target);
		direction.x = Math.signum(direction.x);
		direction.y = Math.signum(direction.y);
		rotation = Vector2.lookAtAngle(pos, target);
	}
	
	@Override
	public void update() {
		super.update();
		
		rotation += ROT_SPEED * scene.getGame().getTimeScale();
		
		counter += SPEED * scene.getGame().getTimeScale();
		float sin = (float)Math.sin(counter);
		float cos = (float)Math.cos(counter);
		
		vel.x = direction.x * sin * RADIUS;
		vel.y = direction.y * cos * RADIUS;
	}
	
}
