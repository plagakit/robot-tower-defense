package gameobjects.towers;

import game.Vector2;
import gameobjects.BuyInfo;
import gameobjects.projectiles.ProjectileData;
import gameobjects.projectiles.Scissor;
import scenes.GameScene;

public class ScissorRobot extends Tower {

	public ScissorRobot(GameScene scene, Vector2 pos) {
		super(scene, "Scissor Robot", pos, 80, 1000,
				new ProjectileData(new Scissor(), 1, 4, "scissor.png", 1000),
				new BuyInfo("Scissor Robot", "Pierces many bloons at once, but needs careful positioning.", 350));
		
		sprite = scene.getGame().getSpriteManager().getSprite("scissorrobot.png");
	
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Sharper Blades", "Sharper blades damage and pierce more bloons!", 400)) {
					@Override
					protected void apply() {
						tower.projectileData.addPierce(3);
						tower.projectileData.addDamage(1);
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Shurikens", "Allows the robot to shoot razor-sharp ninja stars!", 3000)) {
					@Override
					protected void apply() {
						tower.projectileData.addPierce(20);
						tower.projectileData.addDamage(10);
						tower.projectileData.setSpritePath("scissorupgrade1.png");
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Double Shot", "Allows two scissors to be thrown at once!", 750)) {
					@Override
					protected void apply() {
						tower.reloadTime /= 2;
						tower.projectileData.addPierce(2);
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Chakrams", "Allows the robot to shoot many deadly chakrams that slice bloons with ease!", 3000)) {
					@Override
					protected void apply() {
						tower.reloadTime /= 2;
						tower.projectileData.addPierce(20);
						tower.projectileData.setSpritePath("scissorupgrade2.png");
					}
				}
			}
		});
	}

}
