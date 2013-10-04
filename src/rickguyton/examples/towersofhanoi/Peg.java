package rickguyton.examples.towersofhanoi;

public class Peg {
	
	private String label;
	private static int discCount;

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public void addDisc(Disc disc) {
		discCount++;
	}
	
	public int getDiscCount(){
		return discCount;
	}

}
