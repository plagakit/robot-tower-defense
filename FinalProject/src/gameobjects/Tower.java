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
	private boolean validPos;
	
	private boolean selected;
	
	protected CircleBounds bounds;
	protected CircleBounds range;
	
	public Tower(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		bounds = new CircleBounds(this, 15);
		range = new CircleBounds(this, 80);
		
		placed = false;
		selected = true;
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();
		
		if (!placed) {
			pos.x = im.getMousePos().x;
			pos.y = im.getMousePos().y;
			
			validPos = validatePosition();
			
			if (!im.isLmbHeld()) {
				if (validPos) placed = true;
				else scene.getTowers().remove(this);
				
				selected = false;
				im.setDragging(false);
			}
		} 
		else {
			
			if (im.isLmbJustPressed() && bounds.isInside(im.getMousePos())) {
				if (selected) {
					selected = false;
					scene.setTowerSelection(null);
				} else {
					selected = true;
					scene.setTowerSelection(this);
				}
			}
			
		}
	}
	
	private boolean validatePosition() {
		for (Tower t : scene.getTowers().getList())	
			if (t == this) 
				continue;
			else if (t.getBounds().collides(bounds))
				return false;
		
		if (!scene.getTrack().validateTowerPosition(this))
			return false;
		
		return true;	
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
					(int)(pos.x - bounds.getRadius()) * gameScale, 
					(int)(pos.y - bounds.getRadius()) * gameScale, 
					bounds.getDiameter() * gameScale, 
					bounds.getDiameter() * gameScale);
		}
		
		// Range
		if (selected) {
			g.setColor(validPos ? Color.GRAY : Color.RED);
			g.fillOval(
					(int)(pos.x - range.getRadius()) * gameScale, 
					(int)(pos.y - range.getRadius()) * gameScale, 
					range.getDiameter() * gameScale, 
					range.getDiameter() * gameScale);
		}
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	public CircleBounds getBounds() { return bounds; }
	
	public CircleBounds getRangeBounds() { return range; }
	
	public void setSelected(boolean selected) { this.selected = selected; }
}
