package test.rickguyton.examples.towersofhanoi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rickguyton.examples.towersofhanoi.Disk;
import rickguyton.examples.towersofhanoi.Peg;

public class PegTest {
	
	Peg peg;

	@Before
	public void setUp() throws Exception {
		peg = new Peg("A");
	}

	@After
	public void tearDown() throws Exception {
		peg = null;
	}

	@Test
	public void PegLabelText() {
		peg.setLabel("B");
		
		assertTrue(peg.getLabel().equals("B"));
	}
	
	@Test
	public void PegDiscCountTest() {
		peg.addDisk(new Disk(1));
		
		assertTrue("getDiscCount(): " +peg.getDiskCount(), peg.getDiskCount() == 1);
	}
	
	@Test
	public void PegMultipleDiscCountTest(){
		peg.addDisk(new Disk(2));
		peg.addDisk(new Disk(1));
		assertTrue("Actual Disk Count: " + peg.getDiskCount(), peg.getDiskCount() == 2);
	}
	
	@Test
	public void testToString(){
		peg.addDisk(new Disk(5));
		peg.addDisk(new Disk(4));
		
		assertTrue("ToString(): " +peg.toString(), peg.toString().equals("A [5, 4]"));
	}

}
