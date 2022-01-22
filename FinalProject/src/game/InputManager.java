package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** The InputManager class is used to register key press, mouse movement, and mouse clicks and then store them for use in game. */
public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

	/* i made this class at the start of the project, intending to use 
	 * key presses. i spent a long time creating the keys, justPressed, and 
	 * cantPress system but ironically i found later that i only needed the mouse
	 * for input :'( */
	
	private Game game;
	
	private final int KEY_SIZE = 256;
	private boolean[] keys, justPressed, cantPress;
	private boolean debugButton;
	
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
	
	/** Register key presses. */
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
		
		debugButton = justPressed[KeyEvent.VK_F1];
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
	
	/** Returns the mouse position respective to the window and its size. */
	public Vector2 getAbsoluteMousePos() { return mousePos; }
	
	/** Returns the mouse position respective to the game and its size (ignoring displaySize). */
	public Vector2 getMousePos() { 
		return new Vector2(mousePos.x / game.getScale(), mousePos.y / game.getScale());
	}
	
	public boolean isLmbHeld() { return lmbHeld; }
	
	public boolean isLmbJustPressed() { return lmbJustPressed; }
	
	public boolean isDragging() { return dragging; }
	
	public void setDragging(boolean dragging) { this.dragging = dragging; }

	public boolean isDebugPressed() { return debugButton; } 	
}
