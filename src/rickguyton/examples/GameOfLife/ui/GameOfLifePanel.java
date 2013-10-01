package rickguyton.examples.GameOfLife.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import rickguyton.examples.GameOfLife.Cell;

public class GameOfLifePanel extends JPanel {
	
	Cell[][] grid;
	int columns;
	int rows;
	
	GameOfLifePanel(Cell[][] grid, int columns, int rows){
		this.grid = grid;
		this.columns = columns;
		this.rows = rows;
	}
	
	public void setGrid(Cell[][] grid){
		this.grid = grid;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		double boxWidth = (double)(this.getWidth()) / columns;
		double boxHeight = (double)(this.getHeight()) / rows;
		
		for (int i = 0; i < columns; i++){
			for (int j = 0; j < rows; j++){
				if (grid[i][j].isAlive()){
					g.setColor(Color.RED);
					g.fillRect((int)(i * boxWidth), (int)(j * boxHeight), (int)(boxWidth), (int)(boxHeight));
				}
				g.setColor(Color.BLACK);
				g.drawLine(0, (int)(j * boxHeight), this.getWidth(), (int)(j * boxHeight));
			}
			g.setColor(Color.BLACK);
			g.drawLine((int)(i * boxWidth), 0, (int)(i * boxWidth), this.getHeight());
		}
	}
	
}
