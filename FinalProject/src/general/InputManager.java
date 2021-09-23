package general;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** The InputManager class is used to register key press, mouse movement, and mouse clicks and then store them for use in game. */
public class InputManager implements KeyListener {

	private final int KEY_SIZE = 256;
	private boolean[] keys, justPressed, cantPress;

	public InputManager() {
		keys = new boolean[KEY_SIZE];
		justPressed = new boolean[KEY_SIZE];
		cantPress = new boolean[KEY_SIZE];
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
	
}
