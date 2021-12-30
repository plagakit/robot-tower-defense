package scenes;

import java.io.InputStream;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import general.Difficulty;
import general.Game;
import graphics.Renderer;
import tracks.Track;
import tracks.TrackLoader;

public class MainMenuScene extends Scene {

	private TrackLoader trackLoader;

	private boolean showedDialog;
	private String menuText;
	
	public MainMenuScene(Game game) {
		super(game);
		
		trackLoader = new TrackLoader();
		
		showedDialog = false;
		loadMenuText();
	}

	@Override
	public void update() {

		if (showedDialog)
			return;
		showedDialog = true;
		
		
		JRadioButton easy = new JRadioButton("Easy");
		JRadioButton medium = new JRadioButton("Medium");
		JRadioButton hard = new JRadioButton("Hard");
		medium.setSelected(true);
		ButtonGroup difficulty = new ButtonGroup();
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		JPanel p = new JPanel();
		p.add(easy);
		p.add(medium);
		p.add(hard);
		
		String[] trackChoices = trackLoader.getTrackNames();
		JComboBox<String> trackBox = new JComboBox<String>(trackChoices);
		
		Object[] message = { 
				menuText,
				"Difficulty:", p,
				"Tracks:", trackBox };
		Object[] options = {"Play", "Exit"};
		
		int choice = JOptionPane.showOptionDialog(null,
				message,
			    "Robot Tower Defense",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.PLAIN_MESSAGE,
			    null,
			    options,
			    options[0]);

		Difficulty chosenDifficulty = Difficulty.MEDIUM;
		if (easy.isSelected())
			chosenDifficulty = Difficulty.EASY;
		else if (medium.isSelected())
			chosenDifficulty = Difficulty.MEDIUM;
		else if (hard.isSelected())
			chosenDifficulty = Difficulty.HARD;
		
		String chosenTrack = trackChoices[trackBox.getSelectedIndex()];
		System.out.println(chosenTrack);	
		
		switch (choice) {
		
		case 0:
			Track track = new Track(game, trackLoader.get(chosenTrack));
			GameScene scene = new GameScene(game, track, chosenDifficulty);
			game.setCurrentScene(scene);
			break;
		case 1:
			System.exit(0);
		default:
			showedDialog = false;
			break;
		}
		
	}
	
	private void loadMenuText() {
		InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("mainmenuhtml.txt");
		Scanner s = new Scanner(input);
		menuText = "";
		
		while (s.hasNextLine())
			menuText += s.nextLine();
		
		s.close();
	}

	@Override
	public void render(Renderer r) {}

	@Override
	public void onStart() {

		
	}

	@Override
	public void onStop() {

		
	}

}
