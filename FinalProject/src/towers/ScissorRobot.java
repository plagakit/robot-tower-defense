package towers;

import gameobjects.Bloon;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import projectiles.Scissor;
import scenes.GameScene;

public class ScissorRobot extends Tower {

	public ScissorRobot(GameScene scene, Vector2 pos) {
		super(scene, "ScissorRobot", pos, 1, 5, 750, 
				new BuyInfo("Scissor Robot", "High pierce, but needs careful positioning.", 350));
		sprite = scene.getGame().getSpriteManager().getSprite("scissorrobot.png");
	}

	@Override
	protected void fire(Bloon target) {
		rotation = Vector2.lookAtAngle(pos, target.getPos()) + 90;
		
		Scissor p = new Scissor(scene, pos, target.getPos(), "scissor.png", damage, pierce);
		scene.getProjectiles().add(p);
	}

}
