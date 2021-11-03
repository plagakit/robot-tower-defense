package tracks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class TrackLoader {
	
	private HashMap<String, TrackData> trackList;
	
	public TrackLoader() {
		trackList = new HashMap<>();
		loadTracks();
		loadCustomTracks();
	}
	
	private void loadTracks() {
		String dataPath = "trackdata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		while (sc.hasNextLine()) {
			String path = sc.nextLine();
			
			if (path.toCharArray()[0] == '#')
				continue;
			
			System.out.println("Loading track " + path);
			
			try {
				ObjectInputStream objIn = new ObjectInputStream(ClassLoader.getSystemResourceAsStream(path));
				TrackData readData = (TrackData)objIn.readObject();
				/*System.out.println(readData);
				System.out.println(readData.getBackground());
				System.out.println(readData.getMask());
				for (Vector2 point : readData.getPoints())
					System.out.print("TRACK POINT " + point.x + " " + point.y);
				*/
				objIn.close();
				
				trackList.put(path, readData);
			} catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }

		}
		
		sc.close();
	}
	
	private void loadCustomTracks() {
		//TODO MAKE THIS
	}
	
	public TrackData get(String name) {
		TrackData data = trackList.get(name);
		if (data == null)
			System.out.println("Warning: track data " + name + " is null.");
		return data;
	}
}
