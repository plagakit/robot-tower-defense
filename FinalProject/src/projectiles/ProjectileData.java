package projectiles;

public class ProjectileData {

	private ProjectileBehaviour behaviour;
	private int damage;
	private int pierce;
	private String projectileSpritePath;
	
	public ProjectileData(ProjectileBehaviour behaviour, int damage, int pierce, String projectileSpritePath) {
		this.behaviour = behaviour;
		this.damage = damage;
		this.pierce = pierce;
		this.projectileSpritePath = projectileSpritePath;
	}
	
	public ProjectileBehaviour getBehaviour() { return behaviour; }
	public void setBehaviour(ProjectileBehaviour newBehaviour) { behaviour = newBehaviour; }
	
	public int getDamage() { return damage; }
	public void addDamage(int amount) { damage += amount; }
	
	public int getPierce() { return pierce; }
	public void addPierce(int amount) { pierce += amount; }
	
	public String getProjectileSpritePath() { return projectileSpritePath; }
	public void setProjectileSpritePath(String newSpritePath) { projectileSpritePath = newSpritePath; }
	
}
