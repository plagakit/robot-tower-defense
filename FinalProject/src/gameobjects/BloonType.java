package gameobjects;

public enum BloonType {
	EMPTY("EmptyBloon", 0, null, 0, 0),
	RED("RedBloon", 0.3f, "redbloon.png", 1, 1),
	BLUE("BlueBloon", 0.4f, "bluebloon.png", 1, 2, RED),
	GREEN("GreenBloon", 0.5f, "greenbloon.png", 1, 3, BLUE),
	YELLOW("YellowBloon", 0.8f, "yellowbloon.png", 1, 4, GREEN),
	PINK("PinkBloon", 1.2f, "pinkbloon.png", 1, 5, YELLOW),
	BLACK("BlackBloon", 0.65f, "blackbloon.png", 1, 6, PINK),
	WHITE("WhiteBloon", 0.60f, "whitebloon.png", 1, 6, PINK),
	ZEBRA("ZebraBloon", 0.7f, "zebrabloon.png", 1, 12, WHITE, BLACK),
	RAINBOW("RainbowBloon", 1f, "rainbowbloon.png", 1, 24, ZEBRA, ZEBRA),
	CERAMIC("CeramicBloon", 0.5f, "ceramicbloon.png", 5, 48, RAINBOW, RAINBOW);
	
	//https://www.baeldung.com/java-enum-values
	
	public final String name;
	public final float speed;
	public final String spritePath;
	public final int health;
	public final int RBE; // red bloon equivalent
	public final BloonType[] children;
	
	private BloonType(String name, float speed, String spritePath, int health, int RBE, BloonType... children) {
		this.name = name;
		this.speed = speed;
		this.spritePath = spritePath;
		this.health = health;
		this.RBE = RBE;
		this.children = children;
	}
}
