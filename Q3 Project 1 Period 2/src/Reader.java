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
		
		Queue<int[]> visited = queueSearch(maze);
		while(!visited.isEmpty()) {
		    int[] pos = visited.poll();
		    System.out.println(pos[0] + " " + pos[1]);
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
	
	public static Queue<int[]> queueSearch(String[][] maze){
		Queue <int[]> toVisit = new ArrayDeque<>();
		Queue<int[]> visited = new ArrayDeque<>();
		
		int startRow = 0;
		int startCol = 0;
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[0].length; j++) {
				if(maze[i][j].equals("W")) {
					startRow = i;
					startCol = j;
				}
			}
		}
		
		int[] start = new int[]{startRow, startCol};
		toVisit.add(start);
		
		while(!toVisit.isEmpty()) {
			
			int[] current = toVisit.poll();
			
			int row = current[0];
			int col = current[1];
			
			visited.add(current);
			
			//north
			if(row-1 >= 0) {
				 if(maze[row-1][col].equals(".") || maze[row-1][col].equals("$")) {
		                toVisit.add(new int[]{row-1, col});
		                if(maze[row-1][col].equals("$")) {
		                	return visited;
		                }
		            }
		        }
		        
		    //south
		    if(row+1 < maze.length) {
	            if(maze[row+1][col].equals(".") || maze[row+1][col].equals("$")) {
	                toVisit.add(new int[]{row+1, col});
	                if(maze[row+1][col].equals("$")) {
	                	return visited;
	                }
	            }
	        }
		        
		    //east
	        if(col+1 < maze[0].length) {
	            if(maze[row][col+1].equals(".") || maze[row][col+1].equals("$")) {
	                toVisit.add(new int[]{row, col+1});
	                if(maze[row][col+1].equals("$")) {
	                	return visited;
	                }
	            }
	        }
		        
		    //west
	        if(col-1 >= 0) {
	            if(maze[row][col-1].equals(".") || maze[row][col-1].equals("$")) {
	                toVisit.add(new int[]{row, col-1});
	                if(maze[row][col-1].equals("$")) {
	                	return visited;
	                }
	            }
	        }
		}
		
		return visited;
		
	}

}
