package gameobjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import components.CircleBounds;
import general.Game;
import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public abstract class Tower extends GameObject {

	private boolean placed;
	private boolean validPos = true;
	
	private boolean selected;
	
	protected CircleBounds bounds;
	protected CircleBounds range;
	
	public Tower(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		bounds = new CircleBounds(this, 32);
		range = new CircleBounds(this, 100);
		
		placed = false;
		selected = true;
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();
		
		if (!placed) {
			pos.x = im.getMousePos().x;
			pos.y = im.getMousePos().y;
			
			if (!im.isLmbHeld()) {
				if (validPos) {
					placed = true;
					selected = false;
					im.setDragging(false);
				} else {
					
				}
			}
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		// Tower circle bounds & range
		int gameScale = scene.getGame().getScale();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

		// Bounds
		if (Game.DEBUG) {
			g.setColor(Color.GREEN);
			g.fillOval(
					(int)(pos.x - bounds.getRadius()/2.0) * gameScale, 
					(int)(pos.y - bounds.getRadius()/2.0) * gameScale, 
					bounds.getRadius() * gameScale, 
					bounds.getRadius() * gameScale);
		}
		
		// Range
		if (selected) {
			g.setColor(validPos ? Color.GRAY : Color.RED);
			g.fillOval(
					(int)(pos.x - range.getRadius()/2.0) * gameScale, 
					(int)(pos.y - range.getRadius()/2.0) * gameScale, 
					range.getRadius() * gameScale, 
					range.getRadius() * gameScale);
		}
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	public CircleBounds getBounds() { return bounds; }
	
	public CircleBounds getRangeBounds() { return range; }
}
