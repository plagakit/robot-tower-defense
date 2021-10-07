package gameobjects;

public class BuyInfo {

	private String title;
	private String description;
	private int baseCost;
	
	public BuyInfo(String title, String description, int baseCost) {
		this.title = title;
		this.description = description;
		this.baseCost = baseCost;
	}
	
	public String getTitle() { return title; }
	
	public String getDescription() { return description; }
	
	public int getBaseCost() { return baseCost; }
}
