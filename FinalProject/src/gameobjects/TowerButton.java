package gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {

	public TowerButton(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
		
	}
	
	@Override
	protected void initSprite() {
		
	}

	@Override
	protected void onClick() {
		

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)pos.x, (int)pos.y, 50, 50);
	}

}
