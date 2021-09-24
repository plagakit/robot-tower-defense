package graphics;

import java.util.HashMap;

public class SpriteManager {

	private HashMap<String, HashMap<String, Sprite>> spriteList;
	
	public SpriteManager() {
		spriteList = new HashMap<>();
		loadSprites();
	}
	
	private void loadSprites() {
		
	}
}
