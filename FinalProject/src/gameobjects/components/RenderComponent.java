package gameobjects.components;

import java.awt.Color;

import game.Game;
import game.Vector2;
import gameobjects.GameObject;
import graphics.Renderer;
import graphics.Sprite;

/** A component that renders the game object, its sprite, and
 * other rendering-related things to the Renderer. */
public class RenderComponent extends Component {
	
	public RenderComponent(GameObject parent) {
		super(parent);
	}

	/** Renders the sprite that is passed in to the game object's
	 * position. */
	public void render(Renderer r, Sprite sprite) {
		if (!parent.getActive() || sprite == null)
			return;
		
		Vector2 pos = parent.getPos();
		
		float x = pos.x - sprite.getWidth() / 2.0f;
		float y = pos.y - sprite.getHeight() / 2.0f;
		int width = sprite.getWidth();
		int height = sprite.getHeight();
		
		float midx = x + (width / 2.0f);
		float midy = y + (height / 2.0f);
		
		if (parent.getRotation() != 0) 
			r.rotate(Math.toRadians(parent.getRotation()), midx, midy);
		
		r.drawImage(sprite.getImage(), x, y, width, height);
		
		if (parent.getRotation() != 0) 
			r.rotate(Math.toRadians(-parent.getRotation()), midx, midy);
		
		if (Game.DEBUG) {
			// Position
			r.setColor(Color.PINK);
			r.setStroke(5);
			r.drawLine(pos.x, pos.y, pos.x, pos.y); 
			
			r.setStroke(1);
			r.setColor(Color.RED);
		}
	}
}
