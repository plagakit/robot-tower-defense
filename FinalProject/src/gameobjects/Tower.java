package gameobjects;

import java.awt.Graphics2D;

import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public class Tower extends GameObject {

	private boolean placed;
	
	public Tower(GameScene scene, String name, Vector2 pos) {
		super(scene, name, pos);
	}
	
	@Override
	protected void initSprite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (!placed) {
			InputManager im = scene.getGame().getInputManager();
			pos.x = im.getMousePos().x;
			pos.y = im.getMousePos().y;
			
			if (!im.isLmbHeld()) {
				placed = true;
				im.setDragging(false);
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.fillOval((int)(pos.x - 25), (int)(pos.y - 25), 50, 50);
	}

}
