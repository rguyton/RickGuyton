package test.rickguyton.examples.towersofhanoi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rickguyton.examples.towersofhanoi.Disk;
import rickguyton.examples.towersofhanoi.Peg;
import rickguyton.examples.towersofhanoi.TowerOfHanoi;

public class TowersOfHanoiTest {
	
	private ArrayList<Disk> disks = new ArrayList<Disk>();
	private Peg[]  pegs = new Peg[3];
	
	@Before
	public void setUp(){
		disks.add(new Disk (1));
		disks.add(new Disk (2));
		disks.add(new Disk (3));
		disks.add(new Disk (4));
		
		pegs[0] = new Peg ("Left");
		pegs[1] = new Peg ("Middle");
		pegs[2] = new Peg ("Right");
		
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test 
	public void testConstructor(){
		TowerOfHanoi t = new TowerOfHanoi(disks, pegs);
		
		assertTrue("discs length 4", disks.size() == t.getDisksCount());
		assertTrue("pegs length 3", pegs.length == t.getPegCount());
	}
	
	@Test
	public void testMovesMade(){
		disks.add(new Disk (5));
		TowerOfHanoi t = new TowerOfHanoi(disks, pegs);
		double minMoves = Math.pow(2, disks.size()) -1;
		assertTrue("Moves made = minimum moves", t.getMovesMade() == minMoves);
	}
	
}
