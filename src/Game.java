import java.util.Scanner;

import Action.ActionType;


public class Game {

	/**
	 * Takes user input from console
	 */
	private Scanner in;

	/**
	 * Flag for starting and stopping the game loop
	 */
	private boolean inGame = false;
	
	public Game() {
		
	}

	public void play() {
		
		//display intro
		display("Welcome to Muhlenberg Simulator 2015!");
		
		while(this.inGame) {
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
		String values = "";
		for(int i=1; i<words.length; i++) {
			values += words[i];
		}
	}
	
	public Action determineAction(String keyword, String[] values) {
		String description = "User command";
		String name = "";
		Action.ActionType type = Action.ActionType.INVALID;
		
		for(Action.ActionType a : Action.ActionType.values()) {
			if(a.name().equals(keyword)) {
				type = a;
			}
		}
		
		return new Action(name, description, type);
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
