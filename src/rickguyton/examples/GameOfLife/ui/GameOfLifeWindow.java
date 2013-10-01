package rickguyton.examples.GameOfLife.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import rickguyton.examples.GameOfLife.Cell;
import rickguyton.examples.GameOfLife.GameOfLife;

public class GameOfLifeWindow implements MouseListener, ActionListener, Runnable{

	JFrame frame = new JFrame("Conway's Game of Life");
	
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	
	Container south = new Container();
	
	Cell[][] grid;
	
	GameOfLifePanel panel;
	GameOfLife game;
	
	int rows, cols;
	boolean isRunning = false;
	
	public GameOfLifeWindow(int cols, int rows){
		
		this.rows = rows;
		this.cols = cols;
		
		grid = new Cell[cols][rows];
		for (int c=0; c < cols; c++){
			for(int r=0; r < rows; r++){
				grid[c][r] = new Cell(c,r);
			}
		}
		
		panel = new GameOfLifePanel(grid, cols, rows);
		
		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1, 3));
		south.add(step);
		south.add(start);
		south.add(stop);
		frame.add(south, BorderLayout.SOUTH);
		
		start.addActionListener(this);
		stop.addActionListener(this);
		step.addActionListener(this);
		
		panel.addMouseListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		GameOfLifeWindow game = new GameOfLifeWindow(30, 30);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("X: " +e.getX()+ ", Y: " +e.getY() );
		
		int row = e.getY() / (panel.getHeight() / rows);
		row = row > rows -1 ? rows -1 : row;
		System.out.println("Y: " +row);
		
		int col = e.getX() / (panel.getWidth() / cols);
		System.out.println("X: " +col);
		col = col > cols -1 ? cols -1 : col;

		Cell cell = grid[col][row];
		cell.setIsAlive(!cell.isAlive());
		
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(step)){
			game = new GameOfLife(grid, rows, cols);
			grid = game.step();
			panel.setGrid(grid);
			frame.repaint();
		}
		
		if(e.getSource().equals(stop)){
			isRunning = false;
			start.setEnabled(true);
			step.setEnabled(true);
		}
		
		if(e.getSource().equals(start)){
			isRunning = true;
			start.setEnabled(false);
			step.setEnabled(false);
			game = new GameOfLife(grid, rows, cols);
			Thread t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		while(isRunning){
			grid = game.step();
			panel.setGrid(grid);
			frame.repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
