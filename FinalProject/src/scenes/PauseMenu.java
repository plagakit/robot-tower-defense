package scenes;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

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
		
		JCheckBox autostartCheckbox = new JCheckBox(" Auto-start rounds");
		
		JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volumeSlider.setMajorTickSpacing(50);
		volumeSlider.setMinorTickSpacing(5);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setPaintTicks(true);
		
		Object[] menu = { autostartCheckbox, "\nVolume:", volumeSlider };	
		Object[] options = {"Back", "Restart", "Exit to Main Menu"};
		
		int choice = JOptionPane.showOptionDialog(scene.getGame().getDisplay().getJFrame(),
			    menu,
			    "Settings",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.PLAIN_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[0]); //default button title
		
		boolean autostart = autostartCheckbox.isSelected();
		int volume = volumeSlider.getValue();
		
		if (choice == 0) { // Unpause
			scene.unpause();
		}
		else if (choice == 1) { // Restart
			scene.onStart();
		}
		else if (choice == 2) { // Return to main menu
			scene.onStop();
		}
	}

	public void render(Renderer r) {		
		r.setColor(Color.BLACK);
		r.setTransparency(0.5f);
		r.fillRect(0, 0, scene.getGame().getWidth(), scene.getGame().getHeight());
	
		firstPass = false;
	}

}
