import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Muhlenberg Text Adventure 2015 Game controller class. This will take input from the user, instantiate the game fields,
 * react to user input, update values, and basically function as the main controller
 * @author Jalal
 * @version 3/16/2015
 * Time expected: many hours
 * Time actual: many hours
 * Notes: GameTest has quite a few errors... I am still working on fixing them. Everything else should be fine though
 */
public class Game {

	public static void main(String args[]) {
		new Game();
	}

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
	private ArrayList<Item> inventory;

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

		this.inGame		= false;
		this.in			= new Scanner(System.in);
		inventory		= new ArrayList<Item>();

		new GameUtilities();
		play();
	}

	/**
	 * Chooses a random Action from similarly typed Actions, so the move forward command could result in moving backwards
	 * Apparently this adds something meaningful to the game
	 * @param actionPool
	 * @return
	 */
	public Action chooseRandom(ArrayList<Action> actionPool) {
		Random r = new Random();
		int index = r.nextInt(actionPool.size());
		return actionPool.get(index);
	}

	/**
	 * Converts the user input to an Action object by parsing the Strings received
	 * @param keyword String that will be converted to ActionType
	 * @param values String that will be the name of the Action, still needs to be interpreted
	 * @return
	 */
	public Action convertToAction(String keyword, String[] values) throws IllegalArgumentException {
		if(keyword == null || values == null) throw new IllegalArgumentException("Null values not allowed");
		
		String description = "";
		for(String s:values)
			if(s!=null)
				description += s;

		String name = "User command";
		Action.ActionType type = Action.ActionType.INVALID;

		for(Action.ActionType a : Action.ActionType.values()) {
			if(a.toString().equalsIgnoreCase(keyword)) {
				type = a;
			}
		}

		return new Action(name, description, type);
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
	 * Prints the parameter to console
	 * @param output String to print
	 * @return String that was printed
	 */
	public String display(String output) {
		System.out.println(output);
		return output;
	}

	/**
	 * Simulates dropping an item by adding it to the Place's inventory and removing it from the player's inventory
	 * @param action
	 */
	public void doDrop(Action action) {
		Item item = getItemByName(action);

		//remove from player inventory and add to Place inventory
		if(item != null) {
			inventory.remove(item);
			GameUtilities.findPlace(map, currentLocation).getContainedItems().add(item);
			display("Successfully dropped " + item.getName());
		}

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
	 * Simulates picking up an item by adding it to the player's inventory and removing it from the Place's inventory
	 * @param action
	 */
	public void doPickup(Action action) {
		Item item = getItemByName(action);

		//add item to inventory and remove from Place found
		if(item != null) {
			inventory.add(item);
			GameUtilities.findPlace(map, currentLocation).getContainedItems().remove(item);
			display("Successfully picked up " + item.getName());
		}

	}

	/**
	 * Generates a default map if the Game does not receive a map
	 * @return ArrayList<Place> default map
	 */
	public ArrayList<Place> generateMap() {
		ArrayList<Place> map = new ArrayList<Place>();
		
		try {
			ArrayList<Item> itemList1 = new ArrayList<Item>();
			itemList1.add(new Item("Pencil", "Standard Writing Utensil"));
			itemList1.add(new Item("Pen", "Superior Writing Utensil"));

			map.add(new Place.PlaceBuilder("Moyer",		"Politics and Religion",new Coordinate(0,3,0)).build());
			map.add(new Place.PlaceBuilder("Ettinger",	"Liberal Arts", 		new Coordinate(0,2,0)).build());
			map.add(new Place.PlaceBuilder("Haas",		"Administration", 		new Coordinate(0,1,0)).build());
			map.add(new Place.PlaceBuilder("Trumbower", "Science", 				new Coordinate(0,0,0)).containedItems(itemList1).build());
			map.add(new Place.PlaceBuilder("Library",			   "Resources", new Coordinate(1,3,0)).build());
			map.add(new Place.PlaceBuilder("Center for the Arts",  "Fine Arts", new Coordinate(1,2,0)).build());
		} catch(IllegalArgumentException e) {}
		return map;
	}

	/**
	 * Gets a List of the current Items in the player's possesion
	 * @return ArrayList<Item>
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public Item getItemByName(Action action) {
		String itemName = action.getDescription();
		Item item = null;

		for(Item i : GameUtilities.findPlace(map, currentLocation).getContainedItems()) {
			if(i.getName().equalsIgnoreCase(itemName)) {
				item = i;
			}
		}

		return item;
	}

	/**
	 * Gets the next user command from console
	 * @return String command
	 */
	public String getNextUserInput() {
		return in.nextLine();
	}

	/**
	 * Responds to the given action by moving or interacting with items 
	 * @param action user Action to respond to
	 */
	public void handleAction(Action action) {
		
		switch(action.getType()) {
		case MOVE:	currentLocation = doMove(this.chooseRandom
					(this.generateActionFromStringList(GameUtilities.allMoveWords, 
					Action.ActionType.MOVE)));				break; //deal with it
		case PICK:  doPickup(action);						break;
		case DROP:	doDrop(action);							break;

		default: //do nothing
			break;
		}
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
	 * Checks if the desired location can be found in the map and if the play is allowed to enter
	 * @param desired
	 * @return true if can move there
	 */
	public boolean isValidCoordinate(Coordinate desired) {
		if(GameUtilities.findPlace(map, desired) != null) {
			if(GameUtilities.findPlace(map, desired).canEnter()) {
				return true;
			}
		}

		return false;
	}
	/**
	 * The main game loop. Will run until the inGame boolean return false. Can only be called by the Game constructor
	 */
	private void play() {

		//display intro
		display("Welcome to Muhlenberg Simulator 2015!");

		while(this.inGame) {
			//display Place name + description + items found
			Place currentPlace = GameUtilities.findPlace(map, currentLocation); 
			display("You are currently in " + currentPlace.getName() + ". The sign says: " + currentPlace.getDescription() + ".\nYou see a ");
			for(Item i:currentPlace.getContainedItems())
				display(i.getName() + ", " + i.getDescription());

			//prompt the user for input
			display("What would you like to do?");

			//get input
			String input = this.getNextUserInput();

			//parse the input
			handleInput(input);
		}
	}
	
	/**
	 * Sets whether the game will start instantly or not. Mostly for testing purposes
	 * @param b
	 */
	public void setInGame(boolean b) {
		this.inGame = b;
	}

	/**
	 * Set players inventory to completely new List. For testing only 
	 * @param inv ArrayList<Item>
	 */
	public void setInventory(ArrayList<Item> inv) {
		this.inventory = inv;
	}
	
	public ArrayList<Action> generateActionFromStringList(ArrayList<String> strings, Action.ActionType type) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for(String s : strings) {
			actions.add(new Action("name", s, type));
		}
		return actions;
	}
}