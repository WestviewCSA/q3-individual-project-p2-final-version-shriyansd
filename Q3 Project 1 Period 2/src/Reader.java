import java.io.File;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Reader {
	

	public static void main(String[] args) {
		
		
		String[][][] maze = getText("ThreeMazeText");
		
		/*
		for(int level = 0; level < maze.length; level++) {
			for(int i = 0; i < maze[level].length; i++) {
			    for(int j = 0; j < maze[level][0].length; j++) {
			        System.out.print(maze[level][i][j]);
			    }
			    System.out.println();
			}
		}
		*/
		
		System.out.println("----Queue Search----");

	
		Queue<int[]> visited = queueSearch(maze);
		for(int level = 0; level < maze.length; level++) {
		    for(int i = 0; i < maze[level].length; i++) {
		        for(int j = 0; j < maze[level][0].length; j++) {
		            System.out.print(maze[level][i][j]);
		        }
		        System.out.println();
		    }
		}
		
		System.out.println("----Stack Search----");
		
	
		String[][][] maze2 = getText("ThreeMazeText");
		Queue<int[]> visitedStack = stackSearch(maze2);
		for(int level = 0; level < maze2.length; level++) {
		    for(int i = 0; i < maze2[level].length; i++) {
		        for(int j = 0; j < maze2[level][0].length; j++) {
		            System.out.print(maze2[level][i][j]);
		        }
		        System.out.println();
		    }
		}
	
		/*
		
		
	
		String[][][] n = getCords("ThreeMazeCords");
		
		for(int level = 0; level < n.length; level++) {
			for(int i = 0; i < n[level].length; i++) {
			    for(int j = 0; j < n[level][0].length; j++) {
			        System.out.print(n[level][i][j]);
			    }
			    System.out.println();
			}
		}
		
	*/
		

	}
	
	public static String[][][] getText(String passedFile) {
		
		File fileObj = new File(passedFile);
		try {
				
			Scanner scan = new Scanner(fileObj);
			
			int rows = Integer.parseInt(scan.next());
			int cols = Integer.parseInt(scan.next());
			int maps = Integer.parseInt(scan.next());
			
			String[][][] maze = new String[maps][rows][cols];
			
			int currentRow = 0;
			int currentLevel = 0;
			
			while(scan.hasNext()) {
				String line = scan.next();
				for(int col = 0; col < line.length(); col++) {
					maze[currentLevel][currentRow][col] = String.valueOf(line.charAt(col));
				}
				currentRow++;
				
				if(currentRow == rows) {
	                currentRow = 0;
	                currentLevel++;
	            }
			}
			
			return maze;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	public static String[][][] getCords (String passedFile) {
		
		String rows = "";		
		String columns = "";
		String maps = "";
		

		
		File fileObj = new File(passedFile);
		try {
			
			
			Scanner scan = new Scanner(fileObj);
			rows = scan.next();
			columns = scan.next();
			maps = scan.next();
			
			String[][][] cordBased = new String[Integer.parseInt(maps)][Integer.parseInt(rows)][Integer.parseInt(columns)];
			
			while(scan.hasNext()) {
				
				String character = scan.next();
				int rowC = Integer.parseInt(scan.next());
				int colC = Integer.parseInt(scan.next());
				int level = Integer.parseInt(scan.next());
				
				
				if(rowC >= Integer.parseInt(rows) || colC >= Integer.parseInt(columns)) {
					System.out.println("Coordinates don't match the given specs");
					String[][][] empty = new String[0][0][0];
					return empty;	
					
				}
				
				cordBased[level][rowC][colC] = character;
			}
			
			for(int k = 0; k < cordBased.length; k++) {
				for(int i = 0; i < cordBased[0].length; i++) {
					for(int j = 0; j < cordBased[0][0].length; j++) {
						if(cordBased[k][i][j] == null) {
							cordBased[k][i][j] = ".";
						}
					}

				}
			}
			
			return cordBased;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public static Queue<int[]> queueSearch(String[][][] maze){
		Queue <int[]> toVisit = new ArrayDeque<>();
		Queue<int[]> visited = new ArrayDeque<>();
		
		boolean[][][] enqueued = new boolean[maze.length][maze[0].length][maze[0][0].length];
		
		int[][][] parentRow = new int[maze.length][maze[0].length][maze[0][0].length];
	    int[][][] parentCol = new int[maze.length][maze[0].length][maze[0][0].length];
	    int[][][] parentLevel = new int[maze.length][maze[0].length][maze[0][0].length]; 
		
		int startRow = 0;
		int startCol = 0;
		for(int i = 0; i < maze[0].length; i++) {
			for(int j = 0; j < maze[0][0].length; j++) {
				if(maze[0][i][j].equals("W")) {
					startRow = i;
					startCol = j;
				}
			}
		}
		
		int[] start = new int[]{startRow, startCol, 0};
		toVisit.add(start);
		enqueued[0][startRow][startCol] = true;
		
		while(!toVisit.isEmpty()) {
			
			int[] current = toVisit.poll();
			
			
			int row = current[0];
			int col = current[1];
			int level = current[2];
			
			if(!maze[level][row][col].equals("W")) {
				visited.add(current);
			}
			
			if(maze[level][row][col].equals("|")) {
	            int nextLevel = level + 1;
	            for(int i = 0; i < maze[nextLevel].length; i++) {
	                for(int j = 0; j < maze[nextLevel][0].length; j++) {
	                    if(maze[nextLevel][i][j].equals("W")) {
	                        if(!enqueued[nextLevel][i][j]) {
	                            toVisit.add(new int[]{i, j, nextLevel});
	                            enqueued[nextLevel][i][j] = true;
	                            parentRow[nextLevel][i][j] = row;
	                            parentCol[nextLevel][i][j] = col;
	                            parentLevel[nextLevel][i][j] = level;
	                        }
	                    }
	                }
	            }
	            continue;
			}
			
			//north
			if(row-1 >= 0 && !enqueued[level][row-1][col]) {
				 if(maze[level][row-1][col].equals(".") || maze[level][row-1][col].equals("$") || maze[level][row-1][col].equals("|")) {
		                toVisit.add(new int[]{row-1, col, level});
		                enqueued[level][row-1][col] = true;
		                parentRow[level][row-1][col] = row;
		                parentCol[level][row-1][col] = col;
		                parentLevel[level][row-1][col] = level;
		                if(maze[level][row-1][col].equals("$")) {
		                	tracePath(maze, parentRow, parentCol, parentLevel, row-1, col, level);
		                	return visited;
		                }
		            }
		    }
		        
		    //south
		    if(row+1 < maze[level].length && !enqueued[level][row+1][col]) {
	            if(maze[level][row+1][col].equals(".") || maze[level][row+1][col].equals("$") || maze[level][row+1][col].equals("|")) {
	                toVisit.add(new int[]{row+1, col, level});
	                enqueued[level][row+1][col] = true;
	                parentRow[level][row+1][col] = row;
	                parentCol[level][row+1][col] = col;
	                parentLevel[level][row+1][col] = level;
	                if(maze[level][row+1][col].equals("$")) {
	                	tracePath(maze, parentRow, parentCol, parentLevel, row+1, col, level);
	                	return visited;
	                }
	            }
	        }
		        
		    //east
	        if(col+1 < maze[level][0].length && !enqueued[level][row][col+1]) {
	            if(maze[level][row][col+1].equals(".") || maze[level][row][col+1].equals("$") || maze[level][row][col+1].equals("|")) {
	                toVisit.add(new int[]{row, col+1, level});
	                enqueued[level][row][col+1] = true;
	                parentRow[level][row][col+1] = row;
	                parentCol[level][row][col+1] = col;
	                parentLevel[level][row][col+1] = level;
	                if(maze[level][row][col+1].equals("$")) {
	                	tracePath(maze, parentRow, parentCol, parentLevel, row, col+1, level);
	                	return visited;
	                }
	            }
	        }
		        
		    //west
	        if(col-1 >= 0 && !enqueued[level][row][col-1]) {
	            if(maze[level][row][col-1].equals(".") || maze[level][row][col-1].equals("$") || maze[level][row][col-1].equals("|")) {
	                toVisit.add(new int[]{row, col-1, level});
	                enqueued[level][row][col-1] = true;
	                parentRow[level][row][col-1] = row;
	                parentCol[level][row][col-1] = col;
	                parentLevel[level][row][col-1] = level;
	                if(maze[level][row][col-1].equals("$")) {
	                	tracePath(maze, parentRow, parentCol, parentLevel, row, col-1, level);
	                	return visited;
	                }
	            }
	        }
		}
		
		return visited;
		
	}
	
	public static Queue<int[]> stackSearch(String[][][] maze){
		Stack<int[]> toVisit = new Stack<>();
		Queue<int[]> visited = new ArrayDeque<>();
		
		boolean[][][] enqueued = new boolean[maze.length][maze[0].length][maze[0][0].length];
		
		int[][][] parentRow = new int[maze.length][maze[0].length][maze[0][0].length];
		int[][][] parentCol = new int[maze.length][maze[0].length][maze[0][0].length];
		int[][][] parentLevel = new int[maze.length][maze[0].length][maze[0][0].length];
		
		int startRow = 0;
		int startCol = 0;
		for(int i = 0; i < maze[0].length; i++) {
			for(int j = 0; j < maze[0][0].length; j++) {
				if(maze[0][i][j].equals("W")) {
					startRow = i;
					startCol = j;
				}
			}
		}
		
		int[] start = new int[]{startRow, startCol, 0};
		toVisit.push(start);
		enqueued[0][startRow][startCol] = true;
		
		while(!toVisit.isEmpty()) {
			
			int[] current = toVisit.pop();
			
			int row = current[0];
			int col = current[1];
			int level = current[2];
			
			if(!maze[level][row][col].equals("W")) {
				visited.add(current);
			}
			
			if(maze[level][row][col].equals("|")) {
	            int nextLevel = level + 1;
	            for(int i = 0; i < maze[nextLevel].length; i++) {
	                for(int j = 0; j < maze[nextLevel][0].length; j++) {
	                    if(maze[nextLevel][i][j].equals("W")) {
	                        if(!enqueued[nextLevel][i][j]) {
	                            toVisit.add(new int[]{i, j, nextLevel});
	                            enqueued[nextLevel][i][j] = true;
	                            parentRow[nextLevel][i][j] = row;
	                            parentCol[nextLevel][i][j] = col;
	                            parentLevel[nextLevel][i][j] = level;
	                        }
	                    }
	                }
	            }
	            continue;
			}
			
			
			//north
			if(row-1 >= 0 && !enqueued[level][row-1][col]) {
				 if(maze[level][row-1][col].equals(".") || maze[level][row-1][col].equals("$") || maze[level][row-1][col].equals("|")) {
		                toVisit.push(new int[]{row-1, col, level});
		                enqueued[level][row-1][col] = true;
		                parentRow[level][row-1][col] = row;
		                parentCol[level][row-1][col] = col;
		                parentLevel[level][row-1][col] = level;
		                if(maze[level][row-1][col].equals("$")) {
		                	tracePath(maze, parentRow, parentCol, parentLevel, row-1, col, level);
		                	return visited;
		                }
		            }
		        }
		        
		    //south
		    if(row+1 < maze[level].length && !enqueued[level][row+1][col]) {
	            if(maze[level][row+1][col].equals(".") || maze[level][row+1][col].equals("$") || maze[level][row+1][col].equals("|")) {
	                toVisit.push(new int[]{row+1, col, level});
	                enqueued[level][row+1][col] = true;
	                parentRow[level][row+1][col] = row;
	                parentCol[level][row+1][col] = col;
	                parentLevel[level][row+1][col] = level;
	                if(maze[level][row+1][col].equals("$")) {
	                	tracePath(maze, parentRow, parentCol, parentLevel, row+1, col, level);
	                	return visited;
	                }
	            }
	        }
		        
		    //east
	        if(col+1 < maze[level][0].length && !enqueued[level][row][col+1]) {
	            if(maze[level][row][col+1].equals(".") || maze[level][row][col+1].equals("$") || maze[level][row][col+1].equals("|")) {
	                toVisit.push(new int[]{row, col+1, level});
	                enqueued[level][row][col+1] = true;
	                parentRow[level][row][col+1] = row;
	                parentCol[level][row][col+1] = col;
	                parentLevel[level][row][col+1] = level;
	                if(maze[level][row][col+1].equals("$")) {
	                	tracePath(maze, parentRow, parentCol, parentLevel, row, col+1, level);
	                	return visited;
	                }
	            }
	        }
		        
		    //west
	        if(col-1 >= 0 && !enqueued[level][row][col-1]) {
	            if(maze[level][row][col-1].equals(".") || maze[level][row][col-1].equals("$") || maze[level][row][col-1].equals("|")) {
	                toVisit.push(new int[]{row, col-1, level});
	                enqueued[level][row][col-1] = true;
	                parentRow[level][row][col-1] = row;
	                parentCol[level][row][col-1] = col;
	                parentLevel[level][row][col-1] = level;
	                if(maze[level][row][col-1].equals("$")) {
	                	tracePath(maze, parentRow, parentCol, parentLevel, row, col-1, level);
	                	return visited;
	                }
	            }
	        }
		}
		
		return visited;
		
	}
	
	public static void tracePath(String[][][] maze, int[][][] parentRow, int[][][] parentCol, int[][][] parentLevel, int dollarRow, int dollarCol, int dollarLevel) {
		int currentRow = dollarRow;
		int currentCol = dollarCol;
		int currentLevel = dollarLevel;
		
		while(true) {
			 if(!maze[currentLevel][currentRow][currentCol].equals("W") && !maze[currentLevel][currentRow][currentCol].equals("$")) {
			        maze[currentLevel][currentRow][currentCol] = "+";
			 }
			 
			 if(maze[currentLevel][currentRow][currentCol].equals("W") && currentLevel == 0) {
		            break;
		     }
			 
			 int tempRow = parentRow[currentLevel][currentRow][currentCol];
		     int tempCol = parentCol[currentLevel][currentRow][currentCol];
		     int tempLevel = parentLevel[currentLevel][currentRow][currentCol];
		     currentRow = tempRow;
		     currentCol = tempCol;
		     currentLevel = tempLevel;
		}
	}

}
