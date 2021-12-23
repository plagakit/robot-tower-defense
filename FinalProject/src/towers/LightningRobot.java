package towers;

import java.awt.Color;

import components.CircleBounds;
import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Lightning;
import projectiles.Projectile;
import projectiles.ProjectileData;
import scenes.GameScene;

public class LightningRobot extends Tower {

	private Color currentColor = Color.MAGENTA;
	
	public LightningRobot(GameScene scene, Vector2 pos) {
		super(scene, "Tesla Robot", pos, 70, 1000, 
				new ProjectileData(new Lightning(Color.MAGENTA, 5), 1, 5, null, 500), 
				new BuyInfo("Tesla Robot", "Shoots bolts of lightning that zap randomly from bloon to bloon.", 700));
		
		sprite = scene.getGame().getSpriteManager().getSprite("lightningrobot.png");

		upgradePath = new UpgradePath(new Upgrade[][] {
			{ // Branch 1
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("Stronger Charge", "Strengthens the electromagnetism of the lightning, allowing it to hit more bloons!", 700)) {
					@Override
					public void apply() {
						tower.projectileData.addPierce(5);
						tower.projectileData.addDamage(2);
					}
				},
				// Upgrade2
				new Upgrade(this, 
						new BuyInfo("Plasma Beams", "Chaotic plasma beams melt right through bloons!", 4000)) {
					@Override
					public void apply() {
						currentColor = new Color(59, 0, 122);
						tower.projectileData.addDamage(10);
						tower.projectileData.addPierce(10);
					}
				}
			},
			{ // Branch 2
				// Upgrade 1
				new Upgrade(this, 
						new BuyInfo("AC/DC Current", "Increases both range and fire speed!", 600)) {
					@Override
					public void apply() {
						tower.range = new CircleBounds(tower, 80);
						reloadTime /= 1.5f;
					}
				},
				// Upgrade 2
				new Upgrade(this, 
						new BuyInfo("Quantum Elec.", "New theoretical \"quantum electricity\" phases through the subatomic bloon particles!", 8000)) {
					@Override
					public void apply() {
						tower.projectileData.addPierce(5);
						reloadTime /= 1.5f;
						currentColor = new Color(187, 255, 0);
						tower.projectileData.setProjectileType(new Lightning(currentColor, tower.projectileData.getPierce()));
					}
				}
			}
		});
		
	}

	@Override
	protected void fire(Vector2 target) {		
		Projectile blueprint = projectileData.getProjectileType();
		Projectile p = blueprint.init(scene, pos, target, projectileData);
		scene.getProjectiles().add(p);
	}
	
}
