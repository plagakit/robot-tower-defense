package projectiles;

public class ProjectileData {

	private Projectile projectileType;
	private int damage;
	private int pierce;
	private String projectileSpritePath;
	private int despawnTime;
	private ProjectileEffect[] effects;
	
	public ProjectileData(Projectile projectileType, int damage, int pierce, String projectileSpritePath, int despawnTime, ProjectileEffect... effects) {
		this.projectileType = projectileType;
		this.damage = damage;
		this.pierce = pierce;
		this.projectileSpritePath = projectileSpritePath;
		this.despawnTime = despawnTime;
		this.effects = effects;
	}
	
	public Projectile getProjectileType() { return projectileType; }
	public void setProjectileType(Projectile p) { projectileType = p; }

	public int getDamage() { return damage; }
	public void addDamage(int amount) { damage += amount; }
	
	public int getPierce() { return pierce; }
	public void addPierce(int amount) { pierce += amount; }
	
	public String getProjectileSpritePath() { return projectileSpritePath; }
	public void setProjectileSpritePath(String newSpritePath) { projectileSpritePath = newSpritePath; }
	
	public int getDespawnTime() { return despawnTime; }
	public void setDespawnTime(int despawnTime) { this.despawnTime = despawnTime; }
	
	public ProjectileEffect[] getEffects() { return effects; }
	public void setEffects(ProjectileEffect... effects) { this.effects = effects; }
}
