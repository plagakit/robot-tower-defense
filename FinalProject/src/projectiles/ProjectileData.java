package projectiles;

public class ProjectileData {

	private Projectile projectileType;
	private int damage;
	private int pierce;
	private String projectileSpritePath;
	private int despawnTime;
	private Effect[] effects;
	
	public ProjectileData(Projectile projectileType, int damage, int pierce, String projectileSpritePath, int despawnTime, Effect... effects) {
		this.projectileType = projectileType;
		this.damage = damage;
		this.pierce = pierce;
		this.projectileSpritePath = projectileSpritePath;
		this.despawnTime = despawnTime;
		this.effects = effects;
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
	
	public Effect[] getEffects() { return effects; }
	public void setEffects(Effect... effects) { this.effects = effects; }
}
