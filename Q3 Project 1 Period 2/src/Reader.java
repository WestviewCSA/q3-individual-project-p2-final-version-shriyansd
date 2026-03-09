import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Reader {
	

	public static void main(String[] args) {
		
		String[][] maze = getText("Easy_Map_2");
		for(int i = 0; i < maze.length; i++) {
		    for(int j = 0; j < maze[0].length; j++) {
		        System.out.print(maze[i][j]);
		    }
		    System.out.println();
		}
		
		
		System.out.println("");
		
		String[][] n = getCords("Easy_Map_Coordinates");
		
		for(int i = 0; i < n.length; i++) {
			for(int j = 0; j < n[0].length; j++) {
				System.out.print(n[i][j]);
			}
			System.out.println();
		}
		
		

	}
	
	public static String[][] getText(String passedFile) {
		
		File fileObj = new File(passedFile);
		try {
				
			Scanner scan = new Scanner(fileObj);
			
			int rows = Integer.parseInt(scan.next());
			int cols = Integer.parseInt(scan.next());
			int maps = Integer.parseInt(scan.next());
			
			String[][] maze = new String[rows][cols];
			
			int currentRow = 0;
			
			while(scan.hasNext()) {
				String line = scan.next();
				for(int col = 0; col < line.length(); col++) {
					maze[currentRow][col] = String.valueOf(line.charAt(col));
				}
				currentRow++;
			}
			
			return maze;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	public static String[][] getCords (String passedFile) {
		
		String rows = "";		
		String columns = "";
		String maps = "";
		

		
		File fileObj = new File(passedFile);
		try {
			
			
			Scanner scan = new Scanner(fileObj);
			rows = scan.next();
			columns = scan.next();
			maps = scan.next();
			
			String[][] cordBased = new String[Integer.parseInt(rows)][Integer.parseInt(columns)];
			
			while(scan.hasNext()) {
				
				String character = scan.next();
				int rowC = Integer.parseInt(scan.next());
				int colC = Integer.parseInt(scan.next());
				
				
				if(rowC >= Integer.parseInt(rows) || colC >= Integer.parseInt(columns)) {
					System.out.println("Coordinates don't match the given specs");
					String[][] empty = new String[0][0];
					return empty;	
					
				}
				
				cordBased[rowC][colC] = character;
				scan.next();
			}
			
			for(int i = 0; i < cordBased.length; i++) {
				for(int j = 0; j < cordBased[0].length; j++) {
					if(cordBased[i][j] == null) {
						cordBased[i][j] = ".";
					}
				}

			}
			
			return cordBased;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}

}
