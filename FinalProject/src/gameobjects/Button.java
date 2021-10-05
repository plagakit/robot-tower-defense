package gameobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import components.BoxBounds;
import general.Game;
import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Button extends GameObject {

	protected BoxBounds bounds;
	
	public Button(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();

		if (bounds.contains(im.getMousePos())) {
			onHover();
			if (im.isLmbJustPressed())
				onClick();
		}

	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		int gameScale = scene.getGame().getScale();
		if (Game.DEBUG) {
			// Button outline
			g.setColor(Color.GREEN);
			g.setStroke(new BasicStroke(gameScale));
			g.drawRect(
					(int)(pos.x + bounds.getX()) * gameScale, 
					(int)(pos.y + bounds.getY())* gameScale, 
					bounds.getWidth() * gameScale, 
					bounds.getHeight() * gameScale);
		}
	}
	
	protected abstract void onClick();
	
	protected abstract void onHover();
	
	public BoxBounds getBounds() { return bounds; }
}
