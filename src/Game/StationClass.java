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
	 
	    try (InputStream is = StationClass.class.getResourceAsStream("/resources_names/Stations.txt")) {

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
		
		 
		 
	        if (StationObj.get(stID) == null) {
	        	
		 	String[] fraction2 = {"Alterra Corporation", "European Coalition", "Mongolian Independent States", "DeepRockGalactic"};
		 	String[] type2 = {"Civil outpost", "Military outpost", "Scientific outpost", "Mining outpost", "Shipyard"};
		 
	    	Random rand = new Random();
	    	int r = rand.nextInt(stationsName.size());
	    	int r2 = rand.nextInt(10)+1;
	    	int r1 = rand.nextInt(fraction2.length);
	    	int r21 = rand.nextInt(type2.length);
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
	        }// if...else
	 }// sg
	 

	 
	//================= stActivity =================// Shows station activities 
	 	private static void stActivity(int num) {
	 		//JOptionPane.showMessageDialog(null, StationObj.get(num).stTypeObj);
	 		rSt1 = rand.nextInt(10);
	 		rSt2 = rand.nextInt(10);
	 		rSt3 = rand.nextInt(10);
	 		
	 		trade(StationObj.get(num).stFracObj, StationObj.get(num).stTypeObj, num);
	 		
	    }// stActivity
	 
	 	
	 	
	 	
	 	// ========== refuel ========== // Refuels the spaceship
	 	private static void refuel(int num) {
	 		
	 	    double cost = (SpaceMap.fCap - SpaceMap.fuel) * fuelCostPerUnit;
	 	    System.out.println(cost);
	 	    System.out.println(cost);
	 	    
	 	    switch(StationObj.get(num).stFracObj) {
			case "Alterra Corporation": cost = cost - (cost * (SpaceMap.AlterraRep/100)); break;
			case "Mongolian Independent States": cost = cost - (cost * (SpaceMap.MISRep/100)); break;
			case "European Coalition": cost = cost - (cost * (SpaceMap.ECRep/100)); break;
			case "DeepRockGalactic": cost = cost - (cost * (SpaceMap.DRGRep/100)); break;
			}// switch
	 	  
	 	    if (SpaceMap.credits >= cost) {
	 	    	
	 	        SpaceMap.credits -= cost;
	 	        SpaceMap.fuel = SpaceMap.fCap;
	 	        JOptionPane.showMessageDialog(null, "Ship refueled for " + cost + " credits!");
	 	        
	 	    } else {
	 	    	
	 	        JOptionPane.showMessageDialog(null,"Not enough credits!");
	 	    }// if...else
	 	 }// refuel
	 	
	 	
	 	
	 	
	 	
	 	// ========== trade ========== // Defines the type of station
	 	public static void trade(String faction, String type, int num) {
	 		
	 	    switch(faction) {

	 	        case "Alterra Corporation":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("AlterraMart™", num); break;
	 	                case "Military outpost": tradeMil("Alterra Arms™", num); break;
	 	                case "Scientific outpost": tradeSci("Alterra Science™", num); break;
	 	                case "Mining outpost": tradeMin("AlterraCore™", num); break;
	 	                case "Shipyard": tradeSPD("Alterra Engineering™", num); break;
	 	            }// switch "type"
	 	            break;

	 	        case "European Coalition":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Euronics", num); break;
	 	                case "Military outpost": tradeMil("FairFax Industries", num); break;
	 	                case "Scientific outpost": tradeSci("NextPharma", num); break;
	 	                case "Mining outpost": tradeMin("Continental Minerals", num); break;
	 	                case "Shipyard": tradeSPD("EuroTech Shipworks", num); break;
	 	            }// switch "type"
	 	            break;

	 	        case "Mongolian Independent States":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Nomad Market", num); break;
	 	                case "Military outpost": tradeMil("Khan Industries", num); break;
	 	                case "Scientific outpost": tradeSci("SteppeLabs", num); break;
	 	                case "Mining outpost": tradeMin("Altan Mining Co.", num); break;
	 	                case "Shipyard": tradeSPD("Iron Nomad Shipworks", num); break;
	 	            }// switch "type"
	 	            break;

	 	        case "DeepRockGalactic":
	 	            switch(type) {
	 	                case "Civil outpost": tradeCiv("Miner's Rest", num); break;
	 	                case "Military outpost": tradeMil("RockSolid Defense", num); break;
	 	                case "Scientific outpost": tradeSci("DeepScan Technologies", num); break;
	 	                case "Mining outpost": tradeMin("DeepCore Industries", num); break;
	 	                case "Shipyard": tradeSPD("HeavyDrill Shipworks", num); break;
	 	            }// switch "type"
	 	            break;

	 	    }// switch "faction"
	 	}// trade
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	// ========== trade related methods ========== //
	 	private static void tradeCiv(String name, int num) {
	 		
	 		Object[] optionsCiv = {
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
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				 					 WelcomeMessage,
	 				 					 StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 optionsCiv,
	   									 optionsCiv[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(num); break;
	 		case 3: IntputCheck(name, "ENGINE Mk.1", "STORAGE Mk.1", "HAUL Mk.1", num); break;
	 		case 2: break;
	 		case 1: 
	 			
	 			String Upgrades = "==================================\n"
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
	 							 +"||  TRANSACTION: STATION-TRS-"+rSt1+rSt2+rSt3+"||\r\n"
	 							 +"==================================\n";
		 		
		 		JTextArea Upgradesarea = new JTextArea(Upgrades);
		 		Upgradesarea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		 		Upgradesarea.setEditable(false);
		 		Upgradesarea.setOpaque(false);
	 			break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeCiv
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeMil(String name, int num) {
	 		
	 		Object[] optionsMil = {
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
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	 				StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									optionsMil,
	   									optionsMil[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(num); break;
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
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: break;
	 		case 3: IntputCheck(name, "ENGINE Mk.2", "FUEL TANK Mk.1", "HAUL Mk.2", num); break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeMil
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeSci(String name, int num) {
	 		
	 		Object[] optionsSci = {
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
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	 				StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									optionsSci,
	   									optionsSci[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(num); break;
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
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: break;
	 		case 3: IntputCheck(name, "ENGINE Mk.2", "SCANNER Mk.2", "AUTOPILOT", num); break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeSci
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeMin(String name, int num) {
	 		
	 		Object[] optionsMin = {
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
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				WelcomeMessage,
	 				StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									optionsMin,
	   									optionsMin[0]);
	 		if(result == -1) 
	 			result = 4; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(num); break;
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
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		area.setEditable(false);
	 		area.setOpaque(false);
	 		JOptionPane.showInputDialog(null, area);
	 		break;
	 		case 2: break;
	 		case 3: IntputCheck(name, "LASER DRILL", "SCANNER Mk.1", "STORAGE Mk.2", num); break;
	 		case 4: break;
	 		}// switch "result" 			
	 	  }while(result != 4); // do{}while
	 	}// tradeMin
	 	//------------------------------------------------------------------------------------------------------------------------------
	 	private static void tradeSPD(String name, int num) {
	 		
	 		Object[] optionsSPD = {
	 			    "Refuel",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result;
	 		String WelcomeMessage =  "========================================================================================\n"
						 		   + "                           Welcome to " +  StationObj.get(num).stNameObj + "!\n"
						 		   + "========================================================================================";
	 		
	 		JTextArea WelcomeArea = new JTextArea(WelcomeMessage);
	 		WelcomeArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		WelcomeArea.setEditable(false);
	 		WelcomeArea.setOpaque(false);
	 		
	 		do {
	 		 result = JOptionPane.showOptionDialog(null,
	 				 					 WelcomeMessage,
	 				 					 StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 optionsSPD,
	   									 optionsSPD[0]);
	 		if(result == -1) 
	 			result = 3; // cancer button
	 		
	 		switch(result) {
	 		case 0: refuel(num); break;
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
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		JOptionPane.showInputDialog(null, area);
	 			
	 			break;
	 		case 2: IntputCheck(name, "ENGINE Mk.2", "STORAGE Mk.2", "HAUL Mk.1", num); break;
	 		case 3: break;
	 		}// switch "result" 			
	 	  }while(result != 3); // do{}while
	 	}// tradeSPD
	 	
	 	
	 	
	 	
	 	// ========== IntputCheck ========== //
	 	private static void IntputCheck(String name, String opt1, String opt2, String opt3, int num) {
	 		String choseDig, message = "==================================\n"
	 				+"||  	        UPGRADES          ||\n"
					+"==================================\n"
					+"  	  " + name.toUpperCase() + "\n"
					+"==================================\n"
					+"||  AVAILABLE UPGRADES          ||\n"
					+"||                              ||\n"
					+"||  [1] " + opt1 + "            ||\n"
					+"||  [2] " + opt2 + "            ||\n"
					+"||  [3] " + opt3 + "            ||\n"
					+"||                              ||\n"
					+"==================================\n"
					+"||  STATUS: MARKET ONLINE       ||\n"
					+"||  TRANSACTION: STATION-TRD-"+rSt1+rSt2+rSt3+"||\r\n"
					+"==================================\n";
	 		
	 		JTextArea area = new JTextArea(message);
	 		area.setFont(new Font("Monospaced", Font.PLAIN, 20));
	 		area.setEditable(false);
	 		area.setOpaque(false);

	 		choseDig = JOptionPane.showInputDialog(null, area);
	 		
	 		
	 		if (choseDig == null) {
	 		    return;
	 		}
	 		choseDig.trim();
 			       if(choseDig.equals("1") || choseDig.trim().equalsIgnoreCase(opt1)) {
 				PurchaseCheck(choseDig, name, opt1, num);
 			} else if(choseDig.equals("2") || choseDig.trim().equalsIgnoreCase(opt2)) {
 				PurchaseCheck(choseDig, name, opt2, num);
 			} else if(choseDig.equals("3") || choseDig.trim().equalsIgnoreCase(opt3)) {
 				PurchaseCheck(choseDig, name, opt3, num);
 			} else {// if...else if...else if
 				JOptionPane.showMessageDialog(null, "Incorrect input!");
 			}
 			
	 	}// IntputCheck
	 	

	 	

	 // ========== PurchaseCheck ========== //
	 private static void PurchaseCheck(String str, String name, String upgrade, int num) {

	     str = str.trim().toUpperCase();

	     switch (str) {

	         case "ENGINE MK.1":

	             if (SpaceShip.engineCoef == 0) {
	                 if (SpaceMap.credits >= 750) {

	                     double cost = 0;

	                     SpaceShip.engineCoef = 1;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 750 - (750 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 750 - (750 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 750 - (750 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 750 - (750 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "STORAGE MK.1":

	             if (SpaceShip.storageCoef == 50) {
	                 if (SpaceMap.credits >= 275) {

	                     double cost = 0;

	                     SpaceShip.storageCoef += 50;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 275 - (275 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 275 - (275 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 275 - (275 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 275 - (275 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "AUTOPILOT":

	             if (!SpaceShip.Autopilot) {
	                 if (SpaceMap.credits >= 500) {

	                     double cost = 0;

	                     SpaceShip.Autopilot = true;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 500 - (500 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 500 - (500 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 500 - (500 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 500 - (500 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "SCANNER MK.1":

	             if (SpaceShip.scanLVL == 0) {
	                 if (SpaceMap.credits >= 500) {

	                     double cost = 0;

	                     SpaceShip.scanLVL = 1;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 500 - (500 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 500 - (500 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 500 - (500 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 500 - (500 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "LASER DRILL":

	             if (!SpaceShip.hasDrill) {
	                 if (SpaceMap.credits >= 750) {

	                     double cost = 0;

	                     SpaceShip.hasDrill = true;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 750 - (750 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 750 - (750 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 750 - (750 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 750 - (750 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "HAUL MK.1":

	             if (SpaceShip.haulDefCoef == 0) {
	                 if (SpaceMap.credits >= 825) {

	                     double cost = 0;

	                     SpaceShip.haulDefCoef += 15;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 825 - (825 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 825 - (825 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 825 - (825 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 825 - (825 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "ENGINE MK.2":

	             if (SpaceShip.engineCoef == 1) {
	                 if (SpaceMap.credits >= 1250) {

	                     double cost = 0;

	                     SpaceShip.engineCoef = 2;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 1250 - (1250 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 1250 - (1250 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 1250 - (1250 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 1250 - (1250 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else if (SpaceShip.engineCoef < 1) {
	                 JOptionPane.showMessageDialog(null, "ENGINE Mk.1 is needed!");

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "STORAGE MK.2":

	             if (SpaceShip.storageCoef == 100) {
	                 if (SpaceMap.credits >= 575) {

	                     double cost = 0;

	                     SpaceShip.storageCoef += 100;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 575 - (575 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 575 - (575 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 575 - (575 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 575 - (575 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else if (SpaceShip.storageCoef < 100) {
	                 JOptionPane.showMessageDialog(null, "STORAGE Mk.1 is needed!");

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "SCANNER MK.2":

	             if (SpaceShip.scanLVL == 1) {
	                 if (SpaceMap.credits >= 1000) {

	                     double cost = 0;

	                     SpaceShip.scanLVL += 1;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 1000 - (1000 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 1000 - (1000 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 1000 - (1000 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 1000 - (1000 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else if (SpaceShip.scanLVL < 1) {
	                 JOptionPane.showMessageDialog(null, "SCANNER Mk.1 is needed!");

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "HAUL MK.2":

	             if (SpaceShip.haulDefCoef == 15) {
	                 if (SpaceMap.credits >= 1500) {

	                     double cost = 0;

	                     SpaceShip.haulDefCoef += 15;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 1500 - (1500 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 1500 - (1500 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 1500 - (1500 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 1500 - (1500 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else if (SpaceShip.haulDefCoef < 15) {
	                 JOptionPane.showMessageDialog(null, "HAUL Mk.1 is needed!");

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	         case "FUEL TANK MK.1":

	             if (SpaceMap.fCap == 250) {
	                 if (SpaceMap.credits >= 950) {

	                     double cost = 0;

	                     SpaceMap.fCap += 250;

	                     switch (StationObj.get(num).stFracObj) {
	                         case "Alterra Corporation": cost = 950 - (950 * (SpaceMap.AlterraRep / 100.0)); break;
	                         case "Mongolian Independent States": cost = 950 - (950 * (SpaceMap.MISRep / 100.0)); break;
	                         case "European Coalition": cost = 950 - (950 * (SpaceMap.ECRep / 100.0)); break;
	                         case "DeepRockGalactic": cost = 950 - (950 * (SpaceMap.DRGRep / 100.0)); break;
	                     }

	                     SpaceMap.credits -= cost;

	                     JOptionPane.showMessageDialog(null,
	                         "Thank you for buying " + str + " for " + cost + " credits!",
	                         "Thank you for choosing " + name + "!",
	                         JOptionPane.PLAIN_MESSAGE);

	                 } else {
	                     JOptionPane.showMessageDialog(null, "Not enough credits!");
	                 }

	             } else {
	                 JOptionPane.showMessageDialog(null, upgrade + " already have been installed!");
	             }

	             break;

	         //-------------------------------------------------------------------------------------

	     }

	 } // PurchaseCheck
	 	 	
}// class