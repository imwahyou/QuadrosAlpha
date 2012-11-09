package com.example.quadros.alpha;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuadrosGame {
	
	private static final int BOARD_LIMIT = 5;
	private static final int LEVEL_LIMIT = 10;
	
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
	
	public static void displayBoard() {
		
	}
	
}
