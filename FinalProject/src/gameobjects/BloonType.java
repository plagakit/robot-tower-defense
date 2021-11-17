package gameobjects;

import projectiles.ProjectileType;

public enum BloonType {
	EMPTY("EmptyBloon", 0, null, 0, 0, null),
	RED("RedBloon", 0.3f, "redbloon.png", 1, 1, null),
	BLUE("BlueBloon", 0.4f, "bluebloon.png", 1, 2, null, RED),
	GREEN("GreenBloon", 0.5f, "greenbloon.png", 1, 3, null, BLUE),
	YELLOW("YellowBloon", 0.8f, "yellowbloon.png", 1, 4, null, GREEN),
	PINK("PinkBloon", 1.2f, "pinkbloon.png", 1, 5, null, YELLOW),
	BLACK("BlackBloon", 0.65f, "blackbloon.png", 1, 6, null, PINK),
	WHITE("WhiteBloon", 0.60f, "whitebloon.png", 1, 6, null, PINK),
	ZEBRA("ZebraBloon", 0.7f, "zebrabloon.png", 1, 12, null, WHITE, BLACK),
	RAINBOW("RainbowBloon", 1f, "rainbowbloon.png", 1, 24, null, ZEBRA, ZEBRA),
	CERAMIC("CeramicBloon", 0.5f, "ceramicbloon.png", 5, 53, null, RAINBOW, RAINBOW),
	METAL("MetalBloon", 0.5f, null, 1, 48, ProjectileType.FIRE, RAINBOW, RAINBOW),
	
	MOAB("MoabBloon", 0.1f, "moabbloon.png", 200, 412, null, CERAMIC, CERAMIC, CERAMIC, CERAMIC);
	
	//https://www.baeldung.com/java-enum-values
	
	public final String name;
	public final float speed;
	public final String spritePath;
	public final int health;
	public final int RBE; // red bloon equivalent
	public final ProjectileType resistance;
	public final BloonType[] children;
	
	private BloonType(String name, float speed, String spritePath, int health, int RBE, ProjectileType resistance, BloonType... children) {
		this.name = name;
		this.speed = speed;
		this.spritePath = spritePath;
		this.health = health;
		this.RBE = RBE;
		this.resistance = resistance;
		this.children = children;
	}
}
