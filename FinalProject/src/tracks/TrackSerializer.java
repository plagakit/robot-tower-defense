package tracks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import game.Vector2;

/** A custom application for serializing tracks into .track files
 * that can be used in the robot tower defense game. */
public class TrackSerializer implements ActionListener {

	public static void main(String[] args) {
		TrackSerializer ts = new TrackSerializer();
		System.out.println("Closed " + ts);
	}
	
	JLabel errorDialog, bgText, maskText, pointsText;
	JTextField nameField;
	JButton selectBackground, selectMask, selectPoints, save;
	
	BufferedImage background, mask;
	Vector2[] points;
	
	public TrackSerializer() {		
		JFrame frame = new JFrame("Track Serializer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel title = new JLabel("Track Serializer");
		title.setFont(new Font("Arial", Font.BOLD, 16));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.add(title);
		
		selectBackground = new JButton("Select Background Image");
		selectBackground.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectBackground.addActionListener(this);
		p.add(selectBackground);
		
		bgText = new JLabel(" ");
		bgText.setAlignmentX(Component.CENTER_ALIGNMENT);
		bgText.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.add(bgText);
		
		selectMask = new JButton("Select Mask Image");
		selectMask.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectMask.addActionListener(this);
		p.add(selectMask);
		
		maskText = new JLabel(" ");
		maskText.setAlignmentX(Component.CENTER_ALIGNMENT);
		maskText.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.add(maskText);
		
		selectPoints = new JButton("Select Point Text File");
		selectPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectPoints.addActionListener(this);
		p.add(selectPoints);
		
		pointsText = new JLabel(" ");
		pointsText.setAlignmentX(Component.CENTER_ALIGNMENT);
		pointsText.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.add(pointsText);
		
		errorDialog = new JLabel(" ");
		errorDialog.setForeground(Color.RED);
		errorDialog.setAlignmentX(Component.CENTER_ALIGNMENT);
		errorDialog.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.add(errorDialog);
		
		nameField = new JTextField("Enter the track name", 20);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.add(nameField);
		
		save = new JButton("Save");
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.addActionListener(this);
		p.add(save);
		
		frame.add(p);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// https://www.geeksforgeeks.org/java-swing-jfilechooser/
		// https://stackoverflow.com/questions/21534515/jfilechooser-open-in-current-directory
		
		String choice = e.getActionCommand();
		
		if (choice == "Save") {
			if (background == null || mask == null || points == null) {
				errorDialog.setText("Files are missing");
				return;
			}
			
			String name = nameField.getText();
			TrackData data = new TrackData(background, mask, points);
			
			try {
				
				File f = new File(name + ".track");
				FileOutputStream fileOut;
				fileOut = new FileOutputStream(f);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(data);
				objOut.close();
			} catch (IOException e1) { errorDialog.setText(e1.getMessage()); }	
		
			errorDialog.setForeground(Color.GREEN);
			errorDialog.setText(
					String.format("Track %s successfully saved to %s", name, name + ".track"));
		}
		else { 
			
			JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.dir")));
			
			String fileExtension;
			if (choice == "Select Point Text File")
				fileExtension = "txt";
			else fileExtension = "png";
			
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = 
					new FileNameExtensionFilter("." + fileExtension + " file", fileExtension);
			chooser.addChoosableFileFilter(restrict);
			
			int fileChoice = chooser.showOpenDialog(null);
			
			if (fileChoice == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				
				if (choice == "Select Background Image") {
					bgText.setText(file.getName());
					try {
						background = ImageIO.read(file);
					} catch (IOException e1) { errorDialog.setText(e1.getMessage()); }
				}
				else if (choice == "Select Mask Image")  {
					maskText.setText(file.getName());
					try {
						mask = ImageIO.read(file);
					} catch (IOException e1) { errorDialog.setText(e1.getMessage()); }
				}
				else if (choice == "Select Point Text File") {
					pointsText.setText(file.getName());
					readTrackPoints(file);
				}
			}
			
		}
	}
	
	private void readTrackPoints(File file) {
		Scanner sc;
		try {
			sc = new Scanner(file);
			
			int numPoints = sc.nextInt();
			points = new Vector2[numPoints];

			for (int i = 0; i < numPoints; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				points[i] = new Vector2(x, y);
			}
		} catch (FileNotFoundException e) { errorDialog.setText(e.getMessage());; }
	}
	
}
