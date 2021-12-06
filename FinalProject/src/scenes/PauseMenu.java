package scenes;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import general.Settings;
import graphics.Renderer;

public class PauseMenu {

	GameScene scene;
	boolean showedDialog;
	
	public PauseMenu(GameScene scene) {
		this.scene = scene;
		showedDialog = false;
	}
	
	public void update() {

		if (showedDialog)
			return;
		
		showedDialog = true;
		
		
		// run the joptionpane on a separate thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
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
				
				int choice = JOptionPane.showOptionDialog(null,
					    menu,
					    "Settings",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.PLAIN_MESSAGE,
					    null,
					    options,
					    options[0]);
				
				settings.setAutostart(autostartCheckbox.isSelected());
				settings.setVolume(volumeSlider.getValue());
				
				setChoice(choice);
			}	
		});
	}

	public void render(Renderer r) {		
		r.setColor(Color.BLACK);
		r.setTransparency(0.5f);
		r.fillRect(0, 0, scene.getGame().getWidth(), scene.getGame().getHeight());
	}
	
	// even though one other pause menu can be run at a time, i put this just in case
	private synchronized void setChoice(int choice) { 
		
		switch (choice) {
		
		case 0:
			scene.setPaused(false);
			showedDialog = false;
			break;
		case 1:
			scene.onStart();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			showedDialog = false;
			break;
		}
	}

}