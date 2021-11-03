package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class SpriteManager {

	private HashMap<String, Sprite> spriteList;
	
	public SpriteManager() {
		spriteList = new HashMap<>();
		loadSprites();
	}
	
	private void loadSprites() {
		final String folder = "images/";
		final String dataPath = folder + "imagedata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		while (sc.hasNextLine()) {
			String path = sc.nextLine();
			String absolutePath = folder + path;
			
			if (path.toCharArray()[0] == '#')
				continue;
			
			System.out.println("Loading " + path);
			BufferedImage image = null;
			try { image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(absolutePath)); } catch (IOException e) {e.printStackTrace(); }
			
			Sprite s = new Sprite(path, image);
			spriteList.put(path, s);
		}
		
		sc.close();
	}
	
	public Sprite getSprite(String name) {
		Sprite s = spriteList.get(name);
		if (s == null)
			System.out.format("Warning: Sprite %s is not found\n", name);
		return s;
	}
}
