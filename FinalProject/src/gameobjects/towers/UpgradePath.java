package gameobjects.towers;

/** A class for defining the path that a tower progresses when
 * applying multiple upgrades to itself. Upgrades comes in branches,
 * requiring the previous upgrade in one branch to purchase the 
 * next one in that same branch. In the game, there are 2 branches
 * with two upgrades each. Here is a visual representation, with Os
 * being the upgrade, and lines connecting the upgrades.
 * <br>
 * <br> Upgrades with 2 branches, 2 upgrades each:
 * <br> O---O
 * <br> | X    <-- no connection here because locked
 * <br> O---O
 */
public class UpgradePath {
	
	/** The state of a branch/upgrade. */
	public enum State {
		OPEN,
		LOCKED,
		MAX
	}
	
	private Upgrade[][] upgrades;
	private int[] progressions;
	
	/** Initializes the upgrade path with a 2D array of upgrades,
	 * with each array representing a branch and each subarray
	 * representing the contents of that branch. */
	public UpgradePath(Upgrade[][] upgrades) {
		this.upgrades = upgrades;
		progressions = new int[upgrades.length];
	}
	
	/** Gets the state of the next upgrade in the specified branch. */
	public State getNextState(int branchIndex) {
		
		int next = progressions[branchIndex];
		
		// if the next upgrade is at max
		if (next >= upgrades[branchIndex].length) 
			return State.MAX;
		
		// if any other upgrades are greater than half their brach --> second half of this branch becomes locked
		if (next >= upgrades[branchIndex].length / 2)
			for (int i = 0; i < upgrades.length; i++)
				if (i != branchIndex && progressions[i] > upgrades[i].length / 2)
					return State.LOCKED;
		
		return State.OPEN;
	}
	
	/** Returns the next upgrade in the specified branch. */
	public Upgrade getNextUpgrade(int branchIndex) {
		return upgrades[branchIndex][progressions[branchIndex]];
	}
	
	/** Applies the next upgrade in the specified branch and
	 * advances to the next one. */
	public boolean advanceToNextUpgrade(int branchIndex) {
		//System.out.format("Trying to apply upgrade #%d on branch #%d\n" , progressions[branchIndex], branchIndex);
		
		State state = getNextState(branchIndex);
		
		if (state == State.OPEN) {
			upgrades[branchIndex][progressions[branchIndex]].upgrade();
			progressions[branchIndex]++;
			return true;
		}
		
		return false;
	}
	
}