package general;

public class Vector2 {

	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() { return String.format("%f %f", x, y); }
	
	public static Vector2 zero() { return new Vector2(0, 0); }
	
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static float distance(Vector2 start, Vector2 end) {
		float x = end.x - start.x;
		float y = end.y - start.y;
		return (float)Math.abs(Math.sqrt((x*x) + (y*y)));
	}

	public static Vector2 direction(Vector2 start, Vector2 end) {
		//https://www.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-vectors/a/vector-magnitude-normalization
		Vector2 unnormalized = new Vector2(end.x - start.x, end.y - start.y);
		float magnitude = Math.abs(Vector2.distance(start, end));
		
		return new Vector2(unnormalized.x / magnitude, unnormalized.y / magnitude);
	}
	
	/**
	 * Returns the degree angle of a vector if it were to "look at" a position. It uses 
	 * the atan2 method, which calculates the angle of the look at vector in all 
	 * 4 quadrants.
	 * 
	 * @param self The position, which is rotated to "look at" the target.
	 * @param target The target being looked at.
	 * @return The look at angle in degrees.
	 */
	public static float lookAtAngle(Vector2 self, Vector2 target) {
		//https://en.wikipedia.org/wiki/Atan2
		Vector2 lookAt = new Vector2(target.x - self.x, target.y - self.y);
		return (float)Math.toDegrees(Math.atan2(lookAt.y, lookAt.x));
	}
	
	public static Vector2 multiply(Vector2 v, float multiplier) {
		return new Vector2(v.x * multiplier, v.y * multiplier);
	}
	
	public static Vector2 multiply(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x * v2.x, v1.y * v2.y);
	}
}
