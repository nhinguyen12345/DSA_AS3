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
	
    private int[][] parentRow; // Track the parent row
    private int[][] parentCol; // Track the parent column

//hihkbguhikscvb dsdefgdfgnbijnk
public CaveExplorer(char[][] caveLayout) {
	this.cave = caveLayout;
	this.parentRow = new int[cave.length][cave[0].length]; // Initialize parent tracking arrays
	this.parentCol = new int[cave.length][cave[0].length]; // Initialize parent tracking arrays
}


    public CaveExplorer() {
        
        cave = new char[5][6]; // 5 rows and 6 columns

        // Populate the cave with the given layout
        cave[0] = new char[] {'R', 'R', 'R', 'R', 'R', 'R'};
        cave[1] = new char[] {'R', '.', '.', 'S', 'R', 'R'};
        cave[2] = new char[] {'R', '.', 'R', 'R', 'R', 'R'};
        cave[3] = new char[] {'R', '.', 'M', 'R', 'R', 'R'};
        cave[4] = new char[] {'R', 'R', 'R', 'R', 'R', 'R'};
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
	// Step 2-4: Extend solve method (I had some trouble syncing to GH after step 2 so I had to reopen the file)
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
		if (endRow == -1 || endCol == -1) return ""; // No path found
		StringBuilder path = new StringBuilder();
		int row = endRow;
		int col = endCol;
		
		while (parentRow[row][col] != -1 && parentCol[row][col] != -1) {
			int prevRow = parentRow[row][col];
			int prevCol = parentCol[row][col];
		
			if (prevRow == row - 1) path.append('s');
			else if (prevRow == row + 1) path.append('n');
			else if (prevCol == col - 1) path.append('e');
			else if (prevCol == col + 1) path.append('w');
		
			row = prevRow;
			col = prevCol;
			}
		
			return path.reverse().toString();
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
        // Define 4 test caves
        char[][][] caves = {
            // Test case 1: A simple cave layout
            {
                {'S', '.', '.', '#'},
                {'#', '#', '.', '#'},
                {'.', '.', '.', 'M'},
                {'#', '#', '#', '#'}
            },
            // Test case 2: A more complex layout with obstacles
            {
                {'S', '.', '#', 'M'},
                {'#', '.', '#', '#'},
                {'#', '.', '.', '.'},
                {'#', '#', '#', '#'}
            },
            // Test case 3: Cave layout requiring backtracking
            {
                {'#', 'S', '#', '#'},
                {'#', '.', '.', '#'},
                {'#', '#', '.', 'M'},
                {'#', '#', '#', '#'}
            },
            // Test case 4: Another backtracking case
            {
                {'S', '.', '.', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'},
                {'#', '.', 'M', '#'}
            }
        };

        // Iterate over all the test caves
        for (int i = 0; i < caves.length; i++) {
            System.out.println("=== Cave " + (i + 1) + " ===");

            // Create a CaveExplorer object using the char[][] layout
            CaveExplorer explorer = new CaveExplorer(caves[i]);

            // Print the starting layout
            System.out.println("Start layout:");
            System.out.println(explorer.toString());  // Use the toString() method for printing the cave layout

            // Solve and get the path
            boolean foundPath = explorer.solve();

            // Print the final layout after solving
            System.out.println("Final layout:");
            System.out.println(explorer.toString());

            // Print the path if found
            if (foundPath) {
                System.out.println("Path to mirror pool: " + explorer.getPath());
            } else {
                System.out.println("No path to mirror pool.");
            }

            System.out.println(); // Space between test cases
        }
    }
}

	