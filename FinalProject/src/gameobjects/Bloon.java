package gameobjects;

import components.CircleBounds;
import general.Vector2;
import scenes.GameScene;

public class Bloon extends GameObject {

	private CircleBounds bounds;
	
	public Bloon(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		sprite = scene.getGame().getSpriteManager().getSprite("redbloon.png");
	}

	@Override
	public void update() {

		
	}

	
	
}
