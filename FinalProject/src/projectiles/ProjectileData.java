package projectiles;

public class ProjectileData {

	private Projectile projectileType;
	private int damage;
	private int pierce;
	private String projectileSpritePath;
	private int despawnTime;
	
	public ProjectileData(Projectile projectileType, int damage, int pierce, String projectileSpritePath, int despawnTime) {
		this.projectileType = projectileType;
		this.damage = damage;
		this.pierce = pierce;
		this.projectileSpritePath = projectileSpritePath;
		this.despawnTime = despawnTime;
	}
	
	public Projectile getType() { return projectileType; }
	public void setType(Projectile p) { projectileType = p; }

	public int getDamage() { return damage; }
	public void addDamage(int amount) { damage += amount; }
	
	public int getPierce() { return pierce; }
	public void addPierce(int amount) { pierce += amount; }
	
	public String getSpritePath() { return projectileSpritePath; }
	public void setSpritePath(String newSpritePath) { projectileSpritePath = newSpritePath; }
	
	public int getDespawnTime() { return despawnTime; }
	public void setDespawnTime(int despawnTime) { this.despawnTime = despawnTime; }
	
}
