package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.ProjectileData;
import projectiles.Scissor;
import projectiles.ScissorBehaviour;
import scenes.GameScene;

public class ScissorRobot extends Tower {

	public ScissorRobot(GameScene scene, Vector2 pos) {
		super(scene, "Scissor Robot", pos, 80, 1000,
				new ProjectileData(new ScissorBehaviour(), 1, 4, "scissor.png"),
				new BuyInfo("Scissor Robot", "Pierces many bloons at once, but needs careful positioning.", 350));
		sprite = scene.getGame().getSpriteManager().getSprite("scissorrobot.png");
	
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Sharper Blades", "Sharper blades damage and pierce more bloons!", 400)) {
					@Override
					public void apply() {
						tower.projectileData.addPierce(3);
						tower.projectileData.addDamage(2);
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Shurikens", "Allows the robot to shoot razor-sharp ninja stars!", 3000)) {
					@Override
					public void apply() {
						tower.projectileData.addPierce(20);
						tower.projectileData.addDamage(10);
						tower.projectileData.setProjectileSpritePath("scissorupgrade1.png");
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
						tower.projectileData.addPierce(2);
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Chakrams", "Allows the robot to shoot many deadly chakrams that slice bloons with ease!", 3000)) {
					@Override
					public void apply() {
						tower.reloadTime /= 3;
						tower.projectileData.addPierce(5);
						tower.projectileData.setProjectileSpritePath("scissorupgrade2.png");
					}
				}
			}
		});
	}

}
