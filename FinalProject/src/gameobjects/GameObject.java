package gameobjects;

import java.awt.Graphics2D;

import general.Vector2;
import scenes.GameScene;

public abstract class GameObject {

	protected GameScene scene;
	
	protected String name;
	
	protected Vector2 pos;
	protected Vector2 vel;
	
	public GameObject(GameScene scene, String name, Vector2 pos) {
		this.scene = scene;
		this.name = name;
		this.pos = new Vector2(pos);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
}
