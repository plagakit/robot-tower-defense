package gameobjects;

import java.awt.Graphics2D;

import components.RenderComponent;
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
	
	private RenderComponent renderComponent;
	protected Sprite sprite;
	
	public GameObject(GameScene scene, String name, Vector2 pos) {
		this.scene = scene;
		this.name = name;
		this.pos = pos == null ? Vector2.zero() : new Vector2(pos.x, pos.y);
		vel = Vector2.zero();
		rotation = 0;
		
		renderComponent = new RenderComponent(this);
		
		active = true;
	}
	
	public abstract void update();
	
	public void render(Graphics2D g) {
		renderComponent.render(g, sprite);
	}
	
	public GameScene getGameScene() { return scene; }
	
	public String getName() { return name; }
	
	public boolean getActive() { return active; }
	
	public Vector2 getPos() { return pos; }
	
	public Vector2 getVel( ) { return vel; }
	
	public float getRotation() { return rotation; }
	
	public Sprite getSprite() { return sprite; }
	
}
