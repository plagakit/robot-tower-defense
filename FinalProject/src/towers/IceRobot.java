package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import scenes.GameScene;

public class IceRobot extends Tower {

	public IceRobot(GameScene scene, Vector2 pos) {
		super(scene, "Ice Robot", pos, 120, "pellet.png", 4, 2, 2000,
				new BuyInfo("Ice Robot", "Snipes bloons with deadly icicles that do a lot of damage.", 600));
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");
		
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Sharper Blades", "Sharper blades damage and pierce more bloons!", 400)) {
					@Override
					public void apply() {
						tower.pierce += 3;
						tower.damage += 2;
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Shurikens", "Allows the robot to shoot razor-sharp ninja stars!", 3000)) {
					@Override
					public void apply() {
						tower.pierce += 20;
						tower.damage += 10;
						tower.projectileSprite = "scissorupgrade1.png";
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Double Shot", "Allows two scissors to be thrown at once!", 750)) {
					@Override
					public void apply() {
						tower.reloadTime /= 2;
						tower.pierce += 2;
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Chakrams", "Allows the robot to shoot many deadly chakrams that slice bloons with ease!", 3000)) {
					@Override
					public void apply() {
						tower.reloadTime /= 3;
						tower.pierce += 5;
						tower.projectileSprite = "scissorupgrade2.png";
					}
				}
			}
		});
	}

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;

		Pellet p = new Pellet(scene, pos, target, projectileSprite, damage, pierce, 3000);
		scene.getProjectiles().add(p);
	}

}
