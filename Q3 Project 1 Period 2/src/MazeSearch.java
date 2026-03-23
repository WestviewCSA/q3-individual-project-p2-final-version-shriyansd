import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class MazeSearch {
	
	public static Queue<int[]> optSearch(String[][][] maze) {
	    return queueSearch(maze);
	}

    public static Queue<int[]> queueSearch(String[][][] maze) {
        Queue<int[]> toVisit = new ArrayDeque<>();
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
        toVisit.add(new int[]{startRow, startCol, 0});
        enqueued[0][startRow][startCol] = true;
        while(!toVisit.isEmpty()) {
            int[] current = toVisit.poll();
            int row = current[0];
            int col = current[1];
            int level = current[2];
            if(!maze[level][row][col].equals("W")) visited.add(current);
            if(maze[level][row][col].equals("|")) {
                int nextLevel = level + 1;
                for(int i = 0; i < maze[nextLevel].length; i++) {
                    for(int j = 0; j < maze[nextLevel][0].length; j++) {
                        if(maze[nextLevel][i][j].equals("W") && !enqueued[nextLevel][i][j]) {
                            toVisit.add(new int[]{i, j, nextLevel});
                            enqueued[nextLevel][i][j] = true;
                            parentRow[nextLevel][i][j] = row;
                            parentCol[nextLevel][i][j] = col;
                            parentLevel[nextLevel][i][j] = level;
                        }
                    }
                }
                continue;
            }
            // north
            if(row-1 >= 0 && !enqueued[level][row-1][col]) {
                if(maze[level][row-1][col].equals(".") || maze[level][row-1][col].equals("$") || maze[level][row-1][col].equals("|")) {
                    toVisit.add(new int[]{row-1, col, level});
                    enqueued[level][row-1][col] = true;
                    parentRow[level][row-1][col] = row;
                    parentCol[level][row-1][col] = col;
                    parentLevel[level][row-1][col] = level;
                    if(maze[level][row-1][col].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row-1, col, level);
                        return visited;
                    }
                }
            }
            // south
            if(row+1 < maze[level].length && !enqueued[level][row+1][col]) {
                if(maze[level][row+1][col].equals(".") || maze[level][row+1][col].equals("$") || maze[level][row+1][col].equals("|")) {
                    toVisit.add(new int[]{row+1, col, level});
                    enqueued[level][row+1][col] = true;
                    parentRow[level][row+1][col] = row;
                    parentCol[level][row+1][col] = col;
                    parentLevel[level][row+1][col] = level;
                    if(maze[level][row+1][col].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row+1, col, level);
                        return visited;
                    }
                }
            }
            // east
            if(col+1 < maze[level][0].length && !enqueued[level][row][col+1]) {
                if(maze[level][row][col+1].equals(".") || maze[level][row][col+1].equals("$") || maze[level][row][col+1].equals("|")) {
                    toVisit.add(new int[]{row, col+1, level});
                    enqueued[level][row][col+1] = true;
                    parentRow[level][row][col+1] = row;
                    parentCol[level][row][col+1] = col;
                    parentLevel[level][row][col+1] = level;
                    if(maze[level][row][col+1].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row, col+1, level);
                        return visited;
                    }
                }
            }
            // west
            if(col-1 >= 0 && !enqueued[level][row][col-1]) {
                if(maze[level][row][col-1].equals(".") || maze[level][row][col-1].equals("$") || maze[level][row][col-1].equals("|")) {
                    toVisit.add(new int[]{row, col-1, level});
                    enqueued[level][row][col-1] = true;
                    parentRow[level][row][col-1] = row;
                    parentCol[level][row][col-1] = col;
                    parentLevel[level][row][col-1] = level;
                    if(maze[level][row][col-1].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row, col-1, level);
                        return visited;
                    }
                }
            }
        }
        return visited;
    }

    public static Queue<int[]> stackSearch(String[][][] maze) {
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
        toVisit.push(new int[]{startRow, startCol, 0});
        enqueued[0][startRow][startCol] = true;
        while(!toVisit.isEmpty()) {
            int[] current = toVisit.pop();
            int row = current[0];
            int col = current[1];
            int level = current[2];
            if(!maze[level][row][col].equals("W")) visited.add(current);
            if(maze[level][row][col].equals("|")) {
                int nextLevel = level + 1;
                for(int i = 0; i < maze[nextLevel].length; i++) {
                    for(int j = 0; j < maze[nextLevel][0].length; j++) {
                        if(maze[nextLevel][i][j].equals("W") && !enqueued[nextLevel][i][j]) {
                            toVisit.push(new int[]{i, j, nextLevel});
                            enqueued[nextLevel][i][j] = true;
                            parentRow[nextLevel][i][j] = row;
                            parentCol[nextLevel][i][j] = col;
                            parentLevel[nextLevel][i][j] = level;
                        }
                    }
                }
                continue;
            }
            // north
            if(row-1 >= 0 && !enqueued[level][row-1][col]) {
                if(maze[level][row-1][col].equals(".") || maze[level][row-1][col].equals("$") || maze[level][row-1][col].equals("|")) {
                    toVisit.push(new int[]{row-1, col, level});
                    enqueued[level][row-1][col] = true;
                    parentRow[level][row-1][col] = row;
                    parentCol[level][row-1][col] = col;
                    parentLevel[level][row-1][col] = level;
                    if(maze[level][row-1][col].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row-1, col, level);
                        return visited;
                    }
                }
            }
            // south
            if(row+1 < maze[level].length && !enqueued[level][row+1][col]) {
                if(maze[level][row+1][col].equals(".") || maze[level][row+1][col].equals("$") || maze[level][row+1][col].equals("|")) {
                    toVisit.push(new int[]{row+1, col, level});
                    enqueued[level][row+1][col] = true;
                    parentRow[level][row+1][col] = row;
                    parentCol[level][row+1][col] = col;
                    parentLevel[level][row+1][col] = level;
                    if(maze[level][row+1][col].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row+1, col, level);
                        return visited;
                    }
                }
            }
            // east
            if(col+1 < maze[level][0].length && !enqueued[level][row][col+1]) {
                if(maze[level][row][col+1].equals(".") || maze[level][row][col+1].equals("$") || maze[level][row][col+1].equals("|")) {
                    toVisit.push(new int[]{row, col+1, level});
                    enqueued[level][row][col+1] = true;
                    parentRow[level][row][col+1] = row;
                    parentCol[level][row][col+1] = col;
                    parentLevel[level][row][col+1] = level;
                    if(maze[level][row][col+1].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row, col+1, level);
                        return visited;
                    }
                }
            }
            // west
            if(col-1 >= 0 && !enqueued[level][row][col-1]) {
                if(maze[level][row][col-1].equals(".") || maze[level][row][col-1].equals("$") || maze[level][row][col-1].equals("|")) {
                    toVisit.push(new int[]{row, col-1, level});
                    enqueued[level][row][col-1] = true;
                    parentRow[level][row][col-1] = row;
                    parentCol[level][row][col-1] = col;
                    parentLevel[level][row][col-1] = level;
                    if(maze[level][row][col-1].equals("$")) {
                        MazeOutput.tracePath(maze, parentRow, parentCol, parentLevel, row, col-1, level);
                        return visited;
                    }
                }
            }
        }
        return visited;
    }

}