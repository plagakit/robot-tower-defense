package gameobjects;

import components.CircleBounds;
import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Tower extends GameObject {

	private boolean placed;
	
	protected CircleBounds bounds;
	
	public Tower(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		bounds = new CircleBounds(this, 32);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();
		if (!placed) {
			pos.x = im.getMousePos().x;
			pos.y = im.getMousePos().y;
			
			if (!im.isLmbHeld()) {
				placed = true;
				im.setDragging(false);
			}
		}
	}

	public CircleBounds getBounds() { return bounds; }
}
