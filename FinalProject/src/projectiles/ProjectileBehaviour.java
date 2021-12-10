package projectiles;

import general.Vector2;
import graphics.Renderer;

public abstract class ProjectileBehaviour {

	protected Projectile parent;
	
	public abstract ProjectileBehaviour connect(Projectile parent);
	
	public abstract void start(Vector2 target);
	
	public abstract void move();
	
	public abstract void render(Renderer r);
	
}
