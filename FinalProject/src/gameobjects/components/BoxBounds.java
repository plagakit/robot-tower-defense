package gameobjects.components;

import java.awt.Color;

import game.Vector2;
import gameobjects.GameObject;
import graphics.Renderer;
import graphics.Sprite;

/** A component to define a box-shaped bound for a gameobject. */
public class BoxBounds extends Component {
	private int x;
	private int y;
	private int width;
	private int height;
	
	/** The constructor for a BoxBounds, with the x and y acting
	 * as the offsets and the width and height being the size
	 * of the box. */
	public BoxBounds(GameObject parent, int x, int y, int width, int height) {
		super(parent);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/** The constrcutor for a BoxBounds, with the sprite acting as
	 * the box and the offsets making it so that the box is in the
	 * center of  */
	public BoxBounds(GameObject parent, Sprite sprite) {
		super(parent);
		this.x = -sprite.getWidth()/2;
		this.y = -sprite.getHeight()/2;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
	
	/** Renders a green outline around the bounds for debug purposes. */
	public void debugRender(Renderer r) {
		// Button outline
		r.setColor(Color.GREEN);
		r.setStroke(1);
		Vector2 pos = parent.getPos();
		r.drawRect(pos.x + x, pos.y + y, width, height);
	}
	
	/** Determines whether a point is inside the box or not. */
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
