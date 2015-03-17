import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ItemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		Item item = new Item("name", "desc");
		assertEquals("name", item.getName());
		assertEquals("desc", item.getDescription());
		
		item.setName("NOT A NAME");
		item.setDescription("NOT A DESC");
		
		assertEquals("NOT A NAME", item.getName());
		assertEquals("NOT A DESC", item.getDescription());
		
		item.setName("123YTRW\nb\n\n");
		item.setDescription("!#$%(YGFJP@#");
		
		assertEquals("123YTRW\nb\n\n", item.getName());
		assertEquals("!#$%(YGFJP@#", item.getDescription());
	}

}
