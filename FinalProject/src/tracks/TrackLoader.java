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
		String dataPath = "trackfiles/trackdata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String name = line.replace(".track", "");
			String path = "trackfiles/" + line;
			
			if (line.toCharArray()[0] == '#')
				continue;
			
			System.out.println("Loading track " + name);
			
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
				
				trackList.put(name, readData);
			} catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }

		}
		
		sc.close();
	}
	
	private void loadCustomTracks() {
		// did not have enough time to make this
	}
	
	public TrackData get(String name) {
		TrackData data = trackList.get(name);
		if (data == null)
			System.out.println("Warning: track data " + name + " is null.");
		return data;
	}
	
	public String[] getTrackNames() {
		return trackList.keySet().toArray(new String[trackList.size()]);
	}
}
