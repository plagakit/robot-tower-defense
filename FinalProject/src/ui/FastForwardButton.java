package ui;

import components.BoxBounds;
import general.Vector2;
import graphics.Sprite;
import scenes.GameScene;

public class FastForwardButton extends Button {

	private boolean on;
	
	private final int DEFAULT_SPEED = 1;
	private final int FF_SPEED = 2;
	private final Sprite sprite1;
	private final Sprite sprite2;
	
	public FastForwardButton(GameScene scene, Vector2 pos) {
		super(scene, "FastForwardButton", pos);
		
		sprite1 = scene.getGame().getSpriteManager().getSprite("fastforwardoff.png");
		sprite2 = scene.getGame().getSpriteManager().getSprite("fastforwardon.png");
		sprite = sprite1;
		bounds = new BoxBounds(this, sprite);
	}

	@Override
	protected void onClick() {
		if (on) {
			sprite = sprite1;
			scene.getGame().setTimeScale(DEFAULT_SPEED);
			on = false;
		} else {
			sprite = sprite2;
			scene.getGame().setTimeScale(FF_SPEED);
			on = true;
		}
	}

	@Override
	protected void onMouseEnter() {}
	@Override
	protected void onMouseExit() {}

}
