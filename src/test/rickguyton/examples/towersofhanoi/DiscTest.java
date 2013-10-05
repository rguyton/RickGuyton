package test.rickguyton.examples.towersofhanoi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rickguyton.examples.towersofhanoi.Disc;

public class DiscTest {
	
	private Disc disc;

	@Before
	public void setUp() throws Exception {
		disc = new Disc(1);
	}

	@After
	public void tearDown() throws Exception {
		disc = null;
	}

	@Test
	public void testDisc() {
		Disc disc2 = new Disc(2);
		
		assertTrue(disc2.getSize() == 2);
	}

	@Test
	public void testSetSize() {
		assertTrue(disc.getSize() == 1);
		
		disc.setSize(5);
		
		assertTrue(disc.getSize() == 5);
	}
}
