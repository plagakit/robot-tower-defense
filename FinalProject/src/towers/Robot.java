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
			{ // Start of array
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Faster Motors", "Makes the robot shoot faster!", 300)) {
					@Override
					public void apply() {
						tower.reloadTime -= 350;
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("TEST", "This is a test upgrade. Wow!", 100)) {
					@Override
					public void apply() {
						tower.damage = 2;
						tower.reloadTime = 10;
					}
				}
			}, // End of array
			{ // Start of array
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
						new BuyInfo("Nuclear Energy", "New power source makes the robot shoot a destructive energy beam.", 100)) {
					@Override
					public void apply() {
						tower.damage = 2;
						tower.pierce = 3;
						tower.projectileSprite = "pelletupgrade1.png";
					}
				}
			} // End of array
		});
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected void fire(Vector2 target) {
		rotation = Vector2.lookAtAngle(pos, target) + 90;

		Pellet p = new Pellet(scene, pos, target, 
				projectileSprite, damage, pierce, Pellet.DEFAULT_DESPAWN_TIME);
		scene.getProjectiles().add(p);
	}
	
}
