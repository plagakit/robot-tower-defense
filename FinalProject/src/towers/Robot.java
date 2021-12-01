package towers;

import components.CircleBounds;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos, 80, "pellet.png", 1, 1, 650,
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
						tower.damage = 2;
						tower.pierce = 2;
						tower.projectileSprite = "pelletupgrade1.png";
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

		Pellet p = new Pellet(scene, pos, target, projectileSprite, damage, pierce, Pellet.DEFAULT_DESPAWN_TIME);
		scene.getProjectiles().add(p);
	}
	
}
