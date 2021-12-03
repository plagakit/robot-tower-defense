package ui;

import components.BoxBounds;
import general.Vector2;
import scenes.GameScene;

public class SettingsButton extends Button {

	public SettingsButton(GameScene scene, Vector2 pos) {
		super(scene, "SettingsButton", pos);
		
		sprite = scene.getGame().getSpriteManager().getSprite("settings.png");
		bounds = new BoxBounds(this, sprite);
	}

	@Override
	protected void onClick() {
		scene.setPaused(true);
	}

	@Override
	protected void onMouseEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMouseExit() {
		// TODO Auto-generated method stub

	}

}
