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
		this.type = type;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
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