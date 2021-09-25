package gameobjects;

import java.awt.Graphics2D;

import general.Vector2;
import graphics.Sprite;
import scenes.GameScene;

public abstract class GameObject {

	protected GameScene scene;
	
	protected String name;
	protected boolean active;
	
	protected Vector2 pos;
	protected Vector2 vel;
	protected float rotation;
	
	protected Sprite sprite;
	
	public GameObject(GameScene scene, String name, Vector2 pos) {
		this.scene = scene;
		this.name = name;
		this.pos = pos;
		vel = Vector2.zero();
		rotation = 0;
		
		initSprite();
		
		active = true;
	}
	
	protected abstract void initSprite();
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
}
