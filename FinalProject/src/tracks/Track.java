package tracks;

import java.awt.Color;
import java.awt.image.BufferedImage;

import game.Game;
import game.Vector2;
import gameobjects.towers.Tower;
import graphics.Renderer;

/** The track object defines the "map" of the game, where bloons
 * enter, exit, and move along, and where towers can be placed. */
public class Track {
	
	private int width;
	private int height;
	
	private BufferedImage background;
	private BufferedImage mask;
	private boolean[][] maskValues;
	private Vector2[] points;
	
	/*public Track(Game game, String backgroundPath, String maskPath, String dataPath) {
		
		bgS = game.getSpriteManager().getSprite(backgroundPath);
		mS = game.getSpriteManager().getSprite(maskPath);
		
		width = bgS.getWidth();
		height = mS.getHeight();
		
		//if (width != mask.getWidth() || height != mask.getHeight())
		//	System.out.format("Warning mask %s does not match size of bg %s\n", mS.getName(), bgS.getName());
	
		initMaskValues();
		initTrackPoints(dataPath);
	} */
	
	/** The constructor for a track, created from TrackData. Creates
	 * the track so that it is ready for use in the game scene. */
	public Track(Game game, TrackData data) {
		background = data.getBackground();
		mask = data.getMask();
		points = data.getPoints();
		
		width = background.getWidth();
		height = background.getHeight();
		
		if (width != mask.getWidth() || height != mask.getHeight())
			System.out.format("Warning: track mask does not match size of track background\n");
		
		initMaskValues();
	}
	
	/** Initializes the mask values for the track, so that it knows
	 * where towers can and cannot be placed. */
	private void initMaskValues() {
		maskValues = new boolean[width][height];
		
		// green represents can be placed, other represents no
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int pixel = mask.getRGB(x, y);
				if (pixel == Color.GREEN.getRGB())
					maskValues[x][y] = true;
				else maskValues[x][y] = false;
			}
		}
	}
	
	/** Determines whether a tower and its bounds can be placed
	 * within a certain plot of the track. Returns true if it
	 * can be placed there, and false if it cannot. */
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
	
	/** Draws the background map of the track to the screen. */
	public void render(Renderer r) {
		r.drawImage(background, 0, 0, width, height);
		
		/* Draw mask
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		g.drawImage(mask.getImage(), 0, 0, width * scale, height * scale, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		*/
	}

	public Vector2[] getPoints() { return points; }
}
