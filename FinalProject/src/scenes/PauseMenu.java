package scenes;

import java.awt.Color;

import javax.swing.JOptionPane;

import graphics.Renderer;

public class PauseMenu {

	GameScene scene;
	
	boolean firstPass;
	
	public PauseMenu(GameScene scene) {
		this.scene = scene;
		
		firstPass = true;
	}

	public void update() {
		if (firstPass)
			return;
		
		Object[] options = {"Back", "Restart", "Exit to Main Menu"};
		
		int choice = JOptionPane.showOptionDialog(scene.getGame().getDisplay().getJFrame(),
			    "Would you like green eggs and ham?",
			    "A Silly Question",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[0]); //default button title
		
		if (choice == 0)
			scene.unpause();
	}

	public void render(Renderer r) {		
		r.setColor(Color.BLACK);
		r.setTransparency(0.5f);
		r.fillRect(0, 0, scene.getGame().getWidth(), scene.getGame().getHeight());
	
		firstPass = false;
	}

}
