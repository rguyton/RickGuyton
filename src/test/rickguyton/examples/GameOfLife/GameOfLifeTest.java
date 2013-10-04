package test.rickguyton.examples.GameOfLife;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rickguyton.examples.GameOfLife.Cell;
import rickguyton.examples.GameOfLife.GameOfLife;

public class GameOfLifeTest {
	
	private Cell cell;
	
	private Cell[][] grid = new Cell[3][3];
	private Cell[][] grid2 = new Cell[3][3];
	
	@Before
	public void setUp(){
		cell = new Cell (1, 1);
		for(int c = 0; c < 3; c++){
			for (int r = 0; r < 3; r++){
				grid[c][r] = new Cell(c, r);
				grid2[c][r] = new Cell(c, r);
			}
		}
	}
	
	@After
	public void tearDown(){
		cell = null;
		grid = null;
		grid2 = null;
	}

	@Test
	public void testCellStartsDead() {
		assertFalse(cell.isAlive());
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGridOutOfBounds(){
		grid[grid.length +1][grid.length + 1].setIsAlive(true);
	}
	
	@Test
	public void testBlinkerStep(){

		grid[1][0].setIsAlive(true);              //XXX
		grid[1][1].setIsAlive(true);              //OOO
		grid[1][2].setIsAlive(true);              //XXX
		
		GameOfLife game = new GameOfLife(grid, 3, 3);
		grid = game.step(); //step through game of life once.
		
		assertTrue(grid[0][1].isAlive());
		assertTrue(grid[1][1].isAlive());  
		assertTrue(grid[2][1].isAlive());
	}
	
	@Test
	public void testCellAlive(){
		cell.setIsAlive(true);
		
		assertTrue(cell.isAlive());
	}
	
	@Test
	public void testCellNeighbors(){
		assertTrue(true);
	}

}

