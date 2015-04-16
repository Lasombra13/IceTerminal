package Test;

import org.junit.Test;

import junit.framework.TestCase;
import logic.Terminal;

public class TerminalTest extends TestCase {
	
	private Terminal test;
	
	public void setUp(){
		 test = new Terminal();
	}
	
	@Test
	public void test() {
		String expected = "frame0";
		assertEquals(expected,this.test.getName());
	}

}
