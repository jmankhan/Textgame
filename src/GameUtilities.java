import java.util.ArrayList;


public class GameUtilities {
	
	public static final String[] leftWords = {"left", "west"};
	public static final String[] rightWords = {"right", "east"};
	public static final String[] downWords = {"down", "south"};
	public static final String[] upWords = {"up", "north"};
	public static final String[] entryWords = {"enter", "go inside", "inside"};
	
	/**
	 * Checks if the given String is contained in the String array
	 * @param value
	 * @param array
	 * @return true if the value is contained in the array, ignoring case
	 */
	public static boolean arrayContains(String value, String[] array) {
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
	 * Finds the Coordinate values for each Place and return them
	 * @param map
	 * @return Coordinate List of each Place on map
	 */
	public static ArrayList<Coordinate> getCoords(ArrayList<Place> map) {
		ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		for(Place p:map) {
			coords.add(p.getCoords());
		}
		return coords;
	}
	
	/**
	 * Finds the desired Place by comparing the given coordinates to those in the map. If it is not found, null is returned
	 * @param map
	 * @param coord
	 * @return Place within Coordinate, if not found, returns null
	 */
	public static Place findPlace(ArrayList<Place> map, Coordinate coord) {
		for(Place p : map) {
			if(p.getCoords().equals(coord))
				return p;
		}
		
		return null;
	}
}
