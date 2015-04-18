import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		
		Game control = new Game();
		control.setInGame(false);
		Game test = new Game();
		test.setInGame(false);
		
		String[] itemValues = {"money"};
		assertEquals(control.getInventory(), test.getInventory());
		assertEquals(control.isValidCoordinate(new Coordinate(0,0,0)), test.isValidCoordinate(new Coordinate(0,0,0)));
		assertEquals(control.convertToAction("drop", itemValues), test.convertToAction("drop", itemValues));
		
		ArrayList<Place> map = new ArrayList<Place>();
		ArrayList<Item> defaultItems = new ArrayList<Item>();
		defaultItems.add(new Item("pencil", "writing utensil"));
		map.add(new Place.PlaceBuilder("place1", "desc", new Coordinate(0,0,0)).containedItems(defaultItems).build());
		
		Game test2 = new Game(map);
		test2.setInGame(false);
		assertEquals(control.getInventory(), test2.getInventory());
		assertEquals(control.getItemByName(new Action("pick", "Pencil", Action.ActionType.PICK)), new Item("Pencil", "Standard Writing Utensil"));

		test2.getItemByName(new Action("pick", "pencil", Action.ActionType.PICK));		
		assertEquals(control.isValidCoordinate(new Coordinate(0,0,0)), test2.isValidCoordinate(new Coordinate(0,0,0)));
	}

	@Test
	public void testDisplay() {
		Game test = new Game();

		assertEquals("abc", test.display("abc"));
		assertEquals("", test.display(""));
		assertEquals(" ", test.display(" "));
		assertEquals(null, test.display(null));
		assertEquals("asdf 14 v5435gf", test.display("asdf 14 v5435gf"));
	}
	
	@Test
	public void testConvertToAction() {
		Game test = new Game();
		
		Action testAction = new Action("User command", "desc", Action.ActionType.INVALID);
		assertEquals(testAction, test.convertToAction("User command", new String[]{"desc"}));
		Assert.assertEquals(testAction, test.convertToAction("not User Command", new String[]{"desc"}));
		
		try {
			testAction = null;
			assertEquals(testAction, test.convertToAction(null, null));
		} catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
		
		testAction = new Action("an action", "a description", Action.ActionType.DROP);
		Assert.assertNotEquals(testAction, test.convertToAction("action", new String[]{"not a description"}));
	}
	
	@Test
	public void testDrop() {
		Game control = new Game();
		Game test = new Game();
		
		ArrayList<Item> base = new ArrayList<Item>();
		base.add(new Item("A", "A description"));
		base.add(new Item("B", "B description"));
		base.add(new Item("C", "C description"));

		control.setInventory(base);
		test.setInventory(base);
		assertEquals(test.getInventory(), control.getInventory());
		
		test.doDrop(new Action("Drop", "A", Action.ActionType.DROP));
		control.getInventory().remove(0);
		assertEquals(test.getInventory(), control.getInventory());
		
		try{
			test.doDrop(new Action(null, null, null));
			assertEquals(test.getInventory(), control.getInventory());
		} catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
	}
	
	@Test
	public void testPickup() {
		Game control = new Game();
		Game test = new Game();
		
		ArrayList<Item> base = new ArrayList<Item>();
		base.add(new Item("A", "A description"));
		base.add(new Item("B", "B description"));
		base.add(new Item("C", "C description"));

		control.setInventory(base);
		test.setInventory(base);
		
		test.doPickup(new Action("pickup", "pencil", Action.ActionType.PICK));
		assertEquals(test.getInventory().get(3), new Item("Pencil", "Standard Writing Utensil"));
	}
	
	@Test
	public void testAllWordsToActions() {
		Game g = new Game();
		ArrayList<String> stringlist = new ArrayList<String>();
		stringlist.add("a string");
		stringlist.add("");
		stringlist.add(" ");
		stringlist.add("!23");
		stringlist.add("!@#$%kjkjk");

		ArrayList<Action> toTest = g.generateActionFromStringList(stringlist, Action.ActionType.MOVE);
		for(int i=0; i<stringlist.size(); i++) {
			assertEquals(toTest.get(i), new Action("name", stringlist.get(i), Action.ActionType.MOVE));
		}
	}
}
