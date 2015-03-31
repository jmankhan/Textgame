/**
 * Handles player interaction with environment, including moving between Places, picking up, using, and dropping
 * Item. This class needs to be flexible enough to easily add new functionality in almost any direction
 * @author Jalal
 *
 */
public class Action extends GameObject {

	enum ActionType {
		MOVE, PICK, DROP, USE, INVALID;
	};

	private ActionType type;
	
	public Action(String name, String description, ActionType type) {
		super(name, description);
		this.type = setType(type);
	}

	public ActionType getType() {
		return this.type;
	}

	public ActionType setType(ActionType type) throws IllegalArgumentException {
		if(type == null) {throw new IllegalArgumentException("Type cannot be null. Use INVALID instead");}
		
		this.type = type;
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Action) {
			Action a = (Action) o;

			if(this.name.equalsIgnoreCase(a.getName())
					&& this.description.equalsIgnoreCase(a.getDescription())
					&& this.getType().equals(a.getType())) {
				return true;
			}
		}
		return false;
	}
}
