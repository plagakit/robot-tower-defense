package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Flame;
import projectiles.Pellet;
import scenes.GameScene;

public class FireRobot extends Tower {

	public FireRobot(GameScene scene, Vector2 pos) {
		super(scene, "Flamethrower", pos, 50, 1, 3, 200,
				new BuyInfo("Flamethrower", "A powerful robot that incinerates\nbloons within a small radius.", 500));
		
		sprite = scene.getGame().getSpriteManager().getSprite("firerobot.png");
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;
		
		Flame flame = new Flame(scene, pos, target, "fire.png", damage, pierce);
		scene.getProjectiles().add(flame);
	}

}
