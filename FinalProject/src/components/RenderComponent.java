package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import gameobjects.GameObject;
import general.Game;
import general.Vector2;
import graphics.Sprite;

// https://gameprogrammingpatterns.com/component.html


public class RenderComponent {
	
	public void render(Graphics2D g, GameObject go, Sprite sprite) {
		if (!go.getActive())
			return;
		
		int gameScale = go.getGameScene().getGame().getScale();
		Vector2 pos = go.getPos();
		
		int x = (int)(pos.x - sprite.getWidth()/2.0) * gameScale;
		int y = (int)(pos.y - sprite.getHeight()/2.0) * gameScale;
		int width = sprite.getWidth() * gameScale;
		int height = sprite.getHeight() * gameScale;
		
		float midx = x + (width / 2.0f);
		float midy = y + (height / 2.0f);
		
		if (go.getRotation() != 0) 
			g.rotate(Math.toRadians(go.getRotation()), midx, midy);
		
		g.drawImage(sprite.getImage(), x, y, width, height, null);
		
		if (go.getRotation() != 0) 
			g.rotate(Math.toRadians(-go.getRotation()), midx, midy);
		
		if (Game.DEBUG) {
			// Position
			g.setColor(Color.blue);
			g.setStroke(new BasicStroke(10));
			g.drawLine((int)(pos.x * gameScale), (int)(pos.y * gameScale), (int)(pos.x * gameScale), (int)(pos.y * gameScale)); 
			
			g.setStroke(new BasicStroke(1));
		}
	}
}
