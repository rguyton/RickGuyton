package rickguyton.examples.towersofhanoi;

import java.util.ArrayList;

public class TowerOfHanoi {

	/**
	 * The objective of the puzzle is to move the entire stack to another rod, obeying the following simple rules:
     * Only one disk can be moved at a time.
     * Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack i.e. a disk can only be moved if it is the uppermost disk on a stack.
     * No disk may be placed on top of a smaller disk.
	 */
	private ArrayList<Disk> disks;
	private Peg[]  pegs;
	private int movesMade;
	
	public TowerOfHanoi(ArrayList<Disk> disks, Peg[] pegs){
		this.disks = disks;
		this.pegs = pegs;
		
		pegs[0].setStartingDisks(disks);
		
		System.out.println("Initialized game.  Pegs: " +pegs.length);
		System.out.println("Initialized game.  Disks: " +disks.size());
		System.out.println("Initialized game.  Peg 1 setup as: " +pegs[0].toString());
		double minmoves = Math.pow(2, disks.size()) -1;
		System.out.println("Initialized game.  Minimum moves to \"win\" = " +minmoves );
		start();
	}
	
	private void move(int disks, Peg from, Peg helper, Peg to){
		if(disks == 0){
			return;
		}else{
			move(disks-1, from, to, helper);
			
			to.addDisk(from.removeTopDisk());
			
			movesMade++;
			System.out.println("Moved a disk with a size of: " +disks);
			System.out.println("Moved disk to Peg: " +to.getLabel());
			System.out.println("After move peg looks like: " +to.toString());
			System.out.println("Move complete, number of moves made: " +movesMade);
			
			move(disks-1, helper, from, to);
		}
	}
	
	private void start(){
		System.out.println("Starting game...");
		move(getDisksCount(), pegs[0], pegs[1], pegs[2]);
	}
	
	public int getDisksCount() { return disks.size(); }
	
	public int getPegCount()   { return pegs.length;  }
	
	public int getMovesMade()  { return movesMade; }
}
