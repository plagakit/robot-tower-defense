package towers;

import java.awt.Color;

import components.CircleBounds;
import gameobjects.Bloon;
import gameobjects.BuyInfo;
import gameobjects.GameObject;
import general.Game;
import general.InputManager;
import general.Timer;
import general.Vector2;
import graphics.Renderer;
import projectiles.Projectile;
import projectiles.ProjectileData;
import scenes.GameScene;

public abstract class Tower extends GameObject {
	
	protected final BuyInfo info;
	
	private boolean placed;
	private boolean validPos;
	
	private boolean selected;
	
	protected CircleBounds bounds;
	protected CircleBounds range;
	protected ProjectileData projectileData;
	
	protected int reloadTime;
	protected final Timer reloadTimer;
	
	protected UpgradePath upgradePath;
	
	public Tower(GameScene scene, String name, Vector2 pos, int range, int reloadTime, ProjectileData projectileData, BuyInfo info) {
		super(scene, name, pos);
		this.info = info;
		
		bounds = new CircleBounds(this, 15);
		this.range = new CircleBounds(this, range);
		this.projectileData = projectileData;
		
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
					scene.getShop().subtractMoney(
							(int)(info.getBaseCost() * scene.getShop().getCostModifier()));
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
						scene.getShop().selectTower(null);
					} else {
						selected = true;
						scene.getShop().selectTower(this);
					}
				}
			}
			
			reloadTimer.update();
			if (reloadTimer.isDone())
				target();
		}
	}
	
	protected void target() {
		for (Bloon b : scene.getBloons().getList()) {
			if (!b.isInvulnerable() && range.collides(b.getBounds())) {
				fire(b.getPos());
				reloadTimer.restart(reloadTime);
				break;
			}
		}
	}

	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;
		
		Projectile blueprint = projectileData.getProjectileType();
		Projectile p = blueprint.init(scene, pos, target, projectileData);
		scene.getProjectiles().add(p);
	}
	
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
	public void render(Renderer r) {
		super.render(r);
		
		if (Game.DEBUG)
			bounds.debugRender(r);

		if (selected)
			range.render(r, validPos ? Color.GRAY : Color.RED);
	}
	
	public BuyInfo getInfo() { return info; }

	public CircleBounds getBounds() { return bounds; }
	
	public CircleBounds getRangeBounds() { return range; }
	
	public UpgradePath getUpgradePath() { return upgradePath; }
	
	public void setSelected(boolean selected) { this.selected = selected; }

}
