package gameobjects;

import components.Box;
import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Button extends GameObject {

	protected Box bounds;
	
	public Button(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();

		if (im.isLmbJustPressed() && bounds.contains(im.getMousePos()))
			onClick();
	}
	
	protected abstract void onClick();
	
}
