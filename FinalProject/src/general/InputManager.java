package general;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** The InputManager class is used to register key press, mouse movement, and mouse clicks and then store them for use in game. */
public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

	private final int KEY_SIZE = 256;
	private boolean[] keys, justPressed, cantPress;
	
	private Vector2 mousePos;

	public InputManager() {
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
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		System.out.println("moved");
	}
	
	public Vector2 getMousePos() { return mousePos; }
	
}
