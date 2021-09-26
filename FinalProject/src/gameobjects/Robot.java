package gameobjects;

import general.Vector2;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos);
		sprite = scene.getGame().getSpriteManager().getSprite("smile.png");
	}

	@Override
	public void update() {
		super.update();
	}
}
