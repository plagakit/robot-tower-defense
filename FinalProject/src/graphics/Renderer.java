package graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Renderer {

	Graphics2D g;
	
	public Renderer(Graphics2D g) {
		this.g = g;
	}

	public void scale(int x, int y) {
		g.scale(x, y);
	}

	public void clearRect(int x, int y, int width, int height) {
		g.clearRect(x, y, width, height);
	}

	public void dispose() {
		g.dispose();
	}

	public void setFont(Font font) {
		g.setFont(font);	
	}

	public void drawString(String s, int x, int y) {
		g.drawString(s, x, y);
	}

	public void drawImage(BufferedImage image, int x, int y, int width, int height) {
		g.drawImage(image, x, y, width, height, null);
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

	public void drawLine(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

	public void fillRect(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}

	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	public FontMetrics getFontMetrics() {
		return g.getFontMetrics();
	}

	public void drawWrappedString(String s, int x, int y, int maxWidth) {
		FontMetrics fm = getFontMetrics();
		int height = fm.getHeight();
		int currentX = x;
		int currentY = y;
		
		for (String word : s.split(" ")) {
			word += " ";
			int width = fm.stringWidth(word);
			if (currentX + width > x + maxWidth) {
				currentY += height;
				currentX = x;
			}
			drawString(word, currentX, currentY);
			currentX += width;
		}
	}

	public void setTransparency(float alpha) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	public void fillOval(int x, int y, int width, int height) {
		g.fillOval(x, y, width, height);
	}
	
}
