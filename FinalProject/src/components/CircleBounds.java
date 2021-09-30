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
	
	public boolean isInside(Vector2 point) {
		float x = point.x - parent.getPos().x;
		float y = point.y - parent.getPos().y;
		
		// Pythagorean theorem -> a^2 + b^2 = c^2
		float distance = (float)Math.sqrt((x*x) + (y*y));
		return distance < radius;
	}
	
	public boolean collides(CircleBounds bounds) {
		// http://www.jeffreythompson.org/collision-detection/circle-circle.php
		float x = parent.getPos().x - bounds.getParent().getPos().x;
		float y = parent.getPos().y - bounds.getParent().getPos().y;
		
		// Pythagorean theorem -> a^2 + b^2 = c^2
		float distance = (float)Math.sqrt((x*x) + (y*y));
		return distance < (radius + bounds.getRadius());
	}
}
