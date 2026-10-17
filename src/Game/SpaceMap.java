package Game;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SpaceMap {
	public static char Rocket = '^';
	public static int rX, rY, MapX = 25, MapY = 25;
	public static char[][] WorldMap;
	public static char planet = 'O', star = '.', Bstar = '*', Sattelite = '+';
	
	public static void main(String[] args) {
		fileDelete(".");
		WorldMap = MapGenerating();	
		PlanetGeneration.pg(WorldMap, rX, rY);
		rCTRL();
	}
	
	
	
	public static char[][] MapGenerating() {
	
	Random rand = new Random();
    WorldMap = new char[MapX][MapY];
    rX = WorldMap.length/2;
    rY = WorldMap.length/2;
	int planetNum = 0;
	 WorldMap[rX][rY] = Rocket;
	
	for(int i = 0; i < WorldMap.length; i++) {
		System.out.print("--");
	}
	System.out.println();
	
	for(int i = 0; i < WorldMap.length; i++) {
		for(int j = 0; j < WorldMap[i].length; j++){
			
			
			if (i == rX && j == rY) {
			    WorldMap[i][j] = ' ';
			} else {
			    int r1 = rand.nextInt(75) + 1;

			    if (r1 >= 5) {
			        WorldMap[i][j] = ' ';
			    } else {
			        char obj = GenerateObject(r1);

			        if (obj == planet && planetNum >= 3) {
			            WorldMap[i][j] = ' ';
			        } else {
			            WorldMap[i][j] = obj;

			            if (obj == planet) {
			                planetNum++;
			            }
			        }
			    }
			}
			   
			    System.out.print(WorldMap[i][j] + " ");
				}
			 	System.out.print("|\n");
		}
	
	for(int i = 0; i < WorldMap.length; i++) {
		System.out.print("--");
	} 
	return WorldMap;
	}//method		
	
	private static char GenerateObject(int r) {
		char character = ' ';

		switch(r) { 
		case 1: character = star; break;
		case 2: character = Bstar; break;
		case 3: character = planet; break;		
		case 4: character = Sattelite; break;
		}
			
		return character;
	}//GenerateObject	
		
	public static void fileDelete(String directoryPath) {
		 File directory = new File(directoryPath);

	        if (!directory.isDirectory()) {
	            return;
	        }

	        File[] files = directory.listFiles();

	        if (files == null) {
	            return;
	        }

	        for (File file : files) {
	            if (file.isFile() && file.getName().startsWith("Planet") && file.getName().endsWith(".txt")) {
	                file.delete();
	            }
	        }
	}//fileDelete
	
	public static void rCTRL() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nWASD = movement");
		char wasd;
		do {
		wasd = VirknesParbaude();
		
		switch(wasd) {
		case 'w': if(rX > 0) rX-- ;break;
		case 'a': if(rY > 0) rY-- ;break;
		case 's': if(rX < MapX - 1) rX++ ;break;
		case 'd': if(rY < MapY - 1) rY++ ;break;
		case 'x': System.out.println("Rocket stopped"); break;
		default: System.out.println("Incorrect input");
		}
		
		if(WorldMap[rX][rY] == planet) {
			pAction();
		}
		
		mapPrint(WorldMap);
		}while(wasd != 'x');
	}//rCTRL
	
	public static void mapPrint(char[][] arr) {
	    for (int i = 0; i < arr.length; i++) {
	        System.out.print("--");
	    }
	    System.out.println();

	    for (int i = 0; i < arr.length; i++) {
	        for (int j = 0; j < arr[i].length; j++) {

	            if (i == rX && j == rY) {
	                System.out.print(Rocket + " ");
	            } else {
	                System.out.print(arr[i][j] + " ");
	            }
	        }

	        System.out.print("|\n");
	    }

	    for (int i = 0; i < arr.length; i++) {
	        System.out.print("--");
	    }
	    System.out.println();
	}//mapPrint
	
	public static char VirknesParbaude() {
		 Scanner scan = new Scanner(System.in);
		 char dati;
			
				dati = scan.next().charAt(0);

			    if (Character.toString(dati).matches("^[a-zA-Z0-9]*$")) {
			         
			    }else{
			         System.out.println("invalid input");
			    }
			
			return dati;
	 }//VirknesParbaude
	
	public static void pAction() {
	    Object[] options = {"Scan", "Continue exploration"};

	    int selectedPlanet = JOptionPane.showOptionDialog(
	        null,
	        "What would you like to do?",
	        "Planet discovered!",
	        JOptionPane.DEFAULT_OPTION,
	        JOptionPane.QUESTION_MESSAGE,
	        null,
	        options,
	        options[0]
	    );

	    if (selectedPlanet == 0) {
	        JOptionPane.showMessageDialog(null, "Scanning planet...");
	        PlanetGeneration.pg(WorldMap, rX, rY);
	    } else if (selectedPlanet == 1) {
	        JOptionPane.showMessageDialog(null, "Continuing exploration...");
	        rCTRL();
	    }
	}//pAction
	

	
	
}//class
