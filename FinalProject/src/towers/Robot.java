package gameobjects;

import general.Vector2;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos, new BuyInfo("TEST", "This is a test description. Wow!", 100));
		
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected void fire() {
		// TODO Auto-generated method stub
	}
	
}
