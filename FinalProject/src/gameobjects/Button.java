package gameobjects;

import java.awt.Rectangle;

import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Button extends GameObject {

	protected Rectangle bounds;
	
	public Button(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();

		if (im.isLmbJustPressed()) {
			if (contains(im.getMousePos()))
				onClick();
		}
	}
	
	protected abstract void onClick();

	protected boolean contains(Vector2 point) {
		float x1 = pos.x + bounds.x;
		float x2 = x1 + bounds.width;
		float y1 = pos.y + bounds.y;
		float y2 = y1 + bounds.height;
		return point.x > x1 && point.x < x2 && point.y > y1 && point.y < y2;
	}
	
}
