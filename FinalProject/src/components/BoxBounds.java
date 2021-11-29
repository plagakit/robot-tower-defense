package components;

import java.awt.Color;

import gameobjects.GameObject;
import general.Vector2;
import graphics.Renderer;
import graphics.Sprite;

public class BoxBounds extends Component {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public BoxBounds(GameObject parent, int x, int y, int width, int height) {
		super(parent);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public BoxBounds(GameObject parent, Sprite sprite) {
		super(parent);
		this.x = -sprite.getWidth()/2;
		this.y = -sprite.getHeight()/2;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
	
	public void debugRender(Renderer r) {
		// Button outline
		r.setColor(Color.GREEN);
		r.setStroke(1);
		Vector2 pos = parent.getPos();
		r.drawRect((int)(pos.x + x), (int)(pos.y + y), width, height);
	}
	
	public boolean contains(Vector2 point) {
		Vector2 pos = parent.getPos();
		float x1 = pos.x + x;
		float x2 = x1 + width;
		float y1 = pos.y + y;
		float y2 = y1 + height;
		return point.x > x1 && point.x < x2 && point.y > y1 && point.y < y2;
	}
	
	public int getX() { return x; }
	
	public int getY() { return y; }
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
}
