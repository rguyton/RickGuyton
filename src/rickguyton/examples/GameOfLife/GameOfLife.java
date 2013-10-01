package rickguyton.examples.GameOfLife;

public class GameOfLife implements Runnable{

	/**
	 * Rules for the Game Of Life
	 * 
	 * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, 
	 * each of which is in one of two possible states, alive or dead. 
	 * Every cell interacts with its eight neighbors, which are the cells that are horizontally, vertically, or diagonally adjacent.
	 *  
	 * At each step in time, the following transitions occur:
	 *      Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	 *      Any live cell with two or three live neighbors lives on to the next generation.
	 *      Any live cell with more than three live neighbors dies, as if by overcrowding.
	 *      Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 *      
	 * The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously 
	 * to every cell in the seed—births and deaths occur simultaneously, 
	 * and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). 
	 * The rules continue to be applied repeatedly to create further generations.
	 **/
	
	private Cell[][] grid;
	private final int rows;
	private final int columns;
	
	GameOfLife(int columns, int rows, int generations){
		grid = new Cell[columns][rows];
		this.rows = rows;
		this.columns = columns;
		
		for(int c = 0; c < columns; c++){
			for(int r = 0; r < rows; r++){
				grid[c][r] = new Cell(c, r);
			}
		}
		
		for(int i = 0; i < generations; i++){
			step();
		}
	}
	
	public GameOfLife(Cell[][] grid, int rows, int columns){
		this.grid = grid;
		this.rows = rows;
		this.columns = columns;
	
	}
	

	public Cell[][] step(){
		Cell[][] newGrid = new Cell[columns][rows];
		Cell cell;
		
		for (int c = 0; c < columns ; c++){
			for(int r = 0; r < rows ; r++){
				
				try{
					newGrid[c][r] = new Cell (c, r);
					cell = grid[c][r];
					//above
					if(r > 0 && grid[c][r-1].isAlive()){
						cell.neighbors++;
					}
					//below
					if(r < rows -1 && grid[c][r+1].isAlive()){
						cell.neighbors++;
					}
					//left
					if(c > 0 && grid[c-1][r].isAlive()){
						cell.neighbors++;
					}
					//right
					if(c < columns -1 && grid[c+1][r].isAlive()){
						cell.neighbors++;
					}
					//upper left corner
					if((r > 0 && c > 0) && grid[c-1][r-1].isAlive()){
						cell.neighbors++;
					}
					//upper right corner
					if((r > 0 && c < columns -1) && grid[c+1][r-1].isAlive()){
						cell.neighbors++;
					}
					//bottom left corner
					if((r < rows -1 && c > 0) && grid[c-1][r+1].isAlive()){
						cell.neighbors++;
					}
					//bottom right corner
					if((r < rows -1 && c < columns -1) && grid[c+1][r+1].isAlive()){
						cell.neighbors++;
					}

					if(cell.isAlive()){
						if(cell.neighbors == 2 || cell.neighbors == 3){
							newGrid[c][r].setIsAlive(true);
						}else{
							newGrid[c][r].setIsAlive(false);
						}
					}else{
						if(cell.neighbors == 3){
							newGrid[c][r].setIsAlive(true);
						}
					}
				} catch (ArrayIndexOutOfBoundsException e){
					//do nothing
				}
			}//for
		}//for
		grid = newGrid;
		return newGrid;
	}

	@Override
	public void run() {
		step();
	}
	
	
	
}
