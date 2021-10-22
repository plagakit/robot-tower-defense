package gameobjects;

public enum BloonType {
	RED("RedBloon", 0.3f, "redbloon.png", 1),
	BLUE("BlueBloon", 0.5f, "bluebloon.png", 1, RED),
	GREEN("GreenBloon", 0.6f, "greenbloon.png", 1, BLUE),
	YELLOW("YellowBloon", 0.8f, "yellowbloon.png", 1, GREEN),
	PINK("PinkBloon", 1f, "pinkbloon.png", 1, YELLOW),
	BLACK("BlackBloon", 0.7f, "blackbloon.png", 1, PINK),
	WHITE("WhiteBloon", 0.7f, "whitebloon.png", 1, PINK),
	ZEBRA("ZebraBloon", 0.8f, "zebrabloon.png", 1, BLACK, WHITE),
	RAINBOW("RainbowBloon", 1.5f, "rainbowbloon.png", 1, ZEBRA, ZEBRA),
	CERAMIC("CeramicBloon", 0.5f, "ceramicbloon.png", 5, RAINBOW, RAINBOW);
	
	public final String name;
	public final float speed;
	public final String spritePath;
	public final int health;
	public final BloonType[] children;
	
	private BloonType(String name, float speed, String spritePath, int health, BloonType... children) {
		this.name = name;
		this.speed = speed;
		this.spritePath = spritePath;
		this.health = health;
		this.children = children;
	}
}
