package projectiles;

import general.Vector2;

public abstract class ProjectileBehaviour {

	public abstract void start(Projectile parent, Vector2 target);
	
	public abstract void move(Projectile parent);
	
}
