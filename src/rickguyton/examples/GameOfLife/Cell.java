package rickguyton.examples.GameOfLife;

public class Cell {
	
	int row;
	int col;
	int neighbors;
	private boolean isAlive = false;
	
	public Cell(int col, int row){
		this.row = row;
		this.col = col;
		neighbors = 0;
	}
	
	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
}
