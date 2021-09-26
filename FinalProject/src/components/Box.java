package components;

import gameobjects.GameObject;
import general.Vector2;

public class Box extends Component {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public Box(GameObject parent, int x, int y, int width, int height) {
		super(parent);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(Vector2 point) {
		Vector2 pos = parent.getPos();
		float x1 = pos.x + x;
		float x2 = x1 + width;
		float y1 = pos.y + y;
		float y2 = y1 + height;
		return point.x > x1 && point.x < x2 && point.y > y1 && point.y < y2;
	}
}
