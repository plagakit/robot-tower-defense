package graphics;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {

	private int width;
	private int height;
	private int scale;
	private String title;
	private boolean fullscreen = false;
	
	private JFrame jframe;
	private Canvas canvas;
	
	public Display (int width, int height, int scale, String title) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.title = title;
		
		createDisplay();
	}
	
	private void createDisplay() {
		Dimension size = new Dimension(width * scale, height * scale);
		
		jframe = new JFrame(title);
		jframe.setSize(size);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jframe.setIconImage(getIcon());
		
		jframe.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(size);
		canvas.setMaximumSize(size);
		canvas.setMinimumSize(size);
		canvas.setFocusable(false);
		
		jframe.add(canvas);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
	}
	
	public void changeScale(int newScale) {
		scale = newScale;
		Dimension size = new Dimension(width * scale, height * scale);
		jframe.setSize(size);
		canvas.setPreferredSize(size);
		canvas.setMaximumSize(size);
		canvas.setMinimumSize(size);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
	}

	public JFrame getJFrame() { return jframe; }
	
	public Canvas getCanvas() { return canvas; }
	
}
