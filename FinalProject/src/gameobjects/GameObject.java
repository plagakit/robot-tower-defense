package gameobjects;

import game.Vector2;
import gameobjects.components.RenderComponent;
import graphics.Renderer;
import graphics.Sprite;
import scenes.GameScene;

/** An abstract class defining a game object - an object that exists
 * and interacts with other game objects inside of a game scene. */
public abstract class GameObject {

	protected GameScene scene;
	
	protected String name;
	protected boolean active;
	
	protected Vector2 pos;
	protected Vector2 vel;
	protected float rotation;
	
	private RenderComponent renderComponent;
	protected Sprite sprite;
	
	/** Creates a gameobject with the specified parameters. */
	public GameObject(GameScene scene, String name, Vector2 pos) {
		this.scene = scene;
		this.name = name;
		this.pos = pos == null ? Vector2.zero() : new Vector2(pos.x, pos.y);
		vel = Vector2.zero();
		rotation = 0;
		
		renderComponent = new RenderComponent(this);
		
		active = true;
	}
	
	/** An abstract method that should be called every frame in
	 * order to update the game object. */
	public abstract void update();
	
	/** Renders the current sprite with a render component. */
	public void render(Renderer r) {
		renderComponent.render(r, sprite);
	}
	
	public GameScene getGameScene() { return scene; }
	
	public String getName() { return name; }
	
	public boolean getActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
	
	public Vector2 getPos() { return pos; }
	public void setPos(Vector2 pos) { this.pos = pos; }
	
	public Vector2 getVel() { return vel; }
	public void setVel(Vector2 vel) { this.vel = vel; }
	
	public float getRotation() { return rotation; }
	public void setRotation(float rotation) { this.rotation = rotation; }
	
	public Sprite getSprite() { return sprite; }
	
}
