package general;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** The InputManager class is used to register key press, mouse movement, and mouse clicks and then store them for use in game. */
public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

	private Game game;
	
	private final int KEY_SIZE = 256;
	private boolean[] keys, justPressed, cantPress;
	
	private Vector2 mousePos;
	private boolean lmbHeld, lmbJustPressed, lmbCantPress;
	private boolean dragging;

	public InputManager(Game game) {
		this.game = game;
		keys = new boolean[KEY_SIZE];
		justPressed = new boolean[KEY_SIZE];
		cantPress = new boolean[KEY_SIZE];
		mousePos = new Vector2(0, 0);
	}
	
	/** */
	public void update() {
		for (int i = 0; i < KEY_SIZE; i++) {
			if (cantPress[i] && !keys[i])
				cantPress[i] = false;
			else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i])
				justPressed[i] = true;
		}
		
		if (lmbCantPress && !lmbHeld)
			lmbCantPress = false;
		else if (lmbJustPressed) {
			lmbCantPress = true;
			lmbJustPressed = false;
		}
		if (!lmbCantPress & lmbHeld)
			lmbJustPressed = true;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int num = e.getKeyCode();
		if(num < 0 || num >= KEY_SIZE)
			return;
		keys[num] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int num = e.getKeyCode();
		if(num < 0 || num >= KEY_SIZE)
			return;
		keys[num] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			lmbHeld = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			lmbHeld = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos.x = e.getX();
		mousePos.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.x = e.getX();
		mousePos.y = e.getY();
		//System.out.println("moved");
	}
	
	public Vector2 getAbsoluteMousePos() { return mousePos; }
	
	public Vector2 getMousePos() { 
		return new Vector2(mousePos.x / game.getScale(), mousePos.y / game.getScale());
	}
	
	public boolean isLmbHeld() { return lmbHeld; }
	
	public boolean isLmbJustPressed() { return lmbJustPressed; }
	
	public boolean isDragging() { return dragging; }
	
	public void setDragging(boolean dragging) { this.dragging = dragging; }
}
