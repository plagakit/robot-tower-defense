package tracks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TrackSerializer {

	public static void main(String[] args) {
		TrackData t = new TrackData(null, null, "");
	
		
		try {
			
			File f = new File("testTrack.track");
			FileOutputStream output = new FileOutputStream(f);
			ObjectOutputStream obj = new ObjectOutputStream(output);
			obj.writeObject(t);
			obj.close();
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
