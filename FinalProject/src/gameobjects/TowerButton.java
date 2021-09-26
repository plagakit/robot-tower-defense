package gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import general.Vector2;
import scenes.GameScene;

public class TowerButton extends Button {
	
	public TowerButton(GameScene scene, Vector2 pos, Class<? extends Tower> tower, String iconName) {
		super(scene, tower.getName() + "Button", pos);
		sprite = scene.getGame().getSpriteManager().getSprite(iconName);
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
