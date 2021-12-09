package projectiles;

public class ProjectileData {

	private ProjectileBehaviour behaviour;
	private int damage;
	private int pierce;
	private String projectileSpritePath;
	private int despawnTime;
	
	public ProjectileData(ProjectileBehaviour behaviour, int damage, int pierce, String projectileSpritePath, int despawnTime) {
		this.behaviour = behaviour;
		this.damage = damage;
		this.pierce = pierce;
		this.projectileSpritePath = projectileSpritePath;
		this.despawnTime = despawnTime;
	}
	
	public ProjectileBehaviour getBehaviour() { return behaviour; }
	public void setBehaviour(ProjectileBehaviour newBehaviour) { behaviour = newBehaviour; }
	
	public int getDamage() { return damage; }
	public void addDamage(int amount) { damage += amount; }
	
	public int getPierce() { return pierce; }
	public void addPierce(int amount) { pierce += amount; }
	
	public String getProjectileSpritePath() { return projectileSpritePath; }
	public void setProjectileSpritePath(String newSpritePath) { projectileSpritePath = newSpritePath; }
	
	public int getDespawnTime() { return despawnTime; }
	public void setDespawnTime(int despawnTime) { this.despawnTime = despawnTime; }
	
}
