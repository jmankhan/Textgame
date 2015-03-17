import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GameObjectTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testObjects() {
		GameObject goAction = new Action("action", "action desc", Action.ActionType.INVALID);
		GameObject goPlace = new Place.PlaceBuilder("Place", "Place desc", null).build();
		GameObject goItem = new Item("item", "item desc");

		GameObject[] objects = {goAction, goPlace, goItem};
		//test all objects against each other object
		for(int i=0; i<objects.length-1; i++) {
			
			assertNotNull(objects[i]);
			
			for(int j=i+1; j<objects.length; j++) {
				assertNotEquals(objects[i], objects[j]);
				assertNotSame(objects[i], objects[j]);
			}
		}
		
		goItem = new Item(null, null);
		assertEquals(goItem.getName(), "");
		assertEquals(goItem.getDescription(), null);
		
		goItem = new Item("name", null);
		assertEquals(goItem.getName(), "name");
		assertEquals(goItem.getDescription(), null);
		
		goItem = new Item(null, "desc");
		assertEquals(goItem.getName(), "");
		assertEquals(goItem.getDescription(), "desc");
	}
	
	public void testGetters() {
		GameObject testItem = new Item("name", "desc");
		assertEquals(testItem.getName(), "name");
		assertEquals(testItem.getDescription(), "desc");
		
		GameObject testPlace = new Place.PlaceBuilder("name", "desc", null).build();
		assertEquals(testPlace.getName(), "name");
		assertEquals(testPlace.getDescription(), "desc");
		
		GameObject testAction = new Action("name", "desc",null);
		assertEquals(testAction.getName(), "name");
		assertEquals(testAction.getDescription(), "desc");
	}
}