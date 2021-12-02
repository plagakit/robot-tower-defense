package scenes;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import general.Settings;
import graphics.Renderer;

public class PauseMenu {

	GameScene scene;
	
	// First pass allows pause menu to render black screen on game scene then show JOptionPane
	boolean firstPass;
	
	public PauseMenu(GameScene scene) {
		this.scene = scene;
		
		firstPass = true;
	}

	public void update() {
		if (firstPass)
			return;
		
		Settings settings = scene.getGame().getSettings();
		
		JCheckBox autostartCheckbox = new JCheckBox(" Auto-start rounds");
		autostartCheckbox.setSelected(settings.getAutostart());
		
		JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volumeSlider.setMajorTickSpacing(50);
		volumeSlider.setMinorTickSpacing(5);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setValue(settings.getVolume());
		
		Object[] menu = { autostartCheckbox, "\nVolume:", volumeSlider };	
		Object[] options = {"Back", "Restart", "Exit to Main Menu"};
		
		int choice = JOptionPane.showOptionDialog(scene.getGame().getDisplay().getJFrame(),
			    menu,
			    "Settings",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.PLAIN_MESSAGE,
			    null,
			    options,
			    options[0]);
		
		settings.setAutostart(autostartCheckbox.isSelected());
		settings.setVolume(volumeSlider.getValue());
		
		if (choice == 0) { // Unpause
			scene.unpause();
		}
		else if (choice == 1) { // Restart
			scene.onStart();
		}
		else if (choice == 2) { // Return to main menu
			System.exit(0);
		}
	}

	public void render(Renderer r) {		
		r.setColor(Color.BLACK);
		r.setTransparency(0.5f);
		r.fillRect(0, 0, scene.getGame().getWidth(), scene.getGame().getHeight());
	
		firstPass = false;
	}

}
