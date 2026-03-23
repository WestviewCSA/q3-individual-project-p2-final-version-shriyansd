import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeReader {

    public static String[][][] getText(String passedFile) throws IncorrectMapFormatException, IllegalMapCharacterException, IncompleteMapException{
        File fileObj = new File(passedFile);
        try {
            Scanner scan = new Scanner(fileObj);
            
            int rows;
            int cols;
            int maps;

            try{
            	rows = Integer.parseInt(scan.next());
            	cols = Integer.parseInt(scan.next());
                maps = Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
            	throw new IncorrectMapFormatException("First line must be three positive non-zero integers");
            }
            
            
            if(rows <= 0 || cols <= 0 || maps <= 0) {
            	throw new IncorrectMapFormatException("First line must be three positive non-zero integers");
            }
            
            String[][][] maze = new String[maps][rows][cols];
            int currentRow = 0;
            int currentLevel = 0;
            while(scan.hasNext()) {
                String line = scan.next();
                
                if(!line.matches("[.@W$|]+")) {
                    throw new IllegalMapCharacterException("Illegal character found in map");
                }
                
                if(line.length() > cols) {
                    throw new IncompleteMapException("Line is too long");
                }
                
                for(int col = 0; col < line.length(); col++) {
                    maze[currentLevel][currentRow][col] = String.valueOf(line.charAt(col));
                }
                currentRow++;
                if(currentRow == rows) {
                    currentRow = 0;
                    currentLevel++;
                }
            }
            
            if(currentLevel != maps) {
                throw new IncompleteMapException("Map is incomplete - not enough rows");
            }
            
            return maze;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[][][] getCords(String passedFile) throws IncorrectMapFormatException, IllegalMapCharacterException, IncompleteMapException {
        File fileObj = new File(passedFile);
        try {
            Scanner scan = new Scanner(fileObj);
            int rows;
            int cols;
            int maps;
            
            try {
            	rows = Integer.parseInt(scan.next());
                cols = Integer.parseInt(scan.next());
                maps = Integer.parseInt(scan.next());
            }catch (NumberFormatException e) {
            	throw new IncorrectMapFormatException("First line must be three positive non-zero integers");
            }
            
            if(rows <= 0 || cols <= 0 || maps <= 0) {
                throw new IncorrectMapFormatException("First line must be three positive non-zero integers");
            }
            
            String[][][] cordBased = new String[maps][rows][cols];
            
            while(scan.hasNext()) {
                String character = scan.next();
                int rowC = Integer.parseInt(scan.next());
                int colC = Integer.parseInt(scan.next());
                int level = Integer.parseInt(scan.next());
                
                if(!character.matches("[.@W$|]")) {
                    throw new IllegalMapCharacterException("Illegal character found: " + character);
                }
                
                if(rowC >= rows || colC >= cols || level >= maps || rowC < 0 || colC < 0 || level < 0) {
                	throw new IncompleteMapException("Coordinates don't fit inside the maze");
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