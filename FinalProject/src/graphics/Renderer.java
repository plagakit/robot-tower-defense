package graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// composition over inheritance

public class Renderer {

	Graphics2D g;
	
	public Renderer(Graphics2D g) {
		this.g = g;
	}

	public void scale(int x, int y) {
		g.scale(x, y);
	}

	public void clearRect(float x, float y, float width, float height) {
		g.clearRect((int)x, (int)y, (int)width, (int)height);
	}

	public void dispose() {
		g.dispose();
	}

	public void setFont(Font font) {
		g.setFont(font);	
	}

	public void drawString(String s, float x, float y) {
		g.drawString(s, x, y);
	}

	public void drawImage(BufferedImage image, float x, float y, float width, float height) {
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}

	public void rotate(double radians, float x, float y) {
		g.rotate(radians, x, y);
	}

	public void setColor(Color c) {
		g.setColor(c);
	}

	public void setStroke(int thickness) {
		g.setStroke(new BasicStroke(thickness));
	}

	public void drawLine(float x1, float y1, float x2, float y2) {
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	public void fillRect(float x, float y, float width, float height) {
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public void drawRect(float x, float y, float width, float height) {
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	public FontMetrics getFontMetrics() {
		return g.getFontMetrics();
	}

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

	public void setTransparency(float alpha) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	public void fillOval(float x, float y, float width, float height) {
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}
	
}
