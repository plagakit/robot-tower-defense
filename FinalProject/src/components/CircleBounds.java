package components;

import java.awt.Color;

import gameobjects.GameObject;
import general.Vector2;
import graphics.Renderer;

public class CircleBounds extends Component {

	private int radius;
	
	public CircleBounds(GameObject parent, int radius) {
		super(parent);
		this.radius = radius;
	}
	
	public void debugRender(Renderer r) {
		render(r, Color.GREEN);
	}
	
	public void render(Renderer r, Color c) {
		r.setColor(c);
		r.setTransparency(0.3f);
		Vector2 pos = parent.getPos();
		r.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
		r.setTransparency(1f);
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
