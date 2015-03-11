/**
 * Stores and compares locations of GameObjects
 * @author Jalal
 *
 */
public class Coordinate {

	private int x, y, z;

	/**
	 * Default constructor that will initialize axis values to negatives
	 */
	public Coordinate() {
		x=-1;
		y=-1;
		z=-1;
	}
	
	/**
	 * Creates a new Coordinate object that will be used to store location of a GameObject
	 * @param x
	 * @param y
	 * @param z
	 */
	public Coordinate(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public void setDeltaX(int dx) {
		this.x += dx;
	}
	
	public void setDeltaY(int dy) {
		this.y += dy;
	}
	
	public void setDeltaZ(int dz) {
		this.z += dz;
	}
	
	/**
	 * Compares Coordinate to another Coordinate Object. If both coordinates have
	 * the same x, y, and z values, they will be considered equal
	 */
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Coordinate) {
			Coordinate c = (Coordinate) o;
			if(this.x == c.getX() && this.y == c.getY() && this.z == c.getZ())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x + ", " + y + ", " + z;
	}
}
