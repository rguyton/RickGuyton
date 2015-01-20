package test.rickguyton.examples.towersofhanoi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rickguyton.examples.towersofhanoi.Disk;

public class DiskTest {
	
	private Disk disk;

	@Before
	public void setUp() throws Exception {
		disk = new Disk(1);
	}

	@After
	public void tearDown() throws Exception {
		disk = null;
	}

	@Test
	public void testDisc() {
		Disk disk2 = new Disk(2);
		
		assertTrue(disk2.getSize() == 2);
	}

	@Test
	public void testSetSize() {
		assertTrue(disk.getSize() == 1);
		
		disk.setSize(5);
		
		assertTrue(disk.getSize() == 5);
	}
	
	@Test
	public void testToString() {
		String size = "1";
		assertTrue(disk.toString().equals(size));
	}
}
