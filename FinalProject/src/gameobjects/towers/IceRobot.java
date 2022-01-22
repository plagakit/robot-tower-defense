package gameobjects.towers;

import java.util.ArrayList;

import game.Vector2;
import gameobjects.BuyInfo;
import gameobjects.bloons.Bloon;
import gameobjects.components.CircleBounds;
import gameobjects.projectiles.Pellet;
import gameobjects.projectiles.ProjectileData;
import scenes.GameScene;

/** The long-range sniper robot that shoots ice at bloons. */
public class IceRobot extends Tower {

	public IceRobot(GameScene scene, Vector2 pos) {
		super(scene, "Ice Robot", pos, 120, 2000,
				new ProjectileData(new Pellet(5), 4, 5, "ice.png", 1000),
				new BuyInfo("Ice Robot", "Targets the strongest bloons and snipes them with icicles.", 600));
		sprite = scene.getGame().getSpriteManager().getSprite("icerobot.png");
		
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Crystal Vision", "Better vision allows the robot to shoot farther, harder, and deadlier.", 700)) {
					@Override
					protected void apply() {
						tower.range = new CircleBounds(tower, 160);
						tower.projectileData.addDamage(5);
						tower.projectileData.setType(new Pellet(8));
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Glacial Cannon", "Allows the robot to shoot powerful glacial icicles", 5000)) {
					@Override
					protected void apply() {
						tower.range = new CircleBounds(tower, 180);
						tower.projectileData.addDamage(50);
						tower.projectileData.setSpritePath("iceupgrade1.png");
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Sharper Icicles", "Icicles pierce more bloons with each shot!", 750)) {
					@Override
					protected void apply() {
						tower.projectileData.addPierce(5);
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Permafrost", "Converts the ice robot's powerful but slow icicles into fast spraying ice fragments!", 5000)) {
					@Override
					protected void apply() {
						tower.projectileData.addDamage(-3);
						tower.projectileData.setSpritePath("iceupgrade2.png");
						tower.reloadTime /= 5;
					}
				}
			}
		});
	}
	
	/** Targets the strongest bloons in the scene instead of
	 * the first ones. */
	@Override
	protected void target() {
		
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
