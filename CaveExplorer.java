package DSA_AS3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	// Step 3: solve method
	
	// Step 4: getPath method
	
	// Step 5: One parameter constructor to read from a file
	/*
	public CaveExplorer (String fname) throws Exception {
	      Scanner in = new Scanner(new File(fname));

	      int rows = in.nextInt();
	      int cols = in.nextInt();  
    	  String s = in.nextLine(); // Skip newline character
    	   	  
    	  // Construct cave and populate with rest of data in the file
    	  
	}
	*/

	
	public static void main(String[] args) {
		System.out.println("Starting CaveExplorer");
		
		// Create a CaveExplorer object and print the starting layout
		
		// Call solve
		
		// Print the final layout, whether there is a path, and if so, what it is.
		
		// Step 5/6: Repeat for a different CaveExplorer object read from a file
		// Uncomment code below to start testing your 1-parameter constructor

		/*
		String fileName = "testdat.txt"; 
		try { 
			CaveExplorer ce2 = new CaveExplorer(fileName); 
		} 
		catch (FileNotFoundException e ) {
			System.out.println("Can't find file " + fileName); 
		}
		catch (Exception e) {
			System.out.println("Other error: " + e.getMessage());
		}
		*/
		 

		
		System.out.println("Finished CaveExplorer");
	}

}
