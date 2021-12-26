package projectiles;

import java.awt.Color;
import java.util.ArrayList;

import gameobjects.Bloon;
import general.Vector2;
import graphics.Renderer;
import scenes.GameScene;

public class Lightning extends Projectile {

	private Color darkColour;
	private final float FADE = 0.01f;
	private float transparency;
	
	private final int SQUIGGLES = 1;
	private final float SQUIGGLE_DIST_MIN = 10;
	private final float SQUIGGLE_DIST_MAX = 20;
	
	private int numTargets;
	private Bloon[] targets;
	private ArrayList<Vector2> impactPoints;
	
	public Lightning(Color darkColour, int numTargets) {
		super();
		this.darkColour = darkColour;
		this.numTargets = numTargets;
	}
	
	@Override
	public Projectile init(GameScene scene, Vector2 pos, Vector2 target, ProjectileData data) {
		data.setDespawnTime(1000);
		Lightning copy = new Lightning(scene, pos, data, darkColour, numTargets);
		return copy;
	}
	
	public Lightning(GameScene scene, Vector2 pos, ProjectileData data, Color darkColour, int numTargets) {
		super(scene, "Lightning", pos, data);
		this.darkColour = darkColour;
		this.numTargets = numTargets;
		transparency = 1;
		
		ArrayList<Bloon> bloons = scene.getBloons().getList();
		int num = Math.min(bloons.size(), numTargets);
		
		// Select a random bloon that isnt already in the list
		targets = new Bloon[num];
		for (int i = 0; i < num; i++) {
			Bloon selected;
			while (true) {
				int rand = (int)(Math.random() * num);
				selected = bloons.get(rand);
				for (int j = 0; j < i; j++)
					if (targets[j] == selected)
						continue;
				break;
			}
			targets[i] = selected;
		}
		
		// Pop each bloon
		for (Bloon b : targets)
			b.handleCollision(damage);
		
		initSquiggleLines(num);
	}
	
	private void initSquiggleLines(int num) {
		impactPoints = new ArrayList<Vector2>();
		Vector2 prev = pos;
		for (int i = 0; i < num; i++) {
			impactPoints.add(new Vector2(prev.x, prev.y));
			
			Vector2 next = targets[i].getPos();
			int squiggles = SQUIGGLES;
			float incX = (next.x - prev.x) / (squiggles+1);
			float incY = (next.y - prev.y) / (squiggles+1);
			
			float curX = prev.x;
			float curY = prev.y;
			
			for (int j = 0; j < squiggles; j++) {
				Vector2 inc = new Vector2(curX + incX, curY + incY);
				Vector2 point = Vector2.normalize(inc);
				
				//https://gamedev.stackexchange.com/questions/70075/how-can-i-find-the-perpendicular-to-a-2d-vector
				boolean direction = Math.random() > 0.5;
				point.x = direction ? -point.x : point.y;
				point.y = direction ? point.y : -point.y;
				
				float squiggleDistance = (float)(Math.random() * (SQUIGGLE_DIST_MAX - SQUIGGLE_DIST_MIN) + SQUIGGLE_DIST_MIN);
				Vector2 newPoint = Vector2.add(inc, Vector2.multiply(point, squiggleDistance));

				impactPoints.add(newPoint);
				curX += incX;
				curY += incY;
			}
			
			impactPoints.add(new Vector2(next.x, next.y));
			prev = next;
		}
	}

	@Override
	public void update() {
		transparency -= FADE;
		if (transparency <= 0)
			despawn();
	}
	
	@Override
	public void render(Renderer r) {
		r.setTransparency(transparency);
		
		r.setColor(darkColour);
		r.setStroke(6);
		Vector2 prev = pos;
		for (Vector2 p : impactPoints) {
			Vector2 next = p;
			r.drawLine(prev.x, prev.y, next.x, next.y);
			prev = next;
		}
		
		r.setStroke(3);
		r.setColor(Color.WHITE);
		prev = pos;
		for (Vector2 p : impactPoints) {
			Vector2 next = p;
			r.drawLine(prev.x, prev.y, next.x, next.y);
			prev = next;
		}
		
		r.setTransparency(1);
	}

}
