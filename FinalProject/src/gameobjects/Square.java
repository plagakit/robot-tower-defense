package gameobjects;

import java.awt.Graphics2D;

import general.InputManager;
import general.Vector2;
import scenes.GameScene;

public class Square extends GameObject {

	public Square(GameScene scene, Vector2 pos) {
		super(scene, "Square", pos);
	}

	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();
		pos.x = im.getMousePos().x;
		pos.y = im.getMousePos().y;
	}

	@Override
	public void render(Graphics2D g) {
		g.fillRect((int)(pos.x - 25), (int)(pos.y - 25), 50, 50);
	}

}
