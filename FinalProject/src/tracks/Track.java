package tracks;

import java.awt.Color;
import java.awt.Graphics2D;

import gameobjects.Tower;
import general.Game;
import general.Vector2;
import graphics.Sprite;

public class Track {

	private Game game;
	private int width;
	private int height;
	
	private String backgroundPath, maskPath;
	private Sprite background, mask;
	private boolean[][] maskValues;
	
	public Track(Game game, String backgroundPath, String maskPath) {
		this.game = game;
		this.backgroundPath = backgroundPath;
		this.maskPath = maskPath;
		
		background = game.getSpriteManager().getSprite(backgroundPath);
		mask = game.getSpriteManager().getSprite(maskPath);
		width = background.getWidth();
		height = background.getHeight();
		
		if (width != mask.getWidth() || height != mask.getHeight())
			System.out.format("Warning mask %s does not match size of bg %s\n", mask.getName(), background.getName());
	
		initMaskValues();
	}
	
	private void initMaskValues() {
		maskValues = new boolean[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int pixel = mask.getImage().getRGB(x, y);
				if (pixel == Color.GREEN.getRGB())
					maskValues[x][y] = true;
				else maskValues[x][y] = false;
			}
		}
	}
	
	public boolean validateTowerPosition(Tower tower) {
		Vector2 pos = tower.getPos();
		int rad = tower.getBounds().getRadius();
		
		if (pos.x - rad < 0 || pos.y - rad < 0 || pos.x + rad > width || pos.y + rad > height)
			return false;
		
		//https://stackoverflow.com/questions/15856411/finding-all-the-points-within-a-circle-in-2d-space
		for (int x = (int)(pos.x - rad); x < pos.x; x++) {
			for (int y = (int)(pos.y - rad); y < pos.y; y++) {
				if (Math.pow(x - pos.x, 2) + Math.pow(y - pos.y, 2) <= rad * rad) {
					int xSym = (int)(pos.x - (x - pos.x));
					int ySym = (int)(pos.y - (y - pos.y));
					// (x, y), (x, ySym), (xSym , y), (xSym, ySym) are in the circle
					
					if (!maskValues[x][y] || !maskValues[x][ySym] || !maskValues[xSym][y] || !maskValues[xSym][ySym])
						return false;
				}
			}
		}
		
		return true;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(background.getImage(), 0, 0, width, height, null);
		
		/* Draw mask
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		g.drawImage(mask.getImage(), 0, 0, width * scale, height * scale, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		*/
	}
	
}