package gameobjects;

public class Wave {

	class Group {
		public final BloonType type;
		public final int count;
		public final int spacingTime;
		
		public Group(BloonType type, int count, int spacingTime) {
			this.type = type;
			this.count = count;
			this.spacingTime = spacingTime;
		}
	}
	
	private Group[] groups;
	
	public Wave() {
		
	}
	
}
