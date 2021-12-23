package towers;

import java.awt.Color;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.Lightning;
import projectiles.Projectile;
import projectiles.ProjectileData;
import scenes.GameScene;

public class LightningRobot extends Tower {

	public LightningRobot(GameScene scene, Vector2 pos) {
		super(scene, "Tesla Robot", pos, 70, 1000, 
				new ProjectileData(new Lightning(Color.MAGENTA, 5), 1, 5, null, 500), 
				new BuyInfo("Tesla Robot", "Shoots bolts of lightning that zap from bloon to bloon.", 700));
		
		sprite = scene.getGame().getSpriteManager().getSprite("robot.png");

	}

	@Override
	protected void fire(Vector2 target) {		
		Projectile blueprint = projectileData.getProjectileType();
		Projectile p = blueprint.init(scene, pos, target, projectileData);
		scene.getProjectiles().add(p);
	}
	
}
