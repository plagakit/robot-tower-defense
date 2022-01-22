package gameobjects.components;

import java.awt.Color;

import game.Vector2;
import gameobjects.GameObject;
import graphics.Renderer;

/** A component to define a circle-shaped bound for a gameobject. */
public class CircleBounds extends Component {

	private int radius;
	
	/** Creates the circle bounds with the specified radius.*/
	public CircleBounds(GameObject parent, int radius) {
		super(parent);
		this.radius = radius;
	}
	
	/** Renders a green circle inside the bounds for debug purposes. */
	public void debugRender(Renderer r) {
		render(r, Color.GREEN);
	}
	
	/** Fills the bounds with a semitransparent circle of specified colour. */
	public void render(Renderer r, Color c) {
		r.setColor(c);
		r.setTransparency(0.3f);
		Vector2 pos = parent.getPos();
		r.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
		r.setTransparency(1f);
	}
	
	/** Returns whether a point is inside the bounds of the circle. */
	public boolean isInside(Vector2 point) {
		float distance = Vector2.distance(point, parent.getPos());
		return distance < radius;
	}
	
	/** Returns whether a circle collides with this cirle. */
	public boolean collides(CircleBounds bounds) {
		float distance = Vector2.distance(bounds.getParent().getPos(), parent.getPos());
		return distance < (radius + bounds.getRadius());
	}
	
	public int getRadius() { return radius; }
	
	public void setRadius(int radius) { this.radius = radius; }
}
