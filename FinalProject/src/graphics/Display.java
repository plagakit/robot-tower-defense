package graphics;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {

	private int width;
	private int height;
	private String title;
	
	private JFrame jframe;
	private Canvas canvas;
	
	public Display (int width, int height, int scale, String title) {
		this.width = width * scale;
		this.height = height * scale;
		this.title = title;
		
		createDisplay();
	}
	
	private void createDisplay() {
		jframe = new JFrame(title);
		jframe.setSize(width, height);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jframe.setIconImage(getIcon());
		
		// Fullscreen
		//jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//jframe.setUndecorated(true);
		
		jframe.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		jframe.add(canvas);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
	}

	public JFrame getJFrame() { return jframe; }
	
	public Canvas getCanvas() { return canvas; }
	
}
