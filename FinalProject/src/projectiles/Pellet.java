package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Pellet extends Projectile {

	public static final float SPEED = 5;
	
	public Pellet(GameScene scene, Vector2 pos, Vector2 target, String spritePath, int damage, int pierce, int despawnTime) {
		super(scene, "Pellet", pos, spritePath, damage, pierce, despawnTime);
		
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, SPEED);
		rotation = Vector2.lookAtAngle(pos, target);
	}

}
