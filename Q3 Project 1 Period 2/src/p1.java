import java.io.File;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class p1 {
	

	public static void main(String[] args) throws IllegalCommandLineInputsException {

        boolean useStack = false;
        boolean useQueue = false;
        boolean useOpt = false;
        boolean useTime = false;
        boolean inCoordinate = false;
        boolean outCoordinate = false;
        
        for(String arg : args) {
            if(arg.equals("--Stack"))useStack = true;
            if(arg.equals("--Queue")) useQueue = true;
            if(arg.equals("--Opt")) useOpt = true;
            if(arg.equals("--Time")) useTime = true;
            if(arg.equals("--Incoordinate")) inCoordinate = true;
            if(arg.equals("--Outcoordinate")) outCoordinate = true;
            if(arg.equals("--Help")) {
            System.out.println("This program finds a path through a maze for Wolverine");
            System.out.println("--Stack: use stack based search");
            System.out.println("--Queue: use queue based search");
            System.out.println("--Opt: use optimal shortest path search");
            System.out.println("--Time: print runtime of the search");
            System.out.println("--Incoordinate: input file is coordinate based");
            System.out.println("--Outcoordinate: output is coordinate based");
            System.out.println("--Help: print this message");
            System.exit(0);
            }
        }
        
        if(!useStack && !useQueue && !useOpt) {
        	throw new IllegalCommandLineInputsException("Error: exactly one of --Stack, --Queue, or --Opt must be set");
        }
        
        if((useStack && useQueue) || (useStack && useOpt) || (useQueue && useOpt)) {
        	throw new IllegalCommandLineInputsException("Error: exactly one of --Stack, --Queue, or --Opt must be set");
        }
        
        String mapFile = args[args.length - 1];
        
        String[][][] maze = null;
        try {
        	if(inCoordinate) {
                maze = MazeReader.getCords(mapFile);
            } else {
                maze = MazeReader.getText(mapFile);
            }
        } catch (IncorrectMapFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (IllegalMapCharacterException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (IncompleteMapException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } 
        
        double startTime = System.currentTimeMillis();
        
        Queue<int[]> visited = null;
        if(useQueue) visited = MazeSearch.queueSearch(maze);
        if(useStack) visited = MazeSearch.stackSearch(maze);
        if(useOpt) visited = MazeSearch.optSearch(maze);
        
        double endTime = System.currentTimeMillis();
        
        if(MazeOutput.noSolution(maze)) {
            System.out.println("The Wolverine Store is closed.");
        } else {
            if(outCoordinate) {
                MazeOutput.printCoordinates(visited, maze);
            } else {
                MazeOutput.printTextMap(maze);
            }
        }
        
        if(useTime) {
            double seconds = (endTime - startTime) / 1000.0;
            System.out.println("Total Runtime: " + seconds + " seconds");
        }
		
		
	}
	
}
