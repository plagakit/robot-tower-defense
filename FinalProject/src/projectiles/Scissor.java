package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Scissor extends Projectile {

	private final float SPEED = 2;
	private final float ROT_SPEED = 10;
	
	public Scissor(GameScene scene, Vector2 pos, Vector2 target, String spritePath, int damage, int pierce, int despawnTime) {
		super(scene, "Scissor", pos, spritePath, damage, pierce, despawnTime);
		
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, SPEED);
		rotation = Vector2.lookAtAngle(pos, target);
	}
	
	@Override
	public void update() {
		super.update();
		
		rotation += ROT_SPEED * scene.getGame().getTimeScale(); 
	}

}
