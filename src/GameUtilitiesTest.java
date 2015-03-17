import static org.junit.Assert.*;

import org.junit.After;
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
	
	

}
