package gameobjects.ui;

import game.Game;
import game.InputManager;
import game.Vector2;
import gameobjects.GameObject;
import gameobjects.components.BoxBounds;
import graphics.Renderer;
import scenes.GameScene;

/** An abstract class for defining a clickable box-shaped button
 * game object. */
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
	
	/** A method that is called when the button is clicked. */
	protected abstract void onClick();
	
	
	/** A method that is called when the mouse enters the bounds
	 * of the button. */
	protected abstract void onMouseEnter();
	
	/** A method that is called when the mouse exits the bounds
	 * of the button. */
	protected abstract void onMouseExit();
	
	public BoxBounds getBounds() { return bounds; }
}
