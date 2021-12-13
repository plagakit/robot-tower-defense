package projectiles;

import java.awt.Color;

import general.Vector2;
import graphics.Renderer;

public class LightningBehaviour extends ProjectileBehaviour {

	private Color darkColour;
	private int fadeTime;
	private int transparency;
	private final float FADE = 0.01f;

	public LightningBehaviour(Color darkColour) {
		this.darkColour = darkColour;
	}
	
	@Override
	public ProjectileBehaviour connect(Projectile parent) {
		LightningBehaviour copy = new LightningBehaviour(darkColour);
		copy.parent = parent;
		//copy.fadeTime = parent.getDespawnTime();
		return copy;
	}

	@Override
	public void start(Vector2 target) {
		
		transparency = 1;
		parent.getGameScene().getBloons().getList().get(0).handleCollision(parent);
		
		
	}

	@Override
	public void move() {}
	
	@Override
	public void render(Renderer r) {
		
		
		
	}

}
