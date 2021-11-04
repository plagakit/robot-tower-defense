package towers;

import gameobjects.Bloon;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import projectiles.Scissor;
import scenes.GameScene;

public class ScissorRobot extends Tower {

	public ScissorRobot(GameScene scene, Vector2 pos) {
		super(scene, "ScissorRobot", pos, 1, 4, 750, 
				new BuyInfo("Scissor Robot", "This is a test description. Wow!", 350));
		sprite = scene.getGame().getSpriteManager().getSprite("scissorrobot.png");
	}

	@Override
	protected void fire(Bloon target) {
		rotation = Vector2.lookAtAngle(pos, target.getPos()) + 90;
		
		Scissor p = new Scissor(scene, pos, target.getPos(), "scissor.png", damage, pierce, 750);
		scene.getProjectiles().add(p);
	}

}
