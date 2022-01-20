package gameobjects.projectiles;

import game.Vector2;
import scenes.GameScene;

public class Scissor extends Projectile {

	private final float SPEED = 0.05f;
	private final float RADIUS = 60 * SPEED;
	private final float ROT_SPEED = 10;
	
	private Vector2 direction;
	float counter = 0;
	
	public Scissor() {
		super();
	}
	
	public Scissor(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		super(scene, "Scissor", pos, data);
		
		counter = 0;
		direction = Vector2.direction(pos, target);
		direction.x = Math.signum(direction.x);
		direction.y = Math.signum(direction.y);
		
		rotation = Vector2.lookAtAngle(pos, target);
	}

	@Override
	public Projectile init(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {

		// dividing 42.5 by speed gives time it takes for scissor to make 1 full circle
		// ie. with speed of 0.05 (normal speed), 850 ms is about the time it takes for 1 full circle
		data.setDespawnTime((int)(42.5 / SPEED));
		
		Scissor copy = new Scissor(scene, pos, target, data);
		return copy;
	}
	
	@Override
	public void update() {
		super.update();
		
		float timeScale = scene.getGame().getTimeScale();
		
		rotation += ROT_SPEED * timeScale;
		
		counter += SPEED * timeScale;
		float sin = (float)Math.sin(counter);
		float cos = (float)Math.cos(counter);
		
		vel = new Vector2(direction.x * sin * RADIUS, direction.y * cos * RADIUS);
	}

}
