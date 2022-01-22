package gameobjects.bloons;

/** The enum for different bloon types (much cleaner than individual bloon classes & inheritance). */
public enum BloonType {
	
	EMPTY(-1,"EmptyBloon", 0, null, 0, 0),
	RED(0,"RedBloon", 0.3f, "redbloon.png", 1, 1),
	BLUE(1,"BlueBloon", 0.4f, "bluebloon.png", 1, 2, RED),
	GREEN(2,"GreenBloon", 0.5f, "greenbloon.png", 1, 3, BLUE),
	YELLOW(3,"YellowBloon", 0.8f, "yellowbloon.png", 1, 6, GREEN, GREEN),
	PINK(4,"PinkBloon", 1.2f, "pinkbloon.png", 1, 7, YELLOW),
	BLACK(6,"BlackBloon", 0.65f, "blackbloon.png", 1, 14, PINK, PINK),
	WHITE(5,"WhiteBloon", 0.60f, "whitebloon.png", 1, 14, PINK, PINK),
	ZEBRA(7,"ZebraBloon", 0.7f, "zebrabloon.png", 1, 28, WHITE, BLACK),
	RAINBOW(8,"RainbowBloon", 1f, "rainbowbloon.png", 1, 56, ZEBRA, ZEBRA),
	CERAMIC(9,"CeramicBloon", 0.5f, "ceramicbloon.png", 5, 113, RAINBOW, RAINBOW),	
	MOAB(10,"MoabBloon", 0.1f, "moabbloon.png", 200, 652, CERAMIC, CERAMIC, CERAMIC, CERAMIC);
	
	//https://www.baeldung.com/java-enum-values
	
	public final int rank;
	public final String name;
	public final float speed;
	public final String spritePath;
	public final int health;
	public final int RBE; // red bloon equivalent
	public final BloonType[] children;
	
	private BloonType(int rank, String name, float speed, String spritePath, int health, int RBE, BloonType... children) {
		this.rank = rank;
		this.name = name;
		this.speed = speed;
		this.spritePath = spritePath;
		this.health = health;
		this.RBE = RBE;
		this.children = children;
	}
}
