import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Reader {
	

	public static void main(String[] args) {
		
		Queue text = getText("Easy_Map_2");
		
		while(!text.isEmpty()) {
			System.out.println(text.poll());
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
	
	public static Queue<String> getText(String passedFile) {
		
		
		Queue<String> textBased = new ArrayDeque<>();
		File fileObj = new File(passedFile);
		try {
			
			
			String rows = "";		
			String columns = "";
			String maps = "";
			Scanner scan = new Scanner(fileObj);
			rows = scan.next();
			columns = scan.next();
			maps = scan.next();
			
			while(scan.hasNext()) {
				
				String temp = scan.next();
				
				if(!temp.matches("[.$W@]+")){
					System.out.println("There is an invalid character");
					Queue<String> empty = new ArrayDeque<>();
					return empty;
				}else {
					textBased.add(temp);
				}
				

			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return textBased;

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
