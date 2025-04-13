package DSA_AS3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import org.w3c.dom.Node;

public class CaveExplorer {
	// Step 1: Zero parameter constructor to create the specified cave
	private char[][] cave;
//hihkbguhikscvb dsdefgdfgnbijnk
    
    public CaveExplorer() {
        
        cave = new char[5][6]; // 5 rows and 6 columns

        // Populate the cave with the given layout
        cave[0] = new char[] {'R', 'R', 'R', 'R', 'R', 'R'};
        cave[1] = new char[] {'R', '.', '.', 'S', 'R', 'R'};
        cave[2] = new char[] {'R', '.', 'R', 'R', 'R', 'R'};
        cave[3] = new char[] {'R', '.', 'M', 'R', 'R', 'R'};
        cave[4] = new char[] {'R', 'R', 'R', 'R', 'R', 'R'};
    }
	private static class Node {
		int row, col;
		String path;
	
		Node(int row, int col, String path) {
			this.row = row;
			this.col = col;
			this.path = path;
		}
		
	}
	// Step 2: toString method
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Loop through the cave array and build the string with newlines
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[i].length; j++) {
                sb.append(cave[i][j]);
            }
            sb.append("\n"); // Add a newline after each row
        }

        return sb.toString(); // Return the constructed string
    }
	private int endRow = -1, endCol = -1; // Coordinates of 'M'

	// Step 3: solve method
	// Step 2-4: Extend solve method
	public boolean solve() {
		int rows = cave.length;
		int cols = cave[0].length;

		// Direction vectors (up, down, left, right)
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};

		// Find starting position 'S'
		int startRow = -1, startCol = -1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (cave[i][j] == 'S') {
					startRow = i;
					startCol = j;
					break;
				}
			}
		}

		// Sanity check
		if (startRow == -1 || startCol == -1) return false;

		// ****** Visited tracker
		boolean[][] visited = new boolean[rows][cols];

		// DFS stack
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {startRow, startCol});
		visited[startRow][startCol] = true;
		int[][] parentRow = new int[cave.length][cave[0].length];
    	int[][] parentCol = new int[cave.length][cave[0].length];
		parentRow[startRow][startCol] = -1;
		parentCol[startRow][startCol] = -1;
		while (!stack.isEmpty()) {
			int[] curr = stack.pop();
			int row = curr[0], col = curr[1];

			if (cave[row][col] == 'M') {
				endRow = row;
				endCol = col;
				return true;
			}			

			// Explore 4 directions
			for (int i = 0; i < 4; i++) {
				int newRow = row + dRow[i];
				int newCol = col + dCol[i];

				if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
					&& !visited[newRow][newCol]
					&& (cave[newRow][newCol] == '.' || cave[newRow][newCol] == 'M')) {

					visited[newRow][newCol] = true;
					stack.push(new int[] {newRow, newCol});
					parentRow[newRow][newCol] = row;
                	parentCol[newRow][newCol] = col;
				}
			}
    }

    return false; // M not found
}


	// Step 4: getPath method
	public String getPath() {
		
}

	// Step 5: One parameter constructor to read from a file
	
	public CaveExplorer (String fname) throws Exception {
		
	    Scanner in = new Scanner(new File(fname));

	    int rows = in.nextInt();
	    int cols = in.nextInt();  
    	String s = in.nextLine(); // Skip newline character
    	   	  
    	  // Construct cave and populate with rest of data in the file
    	cave = new char[rows][cols];

		for (int i = 0; i < rows; i++) {
			String line = in.nextLine();
			for (int j = 0; j < cols; j++) {
				cave[i][j] = line.charAt(j);
			}
		}

			in.close();
	}
	

	
	public static void main(String[] args) {
		System.out.println("Starting CaveExplorer");
		
		// Create a CaveExplorer object and print the starting layout
		CaveExplorer ce1 = new CaveExplorer();
   		System.out.println("Cave 1 Starting Layout:");
    	System.out.println(ce1.toString());

		// Call solve
		boolean pathFound1 = ce1.solve();

		// Print the final layout, whether there is a path, and if so, what it is.
		System.out.println("Cave 1 Final Layout:");
    	System.out.println(ce1.toString());
    	if (pathFound1) {
        	System.out.println("Path to mirror: " + ce1.getPath());
    	} else {
        	System.out.println("No path to mirror pool found.");
    	}

		// Step 5/6: Repeat for a different CaveExplorer object read from a file
		// Uncomment code below to start testing your 1-parameter constructor

		String fileName = "testdat.txt"; 
		try { 
			CaveExplorer ce2 = new CaveExplorer(fileName); 

			System.out.println("Cave 2 Starting Layout:");
			System.out.println(ce2.toString());

			boolean pathFound2 = ce2.solve();

			System.out.println("Cave 2 Final Layout:");
			System.out.println(ce2.toString());

			if (pathFound2) {
				System.out.println("Path to mirror: " + ce2.getPath());
			} else {
				System.out.println("No path to mirror pool found.");
			}
		} 
		catch (FileNotFoundException e ) {
			System.out.println("Can't find file " + fileName); 
		}
		catch (Exception e) {
			System.out.println("Other error: " + e.getMessage());
		}


		 

		
		System.out.println("Finished CaveExplorer");
	}

}
