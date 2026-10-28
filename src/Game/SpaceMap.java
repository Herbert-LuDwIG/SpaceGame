package Game;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;


public class SpaceMap {
	public static char Rocket = '^';
	public static int rX, rY, MapX = 15, MapY = 15;
	public static char[][] WorldMap;
	public static char planet = 'O', star = '.', Bstar = '*', Sattelite = '+';
	public static double fuel = 1250;
	public static int fCap = 1250;
	public static int credits;
	public static double version = 0.2;
	public static int pDisc = 0;
	public static int TileDist = 150000;
	public static DecimalFormat df1 = new DecimalFormat( "#.##" );
	public static void main(String[] args) {
		Random rCredits = new Random();
		credits = rCredits.nextInt(1000) + 500;
		
		fileDelete(".");
			WorldMap = MapGenerating();	
			PlanetGeneration.pg(WorldMap, rX, rY);
			ShowInfo(fuel, fCap, credits, version);
			fuel = rCTRL(fuel);
			
	}
	
	
	
	public static char[][] MapGenerating() {
		int n = 0;
		int fMapX = MapX;
		
		String[] size = {"Micro (" + fMapX + "x" + fMapX + ")", "Small (" + fMapX*2 + "x" + fMapX*2 + ")", "Medium (" + fMapX*3 + "x" + fMapX*3 + ")", "Big (" + fMapX*4 + "x" + fMapX*4 + ")", "Large (" + fMapX*6 + "x" + fMapX*6 + ")"};  
	    String choice = (String) JOptionPane.showInputDialog(null, "","World Size",JOptionPane.QUESTION_MESSAGE,null, size, size[2]);
	    
	    if(choice == null) {
	    	choice = size[2];
	    }
 	
	    switch(choice) {
	       case "Micro (15x15)":   n = 1; break;
	       case "Small (30x30)":   n = 2; break;
	       case "Medium (45x45)":  n = 3; break;
	       case "Big (60x60)": 	   n = 4; break;
	       case "Large (90x90)":   n = 6; break;
	     }
	   
	Random rand = new Random();
	MapX *= n;
	MapY *= n;
    WorldMap = new char[MapX][MapY];
  
    rX = MapX/2;
    rY = MapY/2;
	int planetNum = 0;
	
	 WorldMap[rX][rY] = Rocket;
	
	 System.out.println("--".repeat(MapX));
	
	for(int i = 0; i < MapX; i++) {
		for(int j = 0; j < MapY; j++){
			
			
			
			if (i == rX && j == rY) {
			    WorldMap[i][j] = ' ';
			} else {
			    int r1 = rand.nextInt(75) + 1;

			    if (r1 >= 5) {
			        WorldMap[i][j] = ' ';
			    } else {
			        char obj = GenerateObject(r1);

			        if (obj == planet && planetNum >= ((r1 = rand.nextInt(3)+3))*n) {
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
				}// j
			 	System.out.print("|\n");
		}// i
	
	System.out.println("--".repeat(MapX));
	return WorldMap;
	}//MapGenerating	
	
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
	
	public static double rCTRL(double fuel) {
		 
		System.out.print("WASD = movement\nx - stop/menu");
		char wasd;
		do {
		wasd = VirknesParbaude();
		
		if(fuel <= 0) {
			JOptionPane.showMessageDialog(null, "Out of fuel!\nMovement is impossible");
			wasd = 'x';
		}
		
		boolean moved = false;

		switch (wasd) {
		    case 'w':
		        if (rX > 0 && fuel > 0) {
		            rX--;
		            fuel -= 2.5;
		            moved = true;
		        }
		        break;

		    case 'a':
		        if (rY > 0 && fuel > 0) {
		            rY--;
		            fuel -= 2.5;
		            moved = true;
		        }
		        break;

		    case 's':
		        if (rX < MapX - 1 && fuel > 0) {
		            rX++;
		            fuel -= 2.5;
		            moved = true;
		        }
		        break;

		    case 'd':
		        if (rY < MapY - 1 && fuel > 0) {
		            rY++;
		            fuel -= 2.5;
		            moved = true;
		        }
		        break;

		    case 'x':
		        JOptionPane.showMessageDialog(
		            null, "SpaceShip has stopped!"
		        );
		       moved = false;
		        ShowInfo(fuel, fCap, credits, version);
		        break;

		    default:
		        System.out.println("Incorrect input");
		}

		if (moved && WorldMap[rX][rY] == planet) {
		    pAction();
		}
		
		if(WorldMap[rX][rY] == planet) {
			pAction();
		}
		
		mapPrint(WorldMap);
		
		}while(wasd != 'x' && fuel > 0);
	
		 
		//ShowInfo(fuel, fCap, credits, version);
		return fuel;
	}//rCTRL
	

public static void ShowInfo(double fuel, int fCap,
                            int credits, double version) {

    String[] methodList2 = {
        "W/A/S/D - Move",
        "Fast travel",
        "Scanned planets",
        "Inventory",
        "Missions",
        "Quit"
    };

    while (true) {

        String method2 = (String) JOptionPane.showInputDialog(
            null,
            "========================================\n"
            + "                       SPACE EXPLORER v" + version + "\n"
            + "========================================\n"
            + "Fuel: " + df1.format(fuel) + "/" + fCap + "\n"
            + "Planets discovered: " + pDisc + "\n"
            + "----------------------------------------",
            "Space Explorer",
            JOptionPane.QUESTION_MESSAGE,
            null,
            methodList2,
            methodList2[0]
        );

        if (method2 == null || method2.equals("Quit")) {
            JOptionPane.showMessageDialog(null, "Bye!");
            System.exit(0);
        }

        switch (method2) {

            case "W/A/S/D - Move":
                fuel = rCTRL(fuel);
                break;

            case "Scanned planets":
                PlanetGeneration.pNum =
                    PlanetGeneration.select(PlanetGeneration.pNum);
                break;

            case "Fast travel":
                fuel = FastTravel(WorldMap, rX, rY);
                break;

            case "Inventory (in development)":
                // Inventory
                break;

            case "Missions (in development)":
                // Missions
                break;
        }
    }
}
// ShowInfo
	
	public static void mapPrint(char[][] arr) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("--".repeat(WorldMap.length));

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

	    System.out.println("--".repeat(WorldMap.length));
	    
	}//mapPrint
	
	public static char VirknesParbaude() {
		 Scanner scan = new Scanner(System.in);
		 char dati = scan.next().charAt(0);

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
	        //rCTRL();
	    }
	}//pAction

	
	public static double FastTravel(char[][] arr, int pI, int pJ) {
		double distance = 0;
		ArrayList<String> SMTH = new ArrayList<>();
		ArrayList<Integer> X = new ArrayList<>();
		ArrayList<Integer> Y = new ArrayList<>();
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == planet) {
					distance = Math.sqrt(Math.pow(i - pI, 2) + Math.pow(j - pJ, 2));
					 SMTH.add("~ " + df1.format(distance) + " tiles (" + df1.format(distance * TileDist) + " km)");
					 X.add(i);
					 Y.add(j);
				}
			}// j
		}// i
		
		String selected = (String) JOptionPane.showInputDialog(
	            null,
	            "Which way will you select?",
	            "Planet selection",
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            SMTH.toArray(),
	            SMTH.get(0)
	    );
		
		
		
		 int index = SMTH.indexOf(selected);
		 
		 rX = X.get(index);
		 rY = Y.get(index);
		 //WorldMap[X.get(index)][Y.get(index)] = Rocket;
		 mapPrint(WorldMap);
		 
		 ShowInfo(fuel, fCap, credits, version);
		 
		df1.format(fuel -= distance * 2.5);
		return fuel;
	}// FastTravel
	
	
}//class
