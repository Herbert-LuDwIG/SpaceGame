package Game;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class SpaceMap {
	public static int iPlusj;
	public static int AlterraRep, ECRep, MISRep;
	public static char Rocket = '^';
	public static int rX, rY, MapX = 15, MapY = 15;
	public static char[][] WorldMap;
	public static char planet = 'O', star = '.', Bstar = '*', Station = '+';
	public static double fuel = 1250;
	public static int fCap = 1250;
	public static int credits;
	public static double version = 0.3;
	public static int pDisc = 0;
	public static int TileDist = 150000;
	public static boolean hasEngine = false;
	public static boolean hasHaul = false;
	public static DecimalFormat df1 = new DecimalFormat( "#.##" );
	public static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		Random rCredits = new Random();
		credits = rCredits.nextInt(1000) + 500;
		
		fileDelete(".");
			WorldMap = MapGenerating();	
			PlanetGeneration.pg(WorldMap, rX, rY);
			ShowInfo(fuel, fCap, credits, version);
			fuel = rCTRL(fuel);
			
	}// main
	
	public static char[][] MapGenerating() {
		int n = 0;
		int fMapX = MapX;
		
		String[] size = {"Micro (" + fMapX + "x" + fMapX + ")", "Small (" + fMapX*2 + "x" + fMapX*2 + ")", "Medium (" + fMapX*3 + "x" + fMapX*3 + ")", "Big (" + fMapX*4 + "x" + fMapX*4 + ")", "Large (" + fMapX*7 + "x" + fMapX*7 + ")"};  
	    String choice = (String) JOptionPane.showInputDialog(null, "Chose world's size","World Size",JOptionPane.QUESTION_MESSAGE,null, size, size[2]);
	    
	    if(choice == null) {
	    	JOptionPane.showMessageDialog(null, "Bye!");
            System.exit(0);
	    }
 	
	    switch(choice) {
	       case "Micro (15x15)":   n = 1; break;
	       case "Small (30x30)":   n = 2; break;
	       case "Medium (45x45)":  n = 3; break;
	       case "Big (60x60)": 	   n = 4; break;
	       case "Large (105x105)": n = 7; break;
	     }
	   
	Random rand = new Random();
	MapX *= n;
	MapY *= n;
    WorldMap = new char[MapX][MapY];
  
    iPlusj = MapX + MapY;
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
			            }// if
			        }// else
			    }// else
			}// else
			   
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
		case 4: character = Station; break;
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
	            if (file.isFile() && file.getName().startsWith("Planet") && file.getName().endsWith(".txt") || file.getName().endsWith(" st.txt")) {
	                file.delete();
	            }
	        }
	}//fileDelete
	
	public static double rCTRL(double fuel) {
		char wasd;
		
		do {
			System.out.print("WASD = movement\nx - stop/menu");
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
		}// switch

		if (moved && WorldMap[rX][rY] == planet) {
		    pAction();
		}// if
		
		if (moved && WorldMap[rX][rY] == Station) {
		    stAction();
		}// if
		
		mapPrint(WorldMap);
		
		}while(wasd != 'x' && fuel > 0); // do{}while
	
		 
		//ShowInfo(fuel, fCap, credits, version);
		return fuel;
	}//rCTRL
	

public static void ShowInfo(double fuel, int fCap,
                            int credits, double version) {

    String[] methodList2 = {
        "W/A/S/D - Move",
        "Fast travel to nearest planets",
        "Scanned planets",
        "Inventory (in development)",
        "Missions (in development)",
        "Quit"
    };

    while (true) {

        String method2 = (String) JOptionPane.showInputDialog(
            null,
            "========================================\n"
            + "                       SPACE EXPLORER v" + version + "\n"
            + "========================================\n"
            + "Fuel: " + df1.format(fuel) + "/" + fCap + "             Credits: " + credits +"\n"
            + "Planets discovered: " + pDisc + "\n"
            + "----------------------------------------------------------------------",
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

            case "Fast travel to nearest planets":
                fuel = FastTravel(WorldMap, rX, rY, fuel);
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
		System.out.println("\n".repeat(WorldMap.length));
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

	public static void stAction() {
	    Object[] options = {"Visit station", "Continue exploration"};

	    int selectedStation = JOptionPane.showOptionDialog(
	        null,
	        "What would you like to do?",
	        "You've arrived to the station!",
	        JOptionPane.DEFAULT_OPTION,
	        JOptionPane.QUESTION_MESSAGE,
	        null,
	        options,
	        options[0]
	    );

	    if (selectedStation == 0) {
	        JOptionPane.showMessageDialog(null, "Welcome, Captain!");
	        StationClass.sg(WorldMap, rX, rY);
	    } else if (selectedStation == 1) {
	        JOptionPane.showMessageDialog(null, "Continuing exploration...");
	    }
	}//stAction
	
	
	public static double FastTravel(char[][] arr, int pI, int pJ, double fuel2) {
		double distance = 0;
		
		ArrayList<String> SMTH = new ArrayList<>();
		ArrayList<Integer> X = new ArrayList<>();
		ArrayList<Integer> Y = new ArrayList<>();
		ArrayList<Double> Dist = new ArrayList<>();
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == planet) {
					distance = Math.sqrt(Math.pow(i - pI, 2) + Math.pow(j - pJ, 2));
					Dist.add(distance);
					 X.add(i);
					 Y.add(j);
				}// if
			}// j
		}// i
		
		Collections.sort(Dist);
		for(int i = 0; i < 3; i++) {
				SMTH.add("~ " + df1.format(Dist.get(i)) + " tiles (" + df1.format(Dist.get(i) * TileDist) + " km)");
			}// for
			
		Collections.sort(Dist);
		
		String selected = (String) JOptionPane.showInputDialog(
	            null,
	            "Which way will you select?",
	            "Planet selection",
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            SMTH.toArray(),
	            SMTH.get(0)
	    );
		
		if(selected == null) 
			return fuel2;
		
		
		 int index = SMTH.indexOf(selected);
		 
		 rX = X.get(index);
		 rY = Y.get(index);
		 //WorldMap[X.get(index)][Y.get(index)] = Rocket;
		 mapPrint(WorldMap);
		 df1.format(fuel2 -= distance * 2.5);
		 ShowInfo(fuel2, fCap, credits, version);
		 
		
		return fuel2;
	}// FastTravel
	
	
}//class
