package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class StationClass {
	
	//================= Attributes =================
	static ArrayList<String> stationsName = new ArrayList<>();
	static ArrayList<StationClass> StationObj = new ArrayList<>();
	private String stNameObj, stFracObj, stTypeObj;
	private static double fuelCostPerUnit = 5;
	private static Random rand = new Random();
	//private int ID;
	
	
	
	//================= loadStations =================// Read station names in the file and put them in the array
	 public static void loadStations() {
	        try (BufferedReader br = new BufferedReader(new FileReader("Stations.txt"))) {

	            String line;

	            while ((line = br.readLine()) != null) {
	                stationsName.add(line);
	            }

	        } catch (IOException e) {
	            System.out.println("Error reading file.");
	        }
	      }// loadStations
	
	 
	//================= StationClass constructor =================// Make a new station
	 StationClass(String name, String fraction, String type, int ajdi){
		 stNameObj = name; 
		 stFracObj = fraction;
		 stTypeObj = type;
		// ID = ajdi;
	 }// StationClass constructor
	 
	
	 
	//================= sg =================// Generate attributes of a new station
	 public static void sg(char[][] arr, int i, int j) {
		 int stID = (i*100)+j;
		 String name;
		
		 for(int i2 = 0; i2 < SpaceMap.maximum; i2++) {
			 StationObj.add(null);
		 }
		 
	        if (StationObj.get(stID) == null) {
	        	
		 	String[] fraction2 = {"Alterra Corporation", "European Coalition", "Mongolian Independent States", "DeepRockGalactic"};
		 	String[] type2 = {"Civil outpost", "Military outpost", "Scientific outpost", "Mining outpost", "Shipyard"};
		 
	    	Random rand = new Random();
	    	int r = rand.nextInt(stationsName.size());
	    	int r2 = rand.nextInt(10)+1;
	    	int r1 = rand.nextInt(fraction2.length), r21 = rand.nextInt(type2.length);
	        if(r2 >= 4) {
	        	 name =  stationsName.get(r);
	        } else {
	        	int r3 = rand.nextInt(10)+1;
	        	 name = stationsName.get(r) + "-" + r3;
	        }
	        
		 if(arr[i][j] == SpaceMap.Station) {
			  
			 r1 = rand.nextInt(fraction2.length);
					
				     
			  }// if
		 
		 StationClass stationObj = new StationClass(name, fraction2[r1], type2[r21], stID);
		 StationObj.set(stID,stationObj);
		 stActivity(stID);
	        } else {
	        stActivity(stID); 	
	        }
		 
	 }// sg
	 

	 
	//================= stActivity =================// Shows station activities 
	 	private static void stActivity(int num) {
	 		
	 		Object[] options = {
	 			    "Refuel",
	 				"Trade",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 									   "===============================================================\n"
	   									 + "                            				Welcome to " +  StationObj.get(num).stNameObj + "!\n"
	   									 + "===============================================================",
	   									StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);

	 		switch(result) {
	 		case 0: refuel(); break;
	 		case 1: trade(StationObj.get(num).stFracObj, StationObj.get(num).stTypeObj); break;
	 		case 2: break;
	 		case 3: break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4);
	    }// stActivity
	 
	 	
	 	
	 	
	 	//========== refuel ==========// Refuels the spaceship
	 	private static void refuel() {
	 		
	 	    double cost = (SpaceMap.fCap - SpaceMap.fuel) * fuelCostPerUnit;

	 	   if (SpaceMap.fuel >= SpaceMap.fCap) {
	 	        JOptionPane.showMessageDialog(null, "There's no need to refuel the spaceship!");
	 	    }// if
	 	    
	 	    if (SpaceMap.credits >= cost) {
	 	    	
	 	        SpaceMap.credits -= cost;
	 	        SpaceMap.fuel = SpaceMap.fCap;
	 	        JOptionPane.showMessageDialog(null, "Ship refueled for " + cost + " credits!");
	 	        
	 	    } else {
	 	    	
	 	        JOptionPane.showMessageDialog(null,"Not enough credits!");
	 	    }// if...else

	 	}// refuel
	 	
	 	
	 	
	 	//========== trade ==========// Defines the type of station
	 	public static void trade(String faction, String type) {
	 		
	 	    switch(faction) {

	 	        case "Alterra Corporation":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("AlterraMart™"); break;
	 	                case "Military outpost": tradeMil("Aegis Defense™"); break;
	 	                case "Scientific outpost": tradeSci("NextPharma™"); break;
	 	                case "Mining outpost": tradeMin("TerraCore™"); break;
	 	                case "Shipyard": tradeSPD("Alterra Engineering™"); break;
	 	            }
	 	            break;

	 	        case "European Coalition":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("FairFax Industries"); break;
	 	                case "Military outpost": tradeMil("EuroShield"); break;
	 	                case "Scientific outpost": tradeSci("EuroGenetics"); break;
	 	                case "Mining outpost": tradeMin("Continental Minerals"); break;
	 	                case "Shipyard": tradeSPD("EuroTech Shipworks"); break;
	 	            }
	 	            break;

	 	        case "Mongolian Independent States":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Nomad Market"); break;
	 	                case "Military outpost": tradeMil("Khan Industries"); break;
	 	                case "Scientific outpost": tradeSci("SteppeLabs"); break;
	 	                case "Mining outpost": tradeMin("Altan Mining Co."); break;
	 	                case "Shipyard": tradeSPD("Iron Nomad Shipworks"); break;
	 	            }
	 	            break;

	 	        case "DeepRockGalactic":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Miner's Rest"); break;
	 	                case "Military outpost": tradeMil("RockSolid Defense"); break;
	 	                case "Scientific outpost": tradeSci("DeepScan Technologies"); break;
	 	                case "Mining outpost": tradeMin("DeepCore Industries"); break;
	 	                case "Shipyard": tradeSPD("HeavyDrill Shipworks"); break;
	 	            }
	 	            break;

	 	    }// switch "faction"
	 	}// trade
	 	
	 	//========== trade related methods ==========
	 	private static void tradeCiv(String name) {
	 		int r1 = rand.nextInt(10);
	 		int r2 = rand.nextInt(10);
	 		int r3 = rand.nextInt(10);
	 		JOptionPane.showInputDialog(null, "==================================\n"
					   +"||  	             				 STATION MARKET                       ||\n"
					   +"==================================\n"
					   +"||  		 " +  name.toUpperCase() + "        ||\n"
					   +"==================================\n"
					   +"||  AVAILABLE TRANSACTIONS      ||\n"
					   +"||                              ||\n"
					   +"||  [1] BUY                                                         ||\n"
					   +"||  [2] SELL                                              			 ||\n"
					   +"||  [3] LEAVE                                             			 ||\n"
					   +"||                              ||\n"
					   +"==================================\n"
					   +"||  STATUS: MARKET ONLINE             ||\r\n"
					   +"||  TRANSACTION: STATION-TRD-" + r1 +r2 + r3 + "     ||\r\n"
					   +"==================================\n");
	 	}// tradeCiv
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeMil(String name) {
	 		int r1 = rand.nextInt(10);
	 		int r2 = rand.nextInt(10);
	 		int r3 = rand.nextInt(10);
	 		JOptionPane.showInputDialog(null, "==================================\n"
					   +"||  	             				 STATION MARKET                       ||\n"
					   +"==================================\n"
					   +"||  		 " +  name.toUpperCase() + "        ||\n"
					   +"==================================\n"
					   +"||  AVAILABLE TRANSACTIONS      ||\n"
					   +"||                              ||\n"
					   +"||  [1] BUY                                                         ||\n"
					   +"||  [2] SELL                                              			 ||\n"
					   +"||  [3] LEAVE                                             			 ||\n"
					   +"||                              ||\n"
					   +"==================================\n"
					   +"||  STATUS: MARKET ONLINE             ||\r\n"
					   +"||  TRANSACTION: STATION-TRD-" + r1 +r2 + r3 + "     ||\r\n"
					   +"==================================\n");
	 	}// tradeMil
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeSci(String name) {
	 		int r1 = rand.nextInt(10);
	 		int r2 = rand.nextInt(10);
	 		int r3 = rand.nextInt(10);
	 		JOptionPane.showInputDialog(null, "==================================\n"
					   +"||  	             				 STATION MARKET                       ||\n"
					   +"==================================\n"
					   +"||  		 " +  name.toUpperCase() + "        ||\n"
					   +"==================================\n"
					   +"||  AVAILABLE TRANSACTIONS      ||\n"
					   +"||                              ||\n"
					   +"||  [1] BUY                                                         ||\n"
					   +"||  [2] SELL                                              			 ||\n"
					   +"||  [3] LEAVE                                             			 ||\n"
					   +"||                              ||\n"
					   +"==================================\n"
					   +"||  STATUS: MARKET ONLINE             ||\r\n"
					   +"||  TRANSACTION: STATION-TRD-" + r1 +r2 + r3 + "     ||\r\n"
					   +"==================================\n");
	 	}// tradeSci
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeMin(String name) {
	 		int r1 = rand.nextInt(10);
	 		int r2 = rand.nextInt(10);
	 		int r3 = rand.nextInt(10);
	 		JOptionPane.showInputDialog(null, "==================================\n"
					   +"||  	             				 STATION MARKET                       ||\n"
					   +"==================================\n"
					   +"||  		 " +  name.toUpperCase() + "        ||\n"
					   +"==================================\n"
					   +"||  AVAILABLE TRANSACTIONS      ||\n"
					   +"||                              ||\n"
					   +"||  [1] BUY                                                         ||\n"
					   +"||  [2] SELL                                              			 ||\n"
					   +"||  [3] LEAVE                                             			 ||\n"
					   +"||                              ||\n"
					   +"==================================\n"
					   +"||  STATUS: MARKET ONLINE             ||\r\n"
					   +"||  TRANSACTION: STATION-TRD-" + r1 +r2 + r3 + "     ||\r\n"
					   +"==================================\n");
	 	}// tradeMin
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeSPD(String name) {
	 		int r1 = rand.nextInt(10);
	 		int r2 = rand.nextInt(10);
	 		int r3 = rand.nextInt(10);
	 		JOptionPane.showInputDialog(null, "==================================\n"
					   +"||  	             				 STATION MARKET                       ||\n"
					   +"==================================\n"
					   +"||  		 " +  name.toUpperCase() + "        ||\n"
					   +"==================================\n"
					   +"||  AVAILABLE TRANSACTIONS      ||\n"
					   +"||                              ||\n"
					   +"||  [1] BUY                                                         ||\n"
					   +"||  [2] SELL                                              			 ||\n"
					   +"||  [3] LEAVE                                             			 ||\n"
					   +"||                              ||\n"
					   +"==================================\n"
					   +"||  STATUS: MARKET ONLINE             ||\r\n"
					   +"||  TRANSACTION: STATION-TRD-" + r1 +r2 + r3 + "     ||\r\n"
					   +"==================================\n");
	 	}// tradeSPD
	 	 	
}// class
