/**
 * A superclass that will handle common functionality of classes used within the Game
 * @author Jalal
 *
 */
public abstract class GameObject {

	/**
	 * Identifier fields
	 */
	protected String name, description;
	
	protected GameObject() {
		
	}
	
	/**
	 * Creates a new GameObject with a String for its name and description. Most classes will use this functionality
	 * @param name String of this GameObject's name
	 * @param description String of this GameObject's description
	 */
	public GameObject(String name, String description) {
		this.name = setName(name);
		this.description = description;
	}

	/**
	 * Get this GameObject's name
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get this GameObject's description
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set this GameObject's name. Null will be converted to an empty String
	 * @param name String name of this GameObject
	 * @return 
	 */
	public String setName(String name) {
		if(name == null)
			name = "";
		
		this.name = name;
		return name;
	}

	/**
	 * Sets this GameObject's description. Null is allowed
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}