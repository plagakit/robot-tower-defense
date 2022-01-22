package game;

/** A class used to store a pair of coordinates, a 2D vector, or
 * any other miscellaneous pair of numbers. */
public class Vector2 {

	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() { return String.format("%f %f", x, y); }
	
	/** Returns a vector of (0, 0). */
	public static Vector2 zero() { return new Vector2(0, 0); }
	
	/** Returns the sum of two vectors. */
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
	
	/** Returns the distance between two points using the pythagorean
	 * fomula. */
	public static float distance(Vector2 start, Vector2 end) {
		float x = end.x - start.x;
		float y = end.y - start.y;
		return (float)Math.abs(Math.sqrt((x*x) + (y*y)));
	}
	
	/** Returns the normalized version of a vector, which makes
	 * it so that it has a magnitude of 1. */
	public static Vector2 normalize(Vector2 v) {
		////https://www.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-vectors/a/vector-magnitude-normalization
		float magnitude = (float)Math.abs(Math.sqrt((v.x*v.x) + (v.y*v.y)));
		return new Vector2(v.x / magnitude, v.y / magnitude);
	}

	/** Returns the normalized direction between two points. */
	public static Vector2 direction(Vector2 start, Vector2 end) {
		Vector2 unnormalized = new Vector2(end.x - start.x, end.y - start.y);
		return normalize(unnormalized);
	}
	
	/** Returns the degree angle of a point if it were to "look at" 
	 * another point. It uses the atan2 method, which calculates 
	 * the angle of the look-at direction in all 4 quadrants. */
	public static float lookAtAngle(Vector2 self, Vector2 target) {
		//https://en.wikipedia.org/wiki/Atan2
		Vector2 lookAt = direction(self, target);
		return (float)Math.toDegrees(Math.atan2(lookAt.y, lookAt.x));
	}
	
	/** Returns the product of a vector and a multiplier. */
	public static Vector2 multiply(Vector2 v, float multiplier) {
		return new Vector2(v.x * multiplier, v.y * multiplier);
	}
	
	/** Returns the product of two vectors. */
	public static Vector2 multiply(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x * v2.x, v1.y * v2.y);
	}
}
