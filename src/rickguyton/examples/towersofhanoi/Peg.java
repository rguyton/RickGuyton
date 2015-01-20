package rickguyton.examples.towersofhanoi;

import java.util.ArrayList;
import java.util.List;

public class Peg {
	
	private String label;
	private int diskCount;
	private ArrayList<Disk> disksOnPeg = new ArrayList<Disk>();
	
	public Peg(String label){
		this.label = label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public void addDisk(Disk disk) {
		diskCount++;
		//always add to the "top" of te list
		disksOnPeg.add(0,disk);
	}
	
	public int getDiskCount(){
		return diskCount;
	}
	
	public Disk removeDisk(Disk disk)
	{
		//check to see if disc exists on peg
		System.out.println("Removing Disk: " +disk.getSize());
		System.out.println("Actually Removing: " +disksOnPeg.get(0).getSize());
		return disksOnPeg.remove(0);
	}
	
	public Disk removeTopDisk(){
		diskCount--;
		return disksOnPeg.remove(0);
		
	}
	
	public void setStartingDisks(ArrayList<Disk> startingDisks){
		disksOnPeg.addAll(startingDisks);
		diskCount = disksOnPeg.size();
	}
	
	@Override
	public String toString()
	{
		return getLabel() +" "+ disksOnPeg.toString();
	}

}
