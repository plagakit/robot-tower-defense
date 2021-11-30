package towers;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Pellet;
import scenes.GameScene;

public class Robot extends Tower {
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos, 80, 1, 1, 650,
				new BuyInfo("TEST", "This is a test description. Wow!", 100));
		
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");
		
		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Start of array
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Faster Motors", "Makes the robot shoot faster!", 300)) {
					@Override
					public void apply() {
						tower.reloadTime = 300;
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
						new BuyInfo("TEST", "This is a test upgrade. Wow!", 100)) {
					@Override
					public void apply() {
						tower.damage = 2;
						tower.reloadTime = 10;
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("TEST", "This is a test upgrade. Wow!", 100)) {
					@Override
					public void apply() {
						tower.damage = 2;
						tower.reloadTime = 10;
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
				"pellet.png", damage, pierce, Pellet.DEFAULT_DESPAWN_TIME);
		scene.getProjectiles().add(p);
	}
	
}
