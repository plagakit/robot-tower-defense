package graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;

// composition over inheritance

/** A custom "wrapper class" for the {@link Graphics2D} object.
 * Renderer allows more functionality and readability than just
 * using Graphics2D by itself. (extending Graphics2D is not an
 * option, because for some reason many of the methods must be
 * implemented manually) */
public class Renderer {

	Graphics2D g;
	
	/** Creates the renderer with the passed in Graphics2D object. */
	public Renderer(Graphics2D g) {
		this.g = g;
	}

	/** Scales the renderer by the specified dimensions. */
	public void scale(int x, int y) {
		g.scale(x, y);
	}

	/** Clears an area of graphics specified by the parameters. */
	public void clearRect(float x, float y, float width, float height) {
		g.clearRect((int)x, (int)y, (int)width, (int)height);
	}

	/** Disposes of this graphics context and releasesany system 
	 * resources that it is using. */
	public void dispose() {
		g.dispose();
	}

	/** Sets this graphics context's font to the specified font.
	 * All subsequent text operations using this graphics context
	 * use this font. A null argument is silently ignored.*/
	public void setFont(Font font) {
		g.setFont(font);	
	}

	/** Renders a string of text using the renderer's current font.
	 * The first character is positioned at the x and y coords,
	 * and it continues rendering the whole string to the right. */
	public void drawString(String s, float x, float y) {
		g.drawString(s, x, y);
	}
	
	/** Renders a string of text using the renderer's current font
	 * but with a stroke around the characters. The current colour
	 * of the graphics context is used for the outline.
	 * @param innerColour The inner colour of the characters, not the outline.
	 * @param thickness The thickness in pixels of the outline. */
	public void drawOutlinedString(String s, float x, float y, Color innerColour, int thickness) {
		//https://stackoverflow.com/questions/10016001/how-to-draw-an-outline-around-text-in-awt
		GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), s);
		Shape outline = gv.getOutline();

		g.setStroke(new BasicStroke(thickness));
		g.translate(x, y);
		g.draw(outline);
		
		g.setColor(innerColour);
		g.fill(outline);
		
		g.translate(-x, -y);
	}

	/** Renders a BufferedImage to the screen and scales it to
	 * fit inside a rectangle of specified dimensions. */
	public void drawImage(BufferedImage image, float x, float y, float width, float height) {
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}

	/** Rotates the graphics by a specified number of radians 
	 * around a pivot that is specified by x and y coordinates. The
	 * rotation applies to subsequent graphics calls. */
	public void rotate(double radians, float x, float y) {
		g.rotate(radians, x, y);
	}

	/** Sets this graphics context's font to the specified font.
	 * All subsequent text operations using this graphics context
	 * use this font.*/
	public void setColor(Color c) {
		g.setColor(c);
	}

	/** Sets the stroke of the graphics context to a BasicStroke
	 * with a specified thickness in pixels. */
	public void setStroke(int thickness) {
		g.setStroke(new BasicStroke(thickness));
	}

	/** Draws a line from (x1, y1) to (x2, y2) using the graphic's
	 * current stroke and colour. */
	public void drawLine(float x1, float y1, float x2, float y2) {
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	/** Fills in a rectangle of the specified position and
	 * dimensions. */
	public void fillRect(float x, float y, float width, float height) {
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	/** Draws the outline of a rectangle of the specified
	 * position and dimensions. */
	public void drawRect(float x, float y, float width, float height) {
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	/** Returns the current font metrics of the graphic context. */
	public FontMetrics getFontMetrics() {
		return g.getFontMetrics();
	}

	/** Draws a string but wraps it around to a new line once the
	 * width reaches a specified maximum in pixels. */
	public void drawWrappedString(String s, float x, float y, int maxWidth) {
		FontMetrics fm = getFontMetrics();
		int height = fm.getHeight();
		int currentX = (int)x;
		int currentY = (int)y;
		
		for (String word : s.split(" ")) {
			word += " ";
			int width = fm.stringWidth(word);
			if (currentX + width > x + maxWidth) {
				currentY += height;
				currentX = (int)x;
			}
			drawString(word, currentX, currentY);
			currentX += width;
		}
	}

	/** Changes the transparency of the graphics.
	 * @param alpha Must be from 0-1, with 0 being transparent and 1 being opaque */
	public void setTransparency(float alpha) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	/** Fills in a oval of the specified position and dimensions. */
	public void fillOval(float x, float y, float width, float height) {
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}
	
}
