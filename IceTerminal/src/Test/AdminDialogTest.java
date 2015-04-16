package Test;

import org.junit.Test;

import junit.framework.TestCase;
import logic.Item;

public class AdminDialogTest extends TestCase {

	private Item test;
	
	public void setUp(){
		test = new Item("test",10);
	}

	@Test
	public void test() {
		double expected1 = 10.0;
		assertEquals(expected1,this.test.getPrice());
		
		String expected2 = "test";
		assertEquals(expected2,this.test.getName());
	}

}
