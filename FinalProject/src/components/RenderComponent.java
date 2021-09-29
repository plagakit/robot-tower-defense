package components;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import gameobjects.Button;
import gameobjects.GameObject;
import gameobjects.Tower;
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
		
		int gameScale = parent.getGameScene().getGame().getScale();
		Vector2 pos = parent.getPos();
		
		int x = (int)(pos.x - sprite.getWidth()/2.0) * gameScale;
		int y = (int)(pos.y - sprite.getHeight()/2.0) * gameScale;
		int width = sprite.getWidth() * gameScale;
		int height = sprite.getHeight() * gameScale;
		
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
			g.setStroke(new BasicStroke(gameScale * 5));
			g.drawLine((int)(pos.x * gameScale), (int)(pos.y * gameScale), (int)(pos.x * gameScale), (int)(pos.y * gameScale)); 
			
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.RED);
		}
	}
}
