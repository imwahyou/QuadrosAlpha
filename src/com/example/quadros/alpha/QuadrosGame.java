package com.example.quadros.alpha;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuadrosGame {
	
	private static final int BOARD_LIMIT = 5;
	private static final int LEVEL_LIMIT = 10;
	
	private int score;
	private int life;
	private int currentLevel;
	private int rows;
	private int cols;
	private Set<Integer> selectedCells;
	private boolean[] board;
	private int boardSize;
	
	public QuadrosGame() {									        
		score = 0;
		life = 4;
		currentLevel = 0;
		rows = 3;
		cols = 3;
		selectedCells = new HashSet<Integer>();
		boardSize = rows*cols;
		board = new boolean[boardSize];
		generateCells(this.currentLevel);
	}
	
	public void generateCells(int level) {
		Random rand = new Random();
		while (this.selectedCells.size() < level+3) {
			int r = rand.nextInt(this.rows * this.cols);
			this.selectedCells.add(r);
		}
	}
	
	public boolean setMove(int location) {
		if(location < 0 || location > this.boardSize)
			throw new IllegalArgumentException("location must be between 0 and board size inclusive: " + location);
		
		if(!this.board[location] && selectedCells.contains(location)) {
			this.board[location] = true;
			this.score += 10;
			
			return true;
		} else {
			this.life--;
			return false;
		}
	}
	
	public HashSet<Integer> getSelectedCells() {
		return (HashSet<Integer>) this.selectedCells;
	}
	
	public boolean getBoardLocation(int i) {
		return this.board[i];
	}
	
	public void setBoard(int i, boolean value) {
		this.board[i] = value;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public int getCurrentLevel() {
		return this.currentLevel;
	}
	
	public int getBoardSize() {
		return this.boardSize;
	}
	
	public static void main(String args[]) {
		
		Random rand = new Random();
		Set<Integer> selected_cells = new HashSet<Integer>();
		//Scanner input = new Scanner(System.in);
		
		//generate boards
		for (int i = 0; i < LEVEL_LIMIT; i++) {
			int rows = ((i + 3) / 2) + 2;
			int cols = ((i + 4) / 2) + 1;
			
			// rows and cols cap at 5
			if (rows*cols >= Math.pow(BOARD_LIMIT, 2)) {
				rows = 5;
				cols = 5;
			}
			
			System.out.println("level[" + i + "]: " + rows + " x " + cols);
			for (int l = 1; l <= 5; l++) {
				System.out.print("   board[" + l + "] : ");
				
				selected_cells = new HashSet<Integer>();
				while (selected_cells.size() < i+3) {
					selected_cells.add(rand.nextInt(rows*cols));
				}
				
				System.out.println(selected_cells.toString());
			}
		}
	}
}
