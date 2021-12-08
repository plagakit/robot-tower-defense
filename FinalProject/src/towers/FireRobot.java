package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.FlameBehaviour;
import projectiles.ProjectileData;
import scenes.GameScene;

public class FireRobot extends Tower {

	public FireRobot(GameScene scene, Vector2 pos) {
		super(scene, "Flamethrower", pos, 50, 350,
				new ProjectileData(new FlameBehaviour(), 1, 2, "fire.png"),
				new BuyInfo("Flamethrower", "Incinerates bloons with flames within a small radius.", 500));
		
		sprite = scene.getGame().getSpriteManager().getSprite("firerobot.png");
	
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Hotter Flames", "Hotter flames roast bloons for more damage and pierce!", 400)) {
					@Override
					public void apply() {
						tower.projectileData.addDamage(1);
						tower.projectileData.addPierce(1);
						tower.projectileData.setProjectileSpritePath("fireupgrade1.png");
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("New Fuel", "Experimental fuel sources make the fire burn even hotter!", 1500)) {
					@Override
					public void apply() {
						tower.projectileData.addDamage(1);
						tower.projectileData.addPierce(3);
						tower.projectileData.setProjectileSpritePath("fireupgrade2.png");
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Servomotors", "Makes the robot spit flame faster!", 600)) {
					@Override
					public void apply() {
						tower.reloadTime /= 1.5;
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Propellants", "Makes the robot shoot flames even faster!", 2000)) {
					@Override
					public void apply() {
						tower.reloadTime /= 1.5;
					}
				}
			}
		});
	}

}
