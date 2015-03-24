import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PlaceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuilder() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item("item", "desc"));
		
		Place control = new Place.PlaceBuilder("name", "desc", null).build();
		Place test1 = new Place.PlaceBuilder("name", "desc", null).build();
		Place test2 = new Place.PlaceBuilder("name1", "desc", new Coordinate(1,2,3)).build();
		Place test3 = new Place.PlaceBuilder("name", "desc", null).containedItems(items).build();
		Place test4 = new Place.PlaceBuilder("name", "desc", null).requiredItems(items).build();
		Place test5 = new Place.PlaceBuilder("name", "desc", null).forbiddenItems(items).build();
		Place[] tests = {test1, test3, test4, test5};

		for(Place test : tests) {

			assertEquals(control.getName(), 			test.getName());
			assertEquals(control.getDescription(), 		test.getDescription());
			assertEquals(control.getCoords(), 			test.getCoords());

		}
		
		Assert.assertNotEquals(control.getCoords(), 			test2.getCoords());
		Assert.assertNotEquals(control.getContainedItems(), 	test3.getContainedItems());
		Assert.assertNotEquals(control.getForbidItems(), 		test4.getReqItems());
		Assert.assertNotEquals(control.getReqItems(),	 		test5.getForbidItems());
		
		test1.setReqItems(items);
		Assert.assertNotEquals(control.getReqItems(), test1.getReqItems());

		test1.setContainedItems(items);
		control.setContainedItems(items);
		Assert.assertEquals(control.getContainedItems(), test1.getContainedItems());
		
		test1.setForbidItems(items);
		control.setForbidItems(items);
		Assert.assertEquals(control.getForbidItems(), test1.getForbidItems());
	}

}
