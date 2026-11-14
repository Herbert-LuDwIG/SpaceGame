package Game;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class SpaceMap {
	//================= Variables =================
	public static int maximum;
	public static int AlterraRep, ECRep, MISRep, DRGRep;
	public static char Rocket = '^';
	public static int rX, rY, MapX = 15, MapY = 15;
	public static char[][] WorldMap;
	public static char planet = 'O', star = '.', Bstar = '*', Station = '+', Mission = '!';
	public static double fuel = 250;
	public static double Fe = 0, Cu = 0, U = 0,Al = 0,Ti = 0;
	public static int fCap = 250;
	public static int credits;
	public static double version = 0.3;
	public static int pDisc = 0;
	public static int TileDist = 150000;
	public static int StorageCap = 250;
	public static double EngineCoef = 0, HaulCoef = 0, StorageCoef = 0;
	public static boolean hasScanner = false;
	public static DecimalFormat df1 = new DecimalFormat( "#.##" );
	public static Scanner scan = new Scanner(System.in);
	
	
	
	//================= Main =================// Calls methods
	public static void main(String[] args) {
		Random rCredits = new Random();
		StationClass.loadStations();
		credits = rCredits.nextInt(1000) + 500;
		WorldMap = MapGenerating();
		mapPrint(WorldMap);
		PlanetGeneration.emptyAnArray();
		PlanetGeneration.loadPlanets();
		PlanetGeneration.pg(WorldMap, rX, rY);
		ShowInfo(fuel);	
	}// main
	
	
	//================= MapGenerating =================// Generates a map
	public static char[][] MapGenerating() {
		int n = 0;
		int fMapX = MapX;
		
		String[] size = {"Micro (" + fMapX + "x" + fMapX + ")", "Small (" + fMapX*2 + "x" + fMapX*2 + ")", "Medium (" + fMapX*3 + "x" + fMapX*3 + ")", "Big (" + fMapX*6 + "x" + fMapX*6 + ")"};  
	    String choice = (String) JOptionPane.showInputDialog(null, "Chose world's size","World Size",JOptionPane.QUESTION_MESSAGE,null, size, size[2]);
	    
	    if(choice == null) {
	    	JOptionPane.showMessageDialog(null, "Bye!");
            System.exit(0);
	    }//if choice == null
 	
	    switch(choice) {
	       case "Micro (15x15)":   n = 1; break;
	       case "Small (30x30)":   n = 2; break;
	       case "Medium (45x45)":  n = 3; break;
	       case "Big (90x90)": 	   n = 4; break;
	     }// switch "choice"
	   
	Random rand = new Random();
	MapX *= n;
	MapY *= n;
    WorldMap = new char[MapX][MapY];
  
    maximum = (MapX * 100) + MapY;
    
    rX = MapX/2;
    rY = MapY/2;
    
	int planetNum = 0;
	
	WorldMap[rX][rY] = Rocket;
	
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
		  }// j	 	
		}// i
	shuffle(WorldMap);
	return WorldMap;
	}//MapGenerating	
	
	
	
	
	

	//================= Shuffle =================// Shuffles the world map
	 public static void shuffle(char[][] map) {
	        Random random = new Random();

	        int rows = map.length;
	        int cols = map[0].length;

	        
	        for (int i = rows * cols - 1; i > 0; i--) {

	            int j = random.nextInt(i + 1);

	            
	            int row1 = i / cols;
	            int col1 = i % cols;

	            int row2 = j / cols;
	            int col2 = j % cols;

	           
	            char temp = map[row1][col1];
	            map[row1][col1] = map[row2][col2];
	            map[row2][col2] = temp;
	        }
	    }// shuffle
	
	
	 
	 
	 
	 
	 
	//================= GenerateObject =================// Chooses random symbols to put into the map
	private static char GenerateObject(int r) {
		char character = ' ';

		switch(r) { 
		case 1: character = star; break; 	// 25%
		case 2: character = Bstar; break; 	// 25%
		case 3: character = planet; break; 	// 25%	
		case 4: character = Station; break; // 25%
		}// switch "r"
			
		return character;
	}//GenerateObject	
		
	
	

	
	
	//================= rCTRL =================// Allows player to move
	public static double rCTRL() {
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
		            fuel -= 2.5 - EngineCoef;
		            moved = true;
		        }
		        break;

		    case 'a':
		        if (rY > 0 && fuel > 0) {
		            rY--;
		            fuel -= 2.5 - EngineCoef;
		            moved = true;
		        }
		        break;

		    case 's':
		        if (rX < MapX - 1 && fuel > 0) {
		            rX++;
		            fuel -= 2.5 - EngineCoef;
		            moved = true;
		        }
		        break;

		    case 'd':
		        if (rY < MapY - 1 && fuel > 0) {
		            rY++;
		            fuel -= 2.5 - EngineCoef;
		            moved = true;
		        }
		        break;

		    case 'x':
		    	moved = false;
		        ShowInfo(fuel);
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
		return fuel;
	}//rCTRL
	
	
	
	
	
	
	//================= ShowInfo =================// Opens main menu up
	public static void ShowInfo(double fuel) {

    String[] methodList2 = {
        "W/A/S/D - Move",
        "Scanned planets",
        "Inventory (in development)",
        "Missions (in development)",
        "Quit"
    };

    while(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(!(false == true)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))) {
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
        }// if

        switch (method2) {

            case "W/A/S/D - Move":
                fuel = rCTRL();
                break;

            case "Scanned planets":
                    PlanetGeneration.select();
                break;

            case "Inventory (in development)":
            	SpaceShip.InventoryCheck(1, 1, 1, 1, 1);
                break;

            case "Missions (in development)":
                // Missions
                break;
        }// switch "method2"
    }// while()
}// ShowInfo
	




	//================= mapPrint =================// Prints the map
	public static void mapPrint(char[][] arr) {
		System.out.println("\n".repeat(WorldMap.length));
		System.out.println("--".repeat(WorldMap.length));

	    for (int i = 0; i < arr.length; i++) {
	        for (int j = 0; j < arr[i].length; j++) {

	            if (i == rX && j == rY) {
	                System.out.print(Rocket + " ");
	            } else {
	                System.out.print(arr[i][j] + " ");
	            }// if...else
	        }// j
	        System.out.print("|\n");
	    }// i

	    System.out.println("--".repeat(WorldMap.length));	
	}//mapPrint
	
	
	
	
	
	
	//================= VirknesParbaude =================// Checks the input
	public static char VirknesParbaude() {
		System.out.println();
		 char dati = scan.next().charAt(0);

			    if (Character.toString(dati).matches("^[a-zA-Z0-9]*$")) {
			         
			    }else{
			         System.out.println("invalid input");
			    }
			
			return dati;
	 }//VirknesParbaude
	
	
	
	
	
	//================= pAction =================// Shows planet activities
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

	
	
	
	
	//================= stAction =================// Shows station activities
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
	        StationClass.sg(WorldMap, rX, rY);
	    } else if (selectedStation == 1) {
	        JOptionPane.showMessageDialog(null, "Continuing exploration...");
	    }
	}//stAction
	
	
}//class
