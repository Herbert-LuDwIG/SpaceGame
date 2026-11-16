package Game;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class StationClass {
	
	//================= Attributes =================
	static ArrayList<String> stationsName = new ArrayList<>();
	static ArrayList<StationClass> StationObj = new ArrayList<>();
	private String stNameObj, stFracObj, stTypeObj;
	private static double fuelCostPerUnit = 5;
	private static Random rand = new Random();
	private static int rSt1, rSt2, rSt3;
	//private int ID;
	
	
	
	//================= loadStations =================// Read station names in the file and put them in the array
	public static void loadStations() {
	 
	    try (InputStream is = StationClass.class.getResourceAsStream("/resources_names/pName.txt")) {

	        if (is == null) {
	           
	            return;
	        }// if

	        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

	            String line;

	            while ((line = br.readLine()) != null) {
	                if (!line.isBlank()) {
	                	stationsName.add(line.trim());
	                }// if
	            }// while
	        }// try

	       

	    } catch (IOException e) {
	        e.printStackTrace();
	    }// catch
	}// loadStations
	
	 
	//================= StationClass constructor =================// New station generation
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
	 		rSt1 = rand.nextInt(10);
	 		rSt2 = rand.nextInt(10);
	 		rSt3 = rand.nextInt(10);
	 		
	 		trade(StationObj.get(num).stFracObj, StationObj.get(num).stTypeObj, num);
	 		
	    }// stActivity
	 
	 	
	 	
	 	
	 	//========== refuel ==========// Refuels the spaceship
	 	private static void refuel() {
	 		
	 	    double cost = (SpaceMap.fCap - SpaceMap.fuel) * fuelCostPerUnit;

	 	    if (SpaceMap.credits >= cost) {
	 	    	
	 	        SpaceMap.credits -= cost;
	 	        SpaceMap.fuel = SpaceMap.fCap;
	 	        JOptionPane.showMessageDialog(null, "Ship refueled for " + cost + " credits!");
	 	        
	 	    } else {
	 	    	
	 	        JOptionPane.showMessageDialog(null,"Not enough credits!");
	 	    }// if...else
	 	    
	 	    
	 	    
	 	    
	 	}// refuel
	 	
	 	
	 	
	 	//========== trade ==========// Defines the type of station
	 	public static void trade(String faction, String type, int num) {
	 		
	 	    switch(faction) {

	 	        case "Alterra Corporation":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Alterra Corporation", "AlterraMart™", num); break;
	 	                case "Military outpost": tradeMil("Alterra Corporation", "Alterra Arms™", num); break;
	 	                case "Scientific outpost": tradeSci("Alterra Corporation", "Alterra Science™", num); break;
	 	                case "Mining outpost": tradeMin("Alterra Corporation", "AlterraCore™", num); break;
	 	                case "Shipyard": tradeSPD("Alterra Corporation", "Alterra Engineering™", num); break;
	 	            }
	 	            break;

	 	        case "European Coalition":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("European Coalition", "Euronics", num); break;
	 	                case "Military outpost": tradeMil("European Coalition", "FairFax Industries", num); break;
	 	                case "Scientific outpost": tradeSci("European Coalition", "NextPharma", num); break;
	 	                case "Mining outpost": tradeMin("European Coalition", "Continental Minerals", num); break;
	 	                case "Shipyard": tradeSPD("European Coalition", "EuroTech Shipworks", num); break;
	 	            }
	 	            break;

	 	        case "Mongolian Independent States":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Mongolian Independent States", "Nomad Market", num); break;
	 	                case "Military outpost": tradeMil("Mongolian Independent States", "Khan Industries", num); break;
	 	                case "Scientific outpost": tradeSci("Mongolian Independent States", "SteppeLabs", num); break;
	 	                case "Mining outpost": tradeMin("Mongolian Independent States", "Altan Mining Co.", num); break;
	 	                case "Shipyard": tradeSPD("Mongolian Independent States", "Iron Nomad Shipworks", num); break;
	 	            }
	 	            break;

	 	        case "DeepRockGalactic":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("DeepRockGalactic", "Miner's Rest", num); break;
	 	                case "Military outpost": tradeMil("DeepRockGalactic", "RockSolid Defense", num); break;
	 	                case "Scientific outpost": tradeSci("DeepRockGalactic", "DeepScan Technologies", num); break;
	 	                case "Mining outpost": tradeMin("DeepRockGalactic", "DeepCore Industries", num); break;
	 	                case "Shipyard": tradeSPD("DeepRockGalactic", "HeavyDrill Shipworks", num); break;
	 	            }
	 	            break;

	 	    }// switch "faction"
	 	}// trade
	 	
	 	//========== trade related methods ==========
	 	private static void tradeCiv(String frac,String name, int num) {
	 		
	 		Object[] options = {
	 			    "Refuel",
	 				"Trade",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		String WelcomeMessage =  "========================================================================================\n"
						 	+ "                                    																		  Welcome to " +  StationObj.get(num).stNameObj + "!\n"
						 	+ "========================================================================================";
	 		
	 		JTextArea WelcomeArea = new JTextArea(WelcomeMessage);
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	   									frac + "'s civil station",
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(); break;
	 		case 1: 
	 			
	 			String message = "==================================\n"
					+"||  	   STATION MARKET       ||\n"
					+"==================================\n"
					+"||  	 " + name.toUpperCase() + "      ||\n"
					+"==================================\n"
					+"||  AVAILABLE TRANSACTIONS      ||\n"
					+"||                              ||\n"
					+"||  [1] BUY                     ||\n"
					+"||  [2] SELL                    ||\n"
					+"||  [3] LEAVE                   ||\n"
					+"||                              ||\n"
					+"==================================\n"
					+"||  STATUS: MARKET ONLINE       ||\n"
					+"||  TRANSACTION: STATION-TRD-"+rSt1+rSt2+rSt3+"||\r\n"
					+"==================================\n";
	 		
	 		JTextArea area = new JTextArea(message);
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: break;
	 		case 3: break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeCiv
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeMil(String frac,String name, int num) {
	 		
	 		Object[] options = {
	 			    "Refuel",
	 				"Trade",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		String WelcomeMessage =  "========================================================================================\n"
						 	+ "                                    																		  Welcome to " +  StationObj.get(num).stNameObj + "!\n"
						 	+ "========================================================================================";
	 		
	 		JTextArea WelcomeArea = new JTextArea(WelcomeMessage);
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	   									frac + "'s civil station",
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(); break;
	 		case 1: 
	 			
	 			String message = "==================================\n"
					+"||  	   STATION MARKET       ||\n"
					+"==================================\n"
					+"||  	 " + name.toUpperCase() + "      ||\n"
					+"==================================\n"
					+"||  AVAILABLE TRANSACTIONS      ||\n"
					+"||                              ||\n"
					+"||  [1] BUY                     ||\n"
					+"||  [2] SELL                    ||\n"
					+"||  [3] LEAVE                   ||\n"
					+"||                              ||\n"
					+"==================================\n"
					+"||  STATUS: MARKET ONLINE       ||\n"
					+"||  TRANSACTION: STATION-TRD-"+rSt1+rSt2+rSt3+"||\r\n"
					+"==================================\n";
	 		
	 		JTextArea area = new JTextArea(message);
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: break;
	 		case 3: break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeMil
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeSci(String frac,String name, int num) {
	 		
	 		Object[] options = {
	 			    "Refuel",
	 				"Trade",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		String WelcomeMessage =  "========================================================================================\n"
						 	+ "                                    																		  Welcome to " +  StationObj.get(num).stNameObj + "!\n"
						 	+ "========================================================================================";
	 		
	 		JTextArea WelcomeArea = new JTextArea(WelcomeMessage);
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	   									frac + "'s civil station",
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(); break;
	 		case 1: 
	 			
	 			String message = "==================================\n"
					+"||  	   STATION MARKET       ||\n"
					+"==================================\n"
					+"||  	 " + name.toUpperCase() + "      ||\n"
					+"==================================\n"
					+"||  AVAILABLE TRANSACTIONS      ||\n"
					+"||                              ||\n"
					+"||  [1] BUY                     ||\n"
					+"||  [2] SELL                    ||\n"
					+"||  [3] LEAVE                   ||\n"
					+"||                              ||\n"
					+"==================================\n"
					+"||  STATUS: MARKET ONLINE       ||\n"
					+"||  TRANSACTION: STATION-TRD-"+rSt1+rSt2+rSt3+"||\r\n"
					+"==================================\n";
	 		
	 		JTextArea area = new JTextArea(message);
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: break;
	 		case 3: break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeSci
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeMin(String frac,String name, int num) {
	 		
	 		Object[] options = {
	 			    "Refuel",
	 				"Trade",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		String WelcomeMessage =  "========================================================================================\n"
						 	+ "                                    																		  Welcome to " +  StationObj.get(num).stNameObj + "!\n"
						 	+ "========================================================================================";
	 		
	 		JTextArea WelcomeArea = new JTextArea(WelcomeMessage);
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	   									frac + "'s civil station",
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(); break;
	 		case 1:// trade 
	 			String message = "==================================\n"
					+"||  	   STATION MARKET       ||\n"
					+"==================================\n"
					+"||  	 " + name.toUpperCase() + "      ||\n"
					+"==================================\n"
					+"||  AVAILABLE TRANSACTIONS      ||\n"
					+"||                              ||\n"
					+"||  [1] BUY                     ||\n"
					+"||  [2] SELL                    ||\n"
					+"||  [3] LEAVE                   ||\n"
					+"||                              ||\n"
					+"==================================\n"
					+"||  STATUS: MARKET ONLINE       ||\n"
					+"||  TRANSACTION: STATION-TRD-"+rSt1+rSt2+rSt3+"||\r\n"
					+"==================================\n";
	 		
	 		JTextArea area = new JTextArea(message);
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		area.setEditable(false);
	 		area.setOpaque(false);
	 		JOptionPane.showInputDialog(null, area);
	 		break;
	 		case 2: break;
	 		case 3: break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeMin
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeSPD(String frac,String name, int num) {
	 		
	 		Object[] options = {
	 			    "Refuel",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		String WelcomeMessage =  "========================================================================================\n"
						 	+ "                                    																		  Welcome to " +  StationObj.get(num).stNameObj + "!\n"
						 	+ "========================================================================================";
	 		
	 		JTextArea WelcomeArea = new JTextArea(WelcomeMessage);
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	   									frac + "'s civil station",
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(); break;
	 		case 1: 
	 			
	 			String message = "==================================\n"
					+"||  	   STATION MARKET       ||\n"
					+"==================================\n"
					+"||  	 " + name.toUpperCase() + "      ||\n"
					+"==================================\n"
					+"||  AVAILABLE TRANSACTIONS      ||\n"
					+"||                              ||\n"
					+"||  [1] BUY                     ||\n"
					+"||  [2] SELL                    ||\n"
					+"||  [3] LEAVE                   ||\n"
					+"||                              ||\n"
					+"==================================\n"
					+"||  STATUS: MARKET ONLINE       ||\n"
					+"||  TRANSACTION: STATION-TRD-"+rSt1+rSt2+rSt3+"||\r\n"
					+"==================================\n";
	 		
	 		JTextArea area = new JTextArea(message);
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: break;
	 		case 3: break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeSPD
	 	 	
}// class
