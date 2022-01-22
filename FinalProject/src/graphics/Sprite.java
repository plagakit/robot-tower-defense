package graphics;

import java.awt.image.BufferedImage;

/** A class that represents a sprite, an image to be drawn to
 * the screen. */
public class Sprite {

	private String name;
	private BufferedImage image;
	private int width;
	private int height;
	
	public Sprite(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public String getName() { return name; }
	
	public BufferedImage getImage() { return image; }
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
}
