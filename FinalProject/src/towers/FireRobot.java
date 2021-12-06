package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Flame;
import scenes.GameScene;

public class FireRobot extends Tower {

	public FireRobot(GameScene scene, Vector2 pos) {
		super(scene, "Flamethrower", pos, 50, "fire.png", 1, 2, 350,
				new BuyInfo("Flamethrower", "A powerful robot that roasts bloons within a small radius.", 500));
		
		sprite = scene.getGame().getSpriteManager().getSprite("firerobot.png");
	
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Hotter Flames", "Hotter flames roast bloons for more damage and pierce!", 400)) {
					@Override
					public void apply() {
						tower.damage++;
						tower.pierce++;
						tower.projectileSprite = "fireupgrade1.png";
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("New Fuel", "Experimental fuel sources make the fire burn even hotter!", 1500)) {
					@Override
					public void apply() {
						tower.damage += 3;
						tower.pierce += 3;
						tower.projectileSprite = "fireupgrade2.png";
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

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;
		
		Flame flame = new Flame(scene, pos, target, projectileSprite, damage, pierce);
		scene.getProjectiles().add(flame);
	}

}
