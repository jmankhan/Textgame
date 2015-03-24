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
		map.add(new Place.PlaceBuilder("place1", "desc", null).build());
		
		Game test2 = new Game(map);
		test2.setInGame(false);
		assertEquals(control.getInventory(), test2.getInventory());
		assertEquals(control.getItemByDescription(new Action("pick", "pencil", Action.ActionType.PICK)),
				test2.getItemByDescription(new Action("pick", "pencil", Action.ActionType.PICK)));		
		assertEquals(control.isValidCoordinate(new Coordinate(0,0,0)), test2.isValidCoordinate(new Coordinate(0,0,0)));
		Assert.assertNotEquals(control.isValidCoordinate(new Coordinate(1,0,0)), test2.isValidCoordinate(new Coordinate(1,0,0)));
		
	}

}
