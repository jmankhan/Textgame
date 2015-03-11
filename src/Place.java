import java.util.ArrayList;


/**
 * This class will track each room or place that the player may travel to within the Game. It uses a 
 * builder pattern that will require the GameObject's fields and a Coordinate field. The list of items
 * are not required to make this class but can be added if desired. They will default to empty Lists
 * This pattern will allow more requirements and optional methods to be added with ease, while keeping the 
 * code clean
 * @author Jalal
 *
 */
public class Place extends GameObject {
	private Coordinate coords;
	private ArrayList<Item> reqItems, forbidItems, containedItems;

	/**
	 * Empty constructor that will never be called but is needed to avoid abstract inheritance errors
	 */
	private Place() {
	}
	
	/**
	 * Private constructor inherited by GameObject
	 * @param name Required String name
	 * @param description Required String description
	 */
	private Place(String name, String description) {
		super(name, description);
	}

	/**
	 * Private constructor that can only be called by PlaceBuilder
	 * @param builder
	 */
	private Place(PlaceBuilder builder) {
		this.name = 		builder.name;
		this.description = 	builder.description;
		this.coords = 		builder.coord;
		this.reqItems = 	builder.reqItems;
		this.forbidItems = 	builder.forbidItems;
		this.containedItems=builder.containedItems;
	}

	public Coordinate getCoords() {
		return coords;
	}

	public ArrayList<Item> getReqItems() {
		return reqItems;
	}

	public ArrayList<Item> getForbidItems() {
		return forbidItems;
	}

	public void setCoords(Coordinate coords) {
		this.coords = coords;
	}

	public void setReqItems(ArrayList<Item> reqItems) {
		this.reqItems = reqItems;
	}

	public void setForbidItems(ArrayList<Item> forbidItems) {
		this.forbidItems = forbidItems;
	}


	public ArrayList<Item> getContainedItems() {
		return containedItems;
	}

	public void setContainedItems(ArrayList<Item> containedItems) {
		this.containedItems = containedItems;
	}


	/**
	 * Builds a Place object. Name, Description, and Coordinate are required, Item lists will be empty by default
	 * Call build() to get the Place object
	 * @author Jalal
	 *
	 */
	public static class PlaceBuilder {
		private String name, description;
		private Coordinate coord;
		private ArrayList<Item> reqItems, forbidItems, containedItems;
		
		/**
		 * Begin creating a Place object. Name, description, and Coordinate are required. Item lists may be added
		 * if desired but will be instantiated to empty lists
		 * @param name String name of this place
		 * @param description String description of this place
		 * @param coord Coordinate loction of this place
		 */
		public PlaceBuilder(String name, String description, Coordinate coord) {
			this.name = name;
			this.description = description;
			this.coord = coord;
			
			reqItems = new ArrayList<Item>();
			forbidItems = new ArrayList<Item>();
		}
		
		public PlaceBuilder requiredItems(ArrayList<Item> reqItems) {
			this.reqItems = reqItems;
			return this;
		}
		
		public PlaceBuilder forbiddenItems(ArrayList<Item> forbidItems) {
			this.forbidItems = forbidItems;
			return this;
		}
		
		public PlaceBuilder containedItems(ArrayList<Item> containItems) {
			this.containedItems = containItems;
			return this;
		}
		public Place build() {
			return new Place(this);
		}
	}
}
