import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ActionTest {

	private Action action;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		action = null;
	}

	@Test
	public void testEmptyName() {
		try {
			action = new Action(null, null, null);
			assertEquals("", action.getName());
			assertEquals(action.getDescription(), null);
			assertEquals(action.getType(), Action.ActionType.INVALID);
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testValidName() {
		try {
			action = new Action("name", null, null);
			assertEquals(action.getName(), "name");
			assertEquals(action.getDescription(), null);
			assertEquals(action.getType(), Action.ActionType.INVALID);
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testValidNameDesc() {
		try {
			action = new Action("name", "desc", null);
			assertEquals(action.getName(), "name");
			assertEquals(action.getDescription(), "desc");
			assertEquals(action.getType(), Action.ActionType.INVALID);
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testType() {
		try {
			action = new Action("name", "desc", Action.ActionType.MOVE);
			assertEquals(action.getName(), "name");
			assertEquals(action.getDescription(), "desc");
			assertEquals(action.getType(), Action.ActionType.MOVE);
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testSetException() {
		try{
			action = new Action(null, null, null);
			assertEquals(action.getName(), null);
			assertEquals(action.getDescription(), null);
			assertEquals(action.getType(), null);
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testEquals() {
		Action control = new Action("name", "desc", Action.ActionType.MOVE);
		Action test = new Action("name", "desc", Action.ActionType.MOVE);
		assertEquals(control, test);

		test.setType(Action.ActionType.DROP);
		assertNotEquals(control, test);

		test = null;
		assertNotEquals(control, test);
	}

}
