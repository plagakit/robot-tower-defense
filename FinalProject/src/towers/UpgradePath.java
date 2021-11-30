package towers;

public class UpgradePath {
	
	/* Upgrade path representation for array of size 2: 
	 *       End
	 *    1-2   2-1
	 *   /   \ /   \
	 * 0-2   1-1   2-0
	 *   \   / \   /
	 *    0-1   1-0
	 *      \   /
	 *       0-0
	 *      Start
	 * Different paths: 7
	 * Unique upgrades: 4         
	 */
	
	private Upgrade[] left, right;
	private int leftIndex;
	private int rightIndex;
	
	public UpgradePath(Upgrade[] left, Upgrade[] right) {
		this.left = left;
		this.right = right;
		
		leftIndex = -1;
		rightIndex = -1;
	}

	// 0 - ready, 1 - locked, 2 - max
	public int getNextLeftState() {
		int next = leftIndex + 1;
		if (next >= left.length) return 2;
		else if (rightIndex >= right.length / 2) return 1;
		else return 0;
	}
	
	public int getNextRightState() {
		int next = rightIndex + 1;
		if (next >= right.length) return 2;
		else if (leftIndex >= left.length / 2) return 1;
		else return 0;
	}
	
	public Upgrade getNextLeftUpgrade() {
		if (leftIndex + 1 >= left.length) return null;
		return left[leftIndex + 1];
	}
	
	public Upgrade getNextRightUpgrade() {
		if (rightIndex + 1 >= right.length) return null;
		return right[rightIndex + 1];
	}
	
	public void advanceLeft() {
		leftIndex++;
		left[leftIndex].apply();
	}
	
	public void advanceRight() {
		rightIndex++;
		right[rightIndex].apply();
	}
	
}
