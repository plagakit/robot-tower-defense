package tracks;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import game.Vector2;

/** A serializable object that represents the three main parts of
 * a track: background image, mask image, and list of points that
 * the bloons move along. This class is serialized by the TrackSerializer
 * and deserialized by the TrackLoader to be used in game. */
public class TrackData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private transient BufferedImage background;
	private transient BufferedImage mask;
	private transient Vector2[] points;
	
	public TrackData(BufferedImage background, BufferedImage mask, Vector2[] points) {
		this.background = background;
		this.mask = mask;
		this.points = points;
	}

	// (kind of) https://stackoverflow.com/questions/15058663/how-to-serialize-an-object-that-includes-bufferedimages
	//https://www.algosome.com/articles/java-custom-serialization.html
	//https://www.py4u.net/discuss/590227
	private void writeObject(ObjectOutputStream out) {
		try {
			out.defaultWriteObject();
			
			ByteArrayOutputStream backgroundBAOS = new ByteArrayOutputStream();
			ImageIO.write(background, "png", backgroundBAOS);
			out.writeInt(backgroundBAOS.size());
			backgroundBAOS.writeTo(out);
			
			ByteArrayOutputStream maskBAOS = new ByteArrayOutputStream();
			ImageIO.write(mask, "png", maskBAOS);
			out.writeInt(maskBAOS.size());
			maskBAOS.writeTo(out);
			
			out.writeInt(points.length);
			for (Vector2 point : points) {
				out.writeFloat(point.x);
				out.writeFloat(point.y);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readObject(ObjectInputStream in) {
		try { 
			
			in.defaultReadObject();
			
			int backgroundLength = in.readInt();
			byte[] backgroundData = new byte[backgroundLength];
			in.readFully(backgroundData);
			background = ImageIO.read(new ByteArrayInputStream(backgroundData));
			
			int maskLength = in.readInt();
			byte[] maskData = new byte[maskLength];
			in.readFully(maskData);
			mask = ImageIO.read(new ByteArrayInputStream(maskData));
			
			int pointLength = in.readInt();
			points = new Vector2[pointLength];
			for (int i = 0; i < pointLength; i++)
				points[i] = new Vector2(in.readFloat(), in.readFloat());
				
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBackground() { return background; }
	
	public BufferedImage getMask() { return mask; }
	
	public Vector2[] getPoints() { return points; }
	
}