package components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import gameobjects.GameObject;
import general.Vector2;

public class CircleBounds extends Component {

	private int radius;
	
	public CircleBounds(GameObject parent, int radius) {
		super(parent);
		this.radius = radius;
	}
	
	public void debugRender(Graphics2D g) {
		render(g, Color.GREEN);
	}
	
	public void render(Graphics2D g, Color c) {
		g.setColor(c);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		Vector2 pos = parent.getPos();
		g.fillOval(
				(int)(pos.x - radius), 
				(int)(pos.y - radius), 
				radius * 2, 
				radius * 2);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}

	public int getRadius() { return radius; }
	
	public void setRadius(int radius) { this.radius = radius; }
	
	
	public boolean isInside(Vector2 point) {
		float distance = Vector2.distance(point, parent.getPos());
		return distance < radius;
	}
	
	public boolean collides(CircleBounds bounds) {
		float distance = Vector2.distance(bounds.getParent().getPos(), parent.getPos());
		return distance < (radius + bounds.getRadius());
	}
}
