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
	
	public boolean contains(Vector2 point) {
		// Pythagorean theorem -> a^2 + b^2 = c^2
		float distanceSquared = (point.x*point.x) + (point.y*point.y);
		return distanceSquared < (radius * radius);
	}
}
