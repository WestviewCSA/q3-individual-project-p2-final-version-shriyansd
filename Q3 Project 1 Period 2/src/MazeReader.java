import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeReader {

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

    public static String[][][] getCords(String passedFile) {
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
                    return new String[0][0][0];
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
}