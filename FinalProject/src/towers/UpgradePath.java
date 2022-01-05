package towers;

public class UpgradePath {
	
	/* Upgrades with 2 branches, 2 upgrades each
	 * O---O
	 * | X    <-- no connection here because locked
	 * O---O
	 */

	public enum State {
		OPEN,
		LOCKED,
		MAX
	}
	
	private Upgrade[][] upgrades;
	private int[] progressions;
	
	public UpgradePath(Upgrade[][] upgrades) {
		this.upgrades = upgrades;
		progressions = new int[upgrades.length];
	}
	
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
	
	public Upgrade getNextUpgrade(int branchIndex) {
		return upgrades[branchIndex][progressions[branchIndex]];
	}
	
	public boolean advanceToNextUpgrade(int branchIndex) {
		System.out.format("Trying to apply upgrade #%d on branch #%d\n" , progressions[branchIndex], branchIndex);
		
		State state = getNextState(branchIndex);
		
		if (state == State.OPEN) {
			upgrades[branchIndex][progressions[branchIndex]].upgrade();
			progressions[branchIndex]++;
			return true;
		}
		
		return false;
	}
	
}