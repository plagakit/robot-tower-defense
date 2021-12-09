package towers;

import java.util.ArrayList;

import components.CircleBounds;
import gameobjects.Bloon;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.PelletBehaviour;
import projectiles.ProjectileData;
import scenes.GameScene;

public class IceRobot extends Tower {

	public IceRobot(GameScene scene, Vector2 pos) {
		super(scene, "Ice Robot", pos, 120, 2000,
				new ProjectileData(new PelletBehaviour(5), 4, 5, "ice.png", 1000),
				new BuyInfo("Ice Robot", "Targets only the strongest bloons and snipes them with icicles.", 600));
		sprite = scene.getGame().getSpriteManager().getSprite("icerobot.png");
		
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Crystal Vision", "Better vision allows the robot to shoot farther, harder, and deadlier.", 700)) {
					@Override
					public void apply() {
						tower.range = new CircleBounds(tower, 160);
						tower.projectileData.addDamage(5);
						tower.projectileData.setBehaviour(new PelletBehaviour(8));
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Glacial Cannon", "Allows the robot to shoot powerful glacial icicles", 5000)) {
					@Override
					public void apply() {
						tower.range = new CircleBounds(tower, 180);
						tower.projectileData.addDamage(50);
						tower.projectileData.setProjectileSpritePath("iceupgrade1.png");
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Bloon Freeze", "Bloons hit by ice are momentarily slowed by ice before thawing out!", 750)) {
					@Override
					public void apply() {
						
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Permafrost", "MOABs can be slowed too, and non-MOAB bloons are permanently slowed!", 5000)) {
					@Override
					public void apply() {
						tower.projectileData.setProjectileSpritePath("iceupgrade2.png");
					}
				}
			}
		});
	}
	
	@Override
	protected void target() {
		
		// TODO dont do this
		
		ArrayList<Bloon> bloons = scene.getBloons().getList();
		bloons.sort((b1, b2) -> Float.compare(b2.getRank(), b1.getRank()));
		
		for (Bloon b : bloons) {
			if (!b.isInvulnerable() && range.collides(b.getBounds())) {
				fire(b.getPos());
				reloadTimer.restart(reloadTime);
				break;
			}
		}
	}

}
