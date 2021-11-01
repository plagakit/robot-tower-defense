package tracks;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class TrackData implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private BufferedImage mask;
	private String data;
	
	public TrackData(BufferedImage background, BufferedImage mask, String data) {
		this.background = background;
		this.mask = mask;
		this.data = data;
	}
	
}
