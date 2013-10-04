package test.rickguyton.examples.towersofhanoi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rickguyton.examples.towersofhanoi.Disc;
import rickguyton.examples.towersofhanoi.Peg;

public class PegTest {
	
	Peg peg;

	@Before
	public void setUp() throws Exception {
		peg = new Peg();
	}

	@After
	public void tearDown() throws Exception {
		peg = null;
	}

	@Test
	public void PegLabelText() {
		peg.setLabel("A");
		
		assertTrue(peg.getLabel().equals("A"));
	}
	
	@Test
	public void PegDiscCountTest() {
		peg.addDisc(new Disc(1));
		
		assertTrue(peg.getDiscCount() == 1);
	}
	
	@Test
	public void PegMultipleDiscCountTest(){
		peg.addDisc(new Disc(2));
		
		assertTrue("Actual Disc Count: " + peg.getDiscCount(), peg.getDiscCount() == 2);
	}

}
