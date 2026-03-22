import java.util.Queue;

public class MazeOutput {

    public static void tracePath(String[][][] maze, int[][][] parentRow, int[][][] parentCol, int[][][] parentLevel, int dollarRow, int dollarCol, int dollarLevel) {
        int currentRow = dollarRow;
        int currentCol = dollarCol;
        int currentLevel = dollarLevel;
        while(true) {
            if(!maze[currentLevel][currentRow][currentCol].equals("W") && !maze[currentLevel][currentRow][currentCol].equals("$")) {
                maze[currentLevel][currentRow][currentCol] = "+";
            }
            if(maze[currentLevel][currentRow][currentCol].equals("W") && currentLevel == 0) break;
            int tempRow = parentRow[currentLevel][currentRow][currentCol];
            int tempCol = parentCol[currentLevel][currentRow][currentCol];
            int tempLevel = parentLevel[currentLevel][currentRow][currentCol];
            currentRow = tempRow;
            currentCol = tempCol;
            currentLevel = tempLevel;
        }
    }

    public static void printTextMap(String[][][] maze) {
        for(int level = 0; level < maze.length; level++) {
            for(int i = 0; i < maze[level].length; i++) {
                for(int j = 0; j < maze[level][0].length; j++) {
                    System.out.print(maze[level][i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void printCoordinates(Queue<int[]> visited, String[][][] maze) {
        for(int[] pos : visited) {
        	if(maze[pos[2]][pos[0]][pos[1]].equals("+")) {
                System.out.println("+ " + pos[0] + " " + pos[1] + " " + pos[2]);
            }
        }
    }
    
    public static boolean noSolution(String[][][] maze) {
        for(int level = 0; level < maze.length; level++) {
            for(int i = 0; i < maze[level].length; i++) {
                for(int j = 0; j < maze[level][0].length; j++) {
                    if(maze[level][i][j].equals("+")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}	