import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CoordinateTest {

	private Coordinate coord;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		coord = null;
	}

	@Test
	public void testConstructorsAndSetters() {
		coord = new Coordinate();
		assertEquals(coord.getX(), -1);
		assertEquals(coord.getY(), -1);
		assertEquals(coord.getZ(), -1);
		
		coord.setX(1);
		coord.setY(234);
		coord.setZ(-123);
		assertEquals(coord.getX(), 1);
		assertEquals(coord.getY(), 234);
		assertEquals(coord.getZ(), -123);
		
		coord = new Coordinate(1, 10, 100);
		coord.setDeltaX(9);
		coord.setDeltaY(0);
		coord.setDeltaZ(-90);
		assertEquals(coord.getX(), 10);
		assertEquals(coord.getY(), 10);
		assertEquals(coord.getZ(), 10);
	}

	@Test
	public void testEquals() {
		Coordinate control = new Coordinate(0,0,0);
		Coordinate test = new Coordinate(0,0,0);
		
		assertEquals(control, test);
		
		test.setX(1);
		assertNotEquals(control, test);
		
		control.setX(1);
		assertEquals(control, test);
	}
	
	@Test
	public void testToString() {
		Coordinate test = new Coordinate(0,0,0);
		Coordinate control = new Coordinate(0,0,0);
		
		assertEquals(test.toString(), control.toString());
		
		control.setX(1);
		assertNotEquals(test.toString(), control.toString());
	}
}
