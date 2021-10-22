package ui;

import java.awt.Graphics2D;

import components.BoxBounds;
import gameobjects.GameObject;
import general.Game;
import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Button extends GameObject {

	private boolean hovering;
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
	public void render(Graphics2D g) {
		super.render(g);

		if (Game.DEBUG)
			bounds.debugRender(g);
	}
	
	protected abstract void onClick();
	
	protected abstract void onMouseEnter();
	
	protected abstract void onMouseExit();
	
	public BoxBounds getBounds() { return bounds; }
}
