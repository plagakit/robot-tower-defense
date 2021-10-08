package components;

import gameobjects.GameObject;
import general.Vector2;

public class CircleBounds extends Component {

	private int radius;
	
	public CircleBounds(GameObject parent, int radius) {
		super(parent);
		this.radius = radius;
	}

	public int getRadius() { return radius; }
	
	public void setRadius(int radius) { this.radius = radius; }
	
	public int getDiameter() { return radius * 2; }
	
	public boolean isInside(Vector2 point) {
		float distance = Vector2.distance(point, parent.getPos());
		return distance < radius;
	}
	
	public boolean collides(CircleBounds bounds) {
		float distance = Vector2.distance(bounds.getParent().getPos(), parent.getPos());
		return distance < (radius + bounds.getRadius());
	}
}
