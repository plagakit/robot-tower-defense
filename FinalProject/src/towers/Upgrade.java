package towers;

import gameobjects.BuyInfo;

//https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
public abstract class Upgrade {

	protected Tower tower;
	protected BuyInfo buyInfo;

	public Upgrade(Tower tower, BuyInfo buyInfo) {
		this.tower = tower;
		this.buyInfo = buyInfo;
	}

	public abstract void apply();
	
	public BuyInfo getBuyInfo() { return buyInfo; }
	
}
