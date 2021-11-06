package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Scissor;
import scenes.GameScene;

public class ScissorRobot extends Tower {

	public ScissorRobot(GameScene scene, Vector2 pos) {
		super(scene, "ScissorRobot", pos, 80, 1, 4, 1000, 
				new BuyInfo("Scissor Robot", "Pierces many bloons at once,\nbut needs careful positioning.", 350));
		sprite = scene.getGame().getSpriteManager().getSprite("scissorrobot.png");
	}

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;
		
		Scissor p = new Scissor(scene, pos, target, "scissor.png", damage, pierce);
		scene.getProjectiles().add(p);
	}

}
