package gameobjects;

import java.awt.Graphics2D;

import components.RenderComponent;
import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Tower extends GameObject {

	protected boolean placed;
	
	RenderComponent renderComponent;
	
	public Tower(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		
		renderComponent = new RenderComponent();
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();
		if (!placed) {
			pos.x = im.getMousePos().x / scene.getGame().getScale();
			pos.y = im.getMousePos().y / scene.getGame().getScale();
			
			if (im.isLmbHeld()) {
				placed = true;
				im.setDragging(false);
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		renderComponent.render(g, this, sprite);
	}

}
