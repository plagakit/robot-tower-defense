package graphics;

import java.awt.image.BufferedImage;

public class Sprite {

	private String name;
	private BufferedImage image;
	private int width;
	private int height;
	
	public Sprite(String name, BufferedImage image, int width, int height) {
		this.name = name;
		this.image = image;
		this.width = width;
		this.height = height;
	}

	public String getName() { return name; }
	
	public BufferedImage getImage() { return image; }
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
}
