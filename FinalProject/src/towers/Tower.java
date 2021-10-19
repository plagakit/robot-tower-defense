package towers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import components.CircleBounds;
import gameobjects.Bloon;
import gameobjects.BuyInfo;
import gameobjects.GameObject;
import general.Game;
import general.InputManager;
import general.Timer;
import general.Vector2;
import scenes.GameScene;

public abstract class Tower extends GameObject {
	
	protected final BuyInfo info;
	
	private boolean placed;
	private boolean validPos;
	
	private boolean selected;
	
	protected CircleBounds bounds;
	protected CircleBounds range;
	protected int damage;
	protected int pierce;
	protected int reloadTime;
	protected final Timer reloadTimer;
	
	public Tower(GameScene scene, String name, Vector2 pos, int damage, int pierce, int reloadTime, BuyInfo info) {
		super(scene, name, pos);
		this.info = info;
		
		bounds = new CircleBounds(this, 15);
		range = new CircleBounds(this, 80);
		this.damage = damage;
		this.pierce = pierce;
		this.reloadTime = reloadTime;
		reloadTimer = new Timer(scene.getGame(), reloadTime);
		
		placed = false;
		selected = true;
	}
	
	@Override
	public void update() {
		InputManager im = scene.getGame().getInputManager();
		
		if (!placed) {
			pos.x = im.getMousePos().x;
			pos.y = im.getMousePos().y;
			
			validPos = validatePosition();
			
			if (!im.isLmbHeld()) {
				if (validPos && scene.getShop().getMoney() >= info.getBaseCost()) {
					placed = true;
					scene.getShop().subtractMoney(info.getBaseCost());
				}
				else scene.getTowers().remove(this);
				
				selected = false;
				im.setDragging(false);
			}
		} 
		else {
			
			if (im.isLmbJustPressed()) {
				if (bounds.isInside(im.getMousePos())) {
					if (selected) {
						selected = false;
						scene.setTowerSelection(null);
					} else {
						selected = true;
						scene.setTowerSelection(this);
					}
				} else {
					rotation = Vector2.lookAtAngle(pos, im.getMousePos()) + 90;
				}
			}
			
			reloadTimer.update();
			if (reloadTimer.isDone()) {
				reloadTimer.restart();
				fire(null);
			}
			
		}
	}
	
	protected abstract void fire(Bloon target);
	
	private boolean validatePosition() {
		for (Tower t : scene.getTowers().getList())	
			if (t == this) 
				continue;
			else if (t.getBounds().collides(bounds))
				return false;
		
		if (!scene.getTrack().validateTowerPosition(this))
			return false;
		
		return true;	
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		// Tower circle bounds & range
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

		// Bounds
		if (Game.DEBUG) {
			g.setColor(Color.GREEN);
			g.fillOval(
					(int)(pos.x - bounds.getRadius()), 
					(int)(pos.y - bounds.getRadius()), 
					bounds.getDiameter(), 
					bounds.getDiameter());
		}
		
		// Range
		if (selected) {
			g.setColor(validPos ? Color.GRAY : Color.RED);
			g.fillOval(
					(int)(pos.x - range.getRadius()), 
					(int)(pos.y - range.getRadius()), 
					range.getDiameter(), 
					range.getDiameter());
		}
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public BuyInfo getInfo() { return info; }

	public CircleBounds getBounds() { return bounds; }
	
	public CircleBounds getRangeBounds() { return range; }
	
	public void setSelected(boolean selected) { this.selected = selected; }

}
