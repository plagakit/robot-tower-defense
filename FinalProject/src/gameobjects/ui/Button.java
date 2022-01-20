package gameobjects.ui;

import game.Game;
import game.InputManager;
import game.Vector2;
import gameobjects.GameObject;
import gameobjects.components.BoxBounds;
import graphics.Renderer;
import scenes.GameScene;

public abstract class Button extends GameObject {

	protected boolean hovering;
	protected BoxBounds bounds;
	
	public Button(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();

		if (bounds.contains(im.getMousePos())) {
			
			if (!hovering) {
				onMouseEnter();
				hovering = true;
			}
			
			if (im.isLmbJustPressed())
				onClick();
			
		} 
		else if (hovering) {
			onMouseExit();
			hovering = false;
		}

	}
	
	@Override
	public void render(Renderer r) {
		super.render(r);

		if (Game.DEBUG)
			bounds.debugRender(r);
	}
	
	protected abstract void onClick();
	
	protected abstract void onMouseEnter();
	
	protected abstract void onMouseExit();
	
	public BoxBounds getBounds() { return bounds; }
}
