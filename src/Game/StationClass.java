package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class StationClass {
	static boolean isCapital = false;
	static ArrayList<String> stationsName = new ArrayList<>();
	static ArrayList<StationClass> StationObj = new ArrayList<>();
	private String stNameObj, stFracObj, stTypeObj;
	private int ID;
	
	
	
	//================= loadStations =================
	 private static void loadStations() {
	        try (BufferedReader br = new BufferedReader(new FileReader("Stations.txt"))) {

	            String line;

	            while ((line = br.readLine()) != null) {
	                stationsName.add(line);
	            }

	        } catch (IOException e) {
	            System.out.println("Error reading file.");
	        }
	      }// loadStations
	
	 
	//================= StationClass constructor =================
	 StationClass(String name, String fraction, String type, int ajdi){
		 stNameObj = name; 
		 stFracObj = fraction;
		 stTypeObj = type;
		 ID = ajdi;
	 }// StationClass constructor
	 
	
	 
	//================= sg =================
	 public static void sg(char[][] arr, int i, int j) {
		 int stID = (i*100)+j;
		 String name;
		
		 for(int i2 = 0; i2 < SpaceMap.maximum; i2++) {
			 StationObj.add(null);
		 }
		 
	        if (StationObj.get(stID) == null) {
	        	
		 	String[] fraction2 = {"Alterra Corp.", "European Coalition", "Mongolian Independent States", "DeepRockGalactic"};
		 	String[] type2 = {"Civil outpost", "Military outpost", "Scientific outpost", "Mining outpost", "Shipyard", "Capital"};
		 
		 	loadStations();
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
					
				     if(!isCapital) {
					  isCapital = true;
				      } else {
				    	   r21 = rand.nextInt(type2.length-1);  
				      }// if
			  }// if
		 
		 StationClass stationObj = new StationClass(name, fraction2[r1], type2[r21], stID);
		 StationObj.set(stID,stationObj);
		 stActivity(stID);
	        } else {
	        stActivity(stID); 	
	        }
		 
	 }// sg
	 

	 
	//================= stActivity =================
	 	private static double stActivity(int num) {
	 		
	 		Object[] options = {
	 			    "Trade",
	 			    "Missions",
	 			    "Spaceship",
	 			    "Leave"
	 			};
	 		int result = JOptionPane.showOptionDialog(null,
	 									   "========================================\n"
	   									 + "                      Welcome to " +  StationObj.get(num).stNameObj + "!\n"
	   									 + "========================================",
	   									StationObj.get(num).stFracObj + "'s " + StationObj.get(num).stTypeObj,
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);

	 		switch(result) {
	 		case 0: break;
	 		case 1: break;
	 		case 2: return SpaceMap.fuel;
	 		}
			return SpaceMap.fuel;

	 			
	    
	    }// stActivity
	 
	 	
	
}// class
