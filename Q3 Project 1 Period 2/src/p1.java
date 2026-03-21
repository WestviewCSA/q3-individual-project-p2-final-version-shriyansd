import java.io.File;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class p1 {
	

	public static void main(String[] args) {
		
		String[][][] maze = MazeReader.getText("ThreeMazeText");
		
		System.out.println("----Queue Search----");
		Queue<int[]> visited = MazeSearch.queueSearch(maze);
		MazeOutput.printTextMap(maze);

		System.out.println("----Stack Search----");
		String[][][] maze2 = MazeReader.getText("ThreeMazeText");
		Queue<int[]> visitedStack = MazeSearch.stackSearch(maze2);
		MazeOutput.printTextMap(maze2);
		
		
	}
	
}