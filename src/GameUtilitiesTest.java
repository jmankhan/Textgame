import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GameUtilitiesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArrayContains() {
		String[] array = {"a", "b", "c"};
		
		//assert true
		String val = "a";
		assertTrue(GameUtilities.arrayContains(val, array));
		
		val = "b";
		assertTrue(GameUtilities.arrayContains(val, array));
		
		val = "c";
		assertTrue(GameUtilities.arrayContains(val, array));

		val = "A";
		assertTrue(GameUtilities.arrayContains(val, array));
		
		//assert false
		val = "d";
		assertFalse(GameUtilities.arrayContains(val, array));
		
		val = "BSCV";
		assertFalse(GameUtilities.arrayContains(val, array));
		
		val = "123";
		assertFalse(GameUtilities.arrayContains(val, array));
		
		val = "";
		assertFalse(GameUtilities.arrayContains(val, array));
		
		//null values
		array = null;
		assertFalse(GameUtilities.arrayContains(val, array));
		
		val = null;
		assertFalse(GameUtilities.arrayContains(val, array));
		
		//empty values
		array = new String[1];
		array[0] = "";
		
		val = "a";
		assertFalse(GameUtilities.arrayContains(val, array));
		
		val = "";
		assertTrue(GameUtilities.arrayContains(val, array));
	}
	
	@Test
	public void testEquivCoord() {

		Coordinate control = new Coordinate(0,0,0);
		assertEquals(control, GameUtilities.getEquivalentCoordinate(new Coordinate(0,0,0)));
		
		Coordinate falseTest = new Coordinate(1,1,1);
		Assert.assertNotEquals(control, falseTest);
		
		Coordinate negativeFalseTest = new Coordinate(-1,-1,-1);
		Assert.assertNotEquals(control, negativeFalseTest);
		
		//shoulda auto initialize to -1,-1,-1
		Coordinate nullTest = new Coordinate();
		Assert.assertNotEquals(control, nullTest);
		
	}

	@Test
	public void testFindPlace() {

		Place p1 = new Place.PlaceBuilder("place", "desc", new Coordinate(0,0,0)).build();
		Place p2 = new Place.PlaceBuilder("place2", "desc2", new Coordinate(1,0,0)).build();
		Place p3 = new Place.PlaceBuilder("place3", "desc3", new Coordinate(2,0,0)).build();
		
		ArrayList<Place> map = new ArrayList<Place>();
		map.add(p1);
		map.add(p2);
		map.add(p3);
		
		assertEquals(p1, GameUtilities.findPlace(map, new Coordinate(0,0,0)));
		assertEquals(p2, GameUtilities.findPlace(map, new Coordinate(1,0,0)));
		assertEquals(p3, GameUtilities.findPlace(map, new Coordinate(2,0,0)));
		
		try {
			Assert.assertNotEquals(p1, GameUtilities.findPlace(map, new Coordinate(-1,-1,-1)));
		} catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
		
		try {
			Assert.assertNotEquals(p1, GameUtilities.findPlace(map, new Coordinate(0,0,1)));
		} catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
		
		try {
			Assert.assertNotEquals(p2, GameUtilities.findPlace(map, new Coordinate(0,0,0)));
		} catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
		
		try {
			Assert.assertNotEquals(p2, GameUtilities.findPlace(map, new Coordinate(-87681,367867,5471235-67)));
		} catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
	}
}

