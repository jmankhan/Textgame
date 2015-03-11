import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Game {

	/**
	 * Max Coordinate values for this map
	 */
	private int width, //max x
				length,//max y 
				height;//max z
	
	/**
	 * Takes user input from console
	 */
	private Scanner in;

	/**
	 * Flag for starting and stopping the game loop
	 */
	private boolean inGame;

	/**
	 * Coordinate that keeps track of player Location
	 */
	private Coordinate currentLocation;
	
	/**
	 * List of all the Places the user can go to in this game
	 */
	private ArrayList<Place> map;

	/**
	 * List of Items that player currently has
	 */
	private static ArrayList<Item> inventory;
	
	public Game() {
		this(null);
	}
	
	public Game(ArrayList<Place> map) {
		if(map == null) {
			this.map = this.generateMap();
		}
		else {
			this.map = map;
		}
		
		currentLocation = new Coordinate(0,0,0);
		
		width = GameUtilities.findMaxX(GameUtilities.getCoords(this.map));
		length= GameUtilities.findMaxY(GameUtilities.getCoords(this.map));
		height= GameUtilities.findMaxZ(GameUtilities.getCoords(this.map));

		this.inGame		= true;
		this.in			= new Scanner(System.in);
		this.inventory	= new ArrayList<Item>();
		
		play();
	}

	/**
	 * The main game loop. Will run until the inGame boolean return false. Can only be called by the Game constructor
	 */
	private void play() {
		
		//display intro
		display("Welcome to Muhlenberg Simulator 2015!");
		
		while(this.inGame) {
			//display Place name + description
			Place currentPlace = GameUtilities.findPlace(map, currentLocation); 
			display("You are currently in " + currentPlace.getName() + ". The sign says: " + currentPlace.getDescription());
			
			//prompt the user for input
			display("What would you like to do?");
			
			//get input
			String input = this.getNextUserInput();
			
			//parse the input
			handleInput(input);
		}
	}
	
	/**
	 * Gets the next user command from console
	 * @return String command
	 */
	public String getNextUserInput() {
		return in.nextLine();
	}
	
	/**
	 * Prints the parameter to console
	 * @param output String to print
	 * @return String that was printed
	 */
	public String display(String output) {
		System.out.println(output);
		return output;
	}
	
	/**
	 * Parses the input and passes it to doAction(). 
	 * Input structure is expected to be [keyword] [value1] [value2]...
	 * [keyword] would be something like "move" or "drop"
	 * [value] would be something like "west" or "item 1"
	 * @param input String user input
	 */
	public void handleInput(String input) {
		//splits the input by empty space into an array of individual words
		String[] words = input.split(" ");
		
		String keyword = words[0];
		String[] values = new String[words.length];
		
		if(words.length < 2 || keyword == null) {
			display("Please type a noun after a verb");
			return;
		}
		
		System.arraycopy(words, 1, values, 0, words.length-1);
		
		Action userAction = convertToAction(keyword, values);
		handleAction(userAction);
	}
	
	/**
	 * Converts the user input to an Action object by parsing the Strings received
	 * @param keyword String that will be converted to ActionType
	 * @param values String that will be the name of the Action, still needs to be interpreted
	 * @return
	 */
	public Action convertToAction(String keyword, String[] values) {

		String description = "";
		for(String s:values)
			if(s!=null)
				description += s;
		
		String name = "User command";
		Action.ActionType type = Action.ActionType.INVALID;
		
		for(Action.ActionType a : Action.ActionType.values()) {
			if(a.name().equalsIgnoreCase(keyword)) {
				type = a;
			}
		}
		
		return new Action(name, description, type);
	}
	
	/**
	 * Responds to the given action by moving or interacting with items 
	 * @param action user Action to respond to
	 */
	public void handleAction(Action action) {
		switch(action.getType()) {
		case MOVE:	currentLocation = doMove(action);		break;
		
		
		default: //do nothing
			break;
		}
	}
	
	/**
	 * Converts a given String to a Coordinate position relative to a given origin
	 * @param origin Coordinate checking against, typically current player location
	 * @param direction String direction given by player
	 * @return Coordinate desired position
	 */
	public Coordinate directionToCoordinate(Coordinate origin, String direction) {
		Coordinate desired = GameUtilities.getEquivalentCoordinate(this.currentLocation);
		
		if(GameUtilities.arrayContains(direction, GameUtilities.leftWords))
			desired.setDeltaX(-1);
		else if(GameUtilities.arrayContains(direction, GameUtilities.rightWords))
			desired.setDeltaX(1);
		else if(GameUtilities.arrayContains(direction, GameUtilities.downWords))
			desired.setDeltaY(-1);
		else if(GameUtilities.arrayContains(direction, GameUtilities.upWords))
			desired.setDeltaY(1);
		
		return desired;
	}
	
	/**
	 * Checks if the desired location is within the bounds of the map and if the player fills the requirements to move there
	 * @param desired
	 * @return true if can move there
	 */
	public boolean isValidCoordinate(Coordinate desired) {
		if(desired.getX() >= 0 && desired.getX() <= width
				&& desired.getY() >= 0 && desired.getY() <= length
				&& desired.getZ() >= -1 && desired.getZ() <= height)
			return true;
		
		return false;
	}
	
	/**
	 * Moves the player to the desired Coordinate by checking the Action
	 * @param action Action of type MOVE
	 * @return Place moved to if able, else same place is returned
	 */
	public Coordinate doMove(Action action) {
		String direction = action.getDescription();
		if(this.isValidCoordinate(directionToCoordinate(currentLocation, direction))) {
			return directionToCoordinate(currentLocation, direction);
		}
		return currentLocation;
	}
	
	/**
	 * Generates a default map if the Game does not receive a map
	 * @return ArrayList<Place> default map
	 */
	public ArrayList<Place> generateMap() {
		ArrayList<Place> map = new ArrayList<Place>();
		
		ArrayList<Item> itemList1 = new ArrayList<Item>();
		itemList1.add(new Item("Pencil", "Standard Writing utensil"));
		itemList1.add(new Item("Pen", "Superior Writing Utensil"));
		
		map.add(new Place.PlaceBuilder("Moyer",		"Politics and Religion",new Coordinate(0,3,0)).build());
		map.add(new Place.PlaceBuilder("Ettinger",	"Liberal Arts", 		new Coordinate(0,2,0)).build());
		map.add(new Place.PlaceBuilder("Haas",		"Administration", 		new Coordinate(0,1,0)).build());
		map.add(new Place.PlaceBuilder("Trumbower", "Science", 				new Coordinate(0,0,0)).containedItems(itemList1).build());
		
		map.add(new Place.PlaceBuilder("Library",				"Resources", new Coordinate(1,3,0)).build());
		map.add(new Place.PlaceBuilder("Center for the Arts",	"Fine Arts", new Coordinate(1,2,0)).build());
		
		return map;
	}

	/**
	 * Gets a List of the current Items in the player's possesion
	 * @return ArrayList<Item>
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
