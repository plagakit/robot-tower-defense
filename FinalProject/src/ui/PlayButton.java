package ui;

import components.BoxBounds;
import general.Vector2;
import graphics.Sprite;
import scenes.GameScene;

public class PlayButton extends Button {

	private boolean on;
	private Sprite onSprite, offSprite;
	
	public PlayButton(GameScene scene, Vector2 pos) {
		super(scene, "PlayButton", pos);

		onSprite = scene.getGame().getSpriteManager().getSprite("playon.png");
		offSprite = scene.getGame().getSpriteManager().getSprite("playoff.png");
		sprite = onSprite;
		bounds = new BoxBounds(this, sprite);
		
		on = true;
	}

	@Override
	protected void onClick() {
		if (on) {
			sprite = offSprite;
			on = false;
			scene.startNextRound();
		}
	}

	@Override
	protected void onMouseEnter() {}

	@Override
	protected void onMouseExit() {}

}
