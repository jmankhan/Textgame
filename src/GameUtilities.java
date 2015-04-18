import java.util.ArrayList;


public class GameUtilities {
	
	public static final String[] leftWords = {"left", "west"};
	public static final String[] rightWords = {"right", "east"};
	public static final String[] downWords = {"down", "south"};
	public static final String[] upWords = {"up", "north"};
	public static final String[] entryWords = {"enter", "go inside", "inside"};
	
	public static ArrayList<String> allMoveWords = new ArrayList<String>();
	
	public GameUtilities() {
		for(int i=0; i<2; i++) {
			allMoveWords.add(leftWords[i]);
			allMoveWords.add(rightWords[i]);
			allMoveWords.add(downWords[i]);
			allMoveWords.add(upWords[i]);

		}
	}
	/**
	 * Checks if the given String is contained in the String array
	 * @param value
	 * @param array
	 * @return true if the value is contained in the array, ignoring case
	 */
	public static boolean arrayContains(String value, String[] array) {
		if(array == null || value == null)
			return false;
		
		for(String s: array) {
			if(s.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
	
	public static Coordinate getEquivalentCoordinate(Coordinate coord) {
		return new Coordinate(coord.getX(), coord.getY(), coord.getZ());
	}
	
	/**
	 * Finds the desired Place by comparing the given coordinates to those in the map. If it is not found, an exception is thrown
	 * @param map
	 * @param coord
	 * @return Place within Coordinate, if not found, returns null
	 */
	public static Place findPlace(ArrayList<Place> map, Coordinate coord) throws IllegalArgumentException {
		if(map == null || coord == null) throw new IllegalArgumentException("Null values not allowed");
		for(Place p : map) {
			if(p.getCoords().equals(coord))
				return p;
		}
		throw new IllegalArgumentException("Place does not exist on map");
	}
}
