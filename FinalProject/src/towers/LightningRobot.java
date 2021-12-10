package towers;

import java.awt.Color;

import gameobjects.BuyInfo;
import general.Vector2;
import projectiles.LightningBehaviour;
import projectiles.ProjectileData;
import scenes.GameScene;

public class LightningRobot extends Tower {

	public LightningRobot(GameScene scene, Vector2 pos) {
		super(scene, "LightningRobot", pos, 70, 1000, 
				new ProjectileData(new LightningBehaviour(Color.MAGENTA), 1, 5, null, 500), 
				new BuyInfo("E", "y", 100));

	}

}
