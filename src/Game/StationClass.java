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
	//static int stNum;
	static boolean isCapital = false;
	static ArrayList<String> stations = new ArrayList<>();
	static ArrayList<String> stNameArr = new ArrayList<>();
	static ArrayList<String> fractionArr = new ArrayList<>();
	static ArrayList<String> typeArr = new ArrayList<>();
	static ArrayList<StationClass> StationObj = new ArrayList<>();
	private String stNameObj, stFracObj, stTypeObj;
	private int ID;
	
	
	
	
	 private static void loadStations() {
	        try (BufferedReader br = new BufferedReader(new FileReader("Stations.txt"))) {

	            String line;

	            while ((line = br.readLine()) != null) {
	                stations.add(line);
	            }

	        } catch (IOException e) {
	            System.out.println("Error reading file.");
	        }
	        
	        
	        
	        
	    }// loadStations
	
	 
	 StationClass(String name, String fraction, String type, int ajdi){
		 stNameObj = name; 
		 stFracObj = fraction;
		 stTypeObj = type;
		 ID = ajdi;
	 }// StationClass constructor
	 
	 
	 
	 
	 
	 
	 
	 public static void sg(char[][] arr, int i, int j) {
		 int stID = (i*100)+j;
		// File f = new File("Station i" + i + "j" + j + " st.txt");
		 for(int i2 = 0; i2 < SpaceMap.maximum; i2++) {
			 stNameArr.add(null);
			 fractionArr.add(null);
			 typeArr.add(null);
			 StationObj.add(null);
		 }
	        if (StationObj.get(stID) == null) {
	      
		 //int x = (SpaceMap.iPlusj % 100) + SpaceMap.iPlusj / 100;
		 //JOptionPane.showMessageDialog(null, "iPlusj = " + SpaceMap.iPlusj + "\nx = " + x);
		 
		
		
		 	String[] fraction2 = {"Alterra Corp.", "European Coalition", "Mongolian Independent States", "DeepRockGalactic"};
		 	String[] type2 = {"Civil outpost", "Military outpost", "Scientific outpost", "Mining outpost", "Shipyard", "Capital"};
		 
		 	loadStations();
	    	Random rand = new Random();
	    	int r = rand.nextInt(stations.size());
	    	int r2 = rand.nextInt(10)+1;
	        if(r2 >= 4) {
	        	 stNameArr.set(stID, stations.get(r));
	        } else {
	        	int r3 = rand.nextInt(10)+1;
	        	 stNameArr.set(stID, stations.get(r) + "-" + r3);
	        }
	        
	       
	        //JOptionPane.showMessageDialog(null, stName.get(stNum));
		 if(arr[i][j] == SpaceMap.Station) {
				
					
					//station name
					 
					  
					//station fraction   
					  int r1 = rand.nextInt(fraction2.length);
					  fractionArr.set(stID, fraction2[r1]);
				     
				      
				    //station fraction 
				      if(!isCapital) {
				      int r21 = rand.nextInt(type2.length);
					  typeArr.set(stID, type2[r21]);  
					  isCapital = true;
				      } else {
				    	  int r21 = rand.nextInt(type2.length-1);
				    	  typeArr.set(stID, type2[r21]);  
				      }
				     
				      
				   
			
			  }// if
		 
		 StationClass stationObj = new StationClass(stNameArr.get(stID), fractionArr.get(stID), typeArr.get(stID), stID);
		 StationObj.set(stID,stationObj);
		 stActivity(i, j, stID);
	        } else {
	        stActivity(i, j, stID); 	
	        }
		 
	 }// sg
	 

	 
	 
	 	private static double stActivity(int i, int j, int num) {
	 		
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
