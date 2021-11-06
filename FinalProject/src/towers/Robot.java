package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos, 1, 2, 600,
				new BuyInfo("TEST", "This is a test description. Wow!", 100));
		
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;
		
		Pellet p = new Pellet(scene, pos, target, 
				"pellet.png", damage, pierce, Pellet.DEFAULT_SPEED, Pellet.DEFAULT_DESPAWN_TIME);
		scene.getProjectiles().add(p);
	}
	
}
