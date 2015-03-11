import java.util.ArrayList;


public class GameUtilities {
	
	public static final String[] leftWords = {"left", "west"};
	public static final String[] rightWords = {"right", "east"};
	public static final String[] downWords = {"down", "south"};
	public static final String[] upWords = {"up", "north"};
	
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
	
	/**
	 * Finds the max x value within the provided Coordinate List
	 * @param coords
	 * @return int max x value or width
	 */
	public static int findMaxX(ArrayList<Coordinate> coords) {
		if(coords != null && !coords.isEmpty()) {
			int max = coords.get(0).getX();
			for(int i=1; i<coords.size();i++) {
				if(coords.get(i).getX() > max) {
					max = coords.get(i).getX();
				}
			}
			return max;
		}
		return -1;
	}
	
	/**
	 * Finds the max y value within the provided Coordinate List
	 * @param coords
	 * @return int max y or length
	 */
	public static int findMaxY(ArrayList<Coordinate> coords) {
		if(coords != null && !coords.isEmpty()) {
			int max = coords.get(0).getY();
			for(int i=1; i<coords.size();i++) {
				if(coords.get(i).getY() > max) {
					max = coords.get(i).getY();
				}
			}
			return max;
		}
		return -1;
	}
	
	/**
	 * Finds the max z value within the provided Coordinate List
	 * @param coords
	 * @return int max z or height
	 */
	public static int findMaxZ(ArrayList<Coordinate> coords) {
		if(coords != null && !coords.isEmpty()) {
			int max = coords.get(0).getZ();
			for(int i=1; i<coords.size();i++) {
				if(coords.get(i).getZ() > max) {
					max = coords.get(i).getZ();
				}
			}
			return max;
		}
		return -1;
	}
}
