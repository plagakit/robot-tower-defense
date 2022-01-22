package gameobjects.bloons;

/** A container class for storing waves of bloons for each round. */
public class Wave {

	/** Stores a group of identical bloons with a type, count, and spacing. */
	class Group {
		public final BloonType type;
		public final int count;
		public final int spacingTime;
		
		/* spacing time is the time until the next bloon is sent,
		 * or the amount of milliseconds between each bloon
		 */
		
		public Group(BloonType type, int count, int spacingTime) {
			this.type = type;
			this.count = count;
			this.spacingTime = spacingTime;
		}
	}
	
	private boolean done;
	
	private Group[] groups;
	private Group currentGroup;
	
	private int groupCount;
	private int sendCount;
	
	/** Creates a wave using a string of formatted wave data from
	 * the wave data text file.
	 * @param waveData Must follow the format "type:count:spacing,..."
	 */
	public Wave(String waveData) {

		done = false;
		
		String[] data = waveData.split(",");
		
		groups = new Group[data.length];
		for (int i = 0; i < data.length; i++) {
			String[] str = data[i].split(":");
			groups[i] = new Group(BloonType.valueOf(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		}
		
		groupCount = 0;
		sendCount = 0;
		currentGroup = groups[0];
		
	}
	
	/** Returns the next bloontype in the wave, and advances to
	 * the next bloon at the same time. */
	public BloonType getNextBloon() {
		
		if (sendCount >= currentGroup.count) {
			
			if (groupCount + 1 >= groups.length) {
				done = true;
				return null;
			}
			
			sendCount = 0;
			groupCount++;
			currentGroup = groups[groupCount];
		}
		
		sendCount++;
		return currentGroup.type;
	}
	
	public boolean isDone() { return done; }
	
	public int getSpacingTime() { return currentGroup.spacingTime; }
}
