package gameobjects.towers;

import game.Vector2;
import gameobjects.BuyInfo;
import gameobjects.projectiles.Flame;
import gameobjects.projectiles.ProjectileData;
import scenes.GameScene;

public class FireRobot extends Tower {

	public FireRobot(GameScene scene, Vector2 pos) {
		super(scene, "Flamethrower", pos, 50, 350,
				new ProjectileData(new Flame(), 1, 2, "fire.png", 1000),
				new BuyInfo("Flamethrower", "Incinerates bloons with flames within a small radius.", 500));
		
		sprite = scene.getGame().getSpriteManager().getSprite("firerobot.png");
	
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Hotter Flames", "Hotter flames roast bloons for more damage and pierce!", 400)) {
					@Override
					protected void apply() {
						tower.projectileData.addDamage(1);
						tower.projectileData.addPierce(1);
						tower.projectileData.setSpritePath("fireupgrade1.png");
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("New Fuel", "Experimental fuel sources make the fire burn even hotter!", 1500)) {
					@Override
					protected void apply() {
						tower.projectileData.addDamage(1);
						tower.projectileData.addPierce(3);
						tower.projectileData.setSpritePath("fireupgrade2.png");
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Servomotors", "Makes the robot spit flame faster!", 600)) {
					@Override
					protected void apply() {
						tower.reloadTime /= 1.5;
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Propellants", "Makes the robot shoot flames even faster!", 2000)) {
					@Override
					protected void apply() {
						tower.reloadTime /= 1.5;
					}
				}
			}
		});
	}

}
