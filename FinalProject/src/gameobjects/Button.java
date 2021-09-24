package gameobjects;

import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Button extends GameObject {

	public Button(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();

	}
	
	protected abstract void onClick();

}
