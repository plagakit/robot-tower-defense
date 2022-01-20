package scenes;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import game.Settings;
import graphics.Renderer;

public class PauseMenu {

	private GameScene scene;
	private boolean showedDialog;
	
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
				
				JCheckBox showTipsCheckbox = new JCheckBox(" Display tips at the start of rounds");
				showTipsCheckbox.setSelected(settings.getShowTips());
				
				JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
				volumeSlider.setMajorTickSpacing(50);
				volumeSlider.setMinorTickSpacing(5);
				volumeSlider.setPaintLabels(true);
				volumeSlider.setPaintTicks(true);
				volumeSlider.setValue(settings.getVolume());
				
				JComboBox<String> displaySizes = new JComboBox<String>(new String[] {
					"1 - 640 x 360",
					"2 - 1280 x 720",
					"3 - 1920 x 1080"
				});
				displaySizes.setSelectedIndex(settings.getDisplaySize() - 1); //starts at 0, have to minus 1
				
				Object[] menu = { 
						autostartCheckbox,
						showTipsCheckbox,
						"\nVolume:", volumeSlider,
						"\nDisplay sizes:", displaySizes };
				
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
				settings.setShowTips(showTipsCheckbox.isSelected());
				settings.setVolume(volumeSlider.getValue());
				settings.setDisplaySize(displaySizes.getSelectedIndex() + 1); // index starts at 0, have to add 1
				
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
			scene.getGame().setCurrentScene(new MainMenuScene(scene.getGame()));
			break;
		default:
			showedDialog = false;
			break;
		}
	}

}
