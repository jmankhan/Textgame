
public class Item extends GameObject {

	public Item(String name, String description) {
		super(name, description);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Item) {
			Item object = (Item) o;
			
			if(this.name.equalsIgnoreCase(object.getName()) && this.description.equalsIgnoreCase(object.getDescription())) {
				return true;
			}
		}
		
		return false;
	}
}
