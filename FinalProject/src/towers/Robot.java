package towers;

import gameobjects.Bloon;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos, 1, 1, 1000,
				new BuyInfo("TEST", "This is a test description. Wow!", 100));
		
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected void fire(Bloon target) {
		rotation = Vector2.lookAtAngle(pos, target.getPos()) + 90;
		
		for (int i =0;i<10;i++) {
			Pellet p = new Pellet(scene, pos, target.getPos(), "pellet.png", damage, pierce);
			scene.getProjectiles().add(p);
		}
	}
	
}
