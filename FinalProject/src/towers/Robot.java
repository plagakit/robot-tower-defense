package towers;

import components.CircleBounds;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import projectiles.PelletBehaviour;
import projectiles.ProjectileData;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos, 80, 650,
				new ProjectileData(new PelletBehaviour(5), 1, 1, "pellet.png"),
				new BuyInfo("TEST", "This is a test description. Wow!", 100));
		
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");
		
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Faster Motors", "Makes the robot shoot faster!", 300)) {
					@Override
					public void apply() {
						tower.reloadTime /= 1.5;
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Rapid Fire", "Makes the robot shoot even faster!", 500)) {
					@Override
					public void apply() {
						tower.reloadTime /= 2;
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Precise Sensors", "Increases the range of the robot!", 300)) {
					@Override
					public void apply() {
						tower.range = new CircleBounds(tower, 120);
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Nuclear Energy", "New power source makes the robot shoot a destructive energy beam.", 500)) {
					@Override
					public void apply() {
						tower.projectileData.addDamage(1);
						tower.projectileData.addPierce(1);
						tower.projectileData.setProjectileSpritePath("pelletupgrade1.png");
					}
				}
			}
		});
	}

}
