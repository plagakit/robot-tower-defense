package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import gameobjects.GameObject;
import general.Game;
import general.Vector2;
import graphics.Sprite;

// https://gameprogrammingpatterns.com/component.html


public class RenderComponent extends Component {
	
	public RenderComponent(GameObject parent) {
		super(parent);
	}

	public void render(Graphics2D g, Sprite sprite) {
		if (!parent.getActive() || sprite == null)
			return;
		
		Vector2 pos = parent.getPos();
		
		int x = (int)(pos.x - sprite.getWidth()/2.0);
		int y = (int)(pos.y - sprite.getHeight()/2.0);
		int width = sprite.getWidth();
		int height = sprite.getHeight();
		
		float midx = x + (width / 2.0f);
		float midy = y + (height / 2.0f);
		
		if (parent.getRotation() != 0) 
			g.rotate(Math.toRadians(parent.getRotation()), midx, midy);
		
		g.drawImage(sprite.getImage(), x, y, width, height, null);
		
		if (parent.getRotation() != 0) 
			g.rotate(Math.toRadians(-parent.getRotation()), midx, midy);
		
		if (Game.DEBUG) {
			// Position
			g.setColor(Color.PINK);
			g.setStroke(new BasicStroke(5));
			g.drawLine((int)(pos.x), (int)(pos.y), (int)(pos.x), (int)(pos.y)); 
			
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.RED);
		}
	}
}
