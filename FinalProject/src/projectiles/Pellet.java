package projectiles;

import general.Vector2;
import scenes.GameScene;

public class Pellet extends Projectile {

	private final int SPEED = 5;
	
	public Pellet(GameScene scene, Vector2 pos, Vector2 target, String spritePath, int damage, int pierce) {
		super(scene, "Pellet", pos, spritePath, damage, pierce);
		
		
		Vector2 direction = Vector2.direction(pos, target);
		vel = Vector2.multiply(direction, SPEED);
		rotation = Vector2.lookAtAngle(pos, target);
	}

}
