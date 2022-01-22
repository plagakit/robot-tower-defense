package gameobjects.towers;

import gameobjects.BuyInfo;

//https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
public abstract class Upgrade {

	protected Tower tower;
	protected BuyInfo buyInfo;

	public Upgrade(Tower tower, BuyInfo buyInfo) {
		this.tower = tower;
		this.buyInfo = buyInfo;
	}

	public void upgrade() {
		// TODO make method in shop called "adjust for difficulty" or somethingh
		int cost = tower.getGameScene().getShop().modifyPrice(buyInfo.getBaseCost() * tower.SELL_RATE);
		tower.addSellPrice(cost);
		tower.getGameScene().getGame().getAudioManager().playSound("upgrade.wav");
		apply();
	}
	
	protected abstract void apply();
	
	public BuyInfo getBuyInfo() { return buyInfo; }
	
}
