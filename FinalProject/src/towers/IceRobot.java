package towers;

import java.util.ArrayList;

import components.CircleBounds;
import gameobjects.Bloon;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import scenes.GameScene;

public class IceRobot extends Tower {

	public IceRobot(GameScene scene, Vector2 pos) {
		super(scene, "Ice Robot", pos, 120, "ice.png", 4, 2, 2000,
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

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;

		Pellet p = new Pellet(scene, pos, target, projectileSprite, damage, pierce, 3000);
		scene.getProjectiles().add(p);
	}

}
