package gameobjects.towers;

import gameobjects.BuyInfo;

/** An abstract class for defining an upgrade to a tower, a buyable
 * object with a cost that, when bought, changes a tower in certain
 * way (usually its projectiledata or other related things). Extend
 * this as an anonymous class in the tower classes to provide upgrades.
 * @see https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html */
public abstract class Upgrade {

	protected Tower tower;
	protected BuyInfo buyInfo;

	public Upgrade(Tower tower, BuyInfo buyInfo) {
		this.tower = tower;
		this.buyInfo = buyInfo;
	}

	/** Executes the upgrade. */
	public void upgrade() {
		int cost = tower.getGameScene().getShop().modifyPrice(buyInfo.getBaseCost() * tower.SELL_RATE);
		tower.addSellPrice(cost);
		tower.getGameScene().getGame().getAudioManager().playSound("upgrade.wav");
		apply();
	}
	
	/** Applies the upgrade changes to the tower. */
	protected abstract void apply();
	
	public BuyInfo getBuyInfo() { return buyInfo; }
	
}
