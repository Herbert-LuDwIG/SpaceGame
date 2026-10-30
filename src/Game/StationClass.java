package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class StationClass {
	static int stNum;
	static boolean isCapital = false;
	static ArrayList<String> stations = new ArrayList<>();
	static ArrayList<String> stName = new ArrayList<>();
	static ArrayList<String> fraction = new ArrayList<>();
	static ArrayList<String> type = new ArrayList<>();
	
	
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
	
	 public static void sg(char[][] arr, int i, int j) {

		 for(int i2 = 0; i2 < SpaceMap.iPlusj; i2++) {
			 stName.add(null);
			 fraction.add(null);
			 type.add(null);
		 }
		 
		 
		 
		 	String[] fraction2 = {"Alterra Corp.", "European Coalition", "Mongolian Independent States", "DeepRockGalactic"};
		 	String[] type2 = {"Civil outpost", "Military outpost", "Scientific outpost", "Mining outpost", "Shipyard", "Capital"};
		 
		 	loadStations();
	    	Random rand = new Random();
	    	int r = rand.nextInt(stations.size());
	    	int r2 = rand.nextInt(10)+1;
	        if(r2 >= 4) {
	        	 stName.set(stNum, stations.get(r));
	        } else {
	        	int r3 = rand.nextInt(10)+1;
	        	 stName.set(stNum, stations.get(r) + "-" + r3);
	        }
	        
	       
	        //JOptionPane.showMessageDialog(null, stName.get(stNum));
		 if(arr[i][j] == SpaceMap.Station) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(stName.get(stNum) + " i" + i + "j" + j + " st.txt"))) {
					
					//station name
					  bw.write("Name: " + stName.get(stNum));
					  bw.newLine(); 
					  
					//station fraction   
					  int r1 = rand.nextInt(fraction2.length);
					  fraction.set(stNum, fraction2[r1]);
				      bw.write("Fraction: " + fraction.get(stNum));
				      bw.newLine();
				      
				    //station fraction 
				      if(!isCapital) {
				      int r21 = rand.nextInt(type2.length);
					  type.set(stNum, type2[r21]);  
					  isCapital = true;
				      } else {
				    	  int r21 = rand.nextInt(type2.length-1);
				    	  type.set(stNum, type2[r21]);  
				      }
				      bw.write("Type: " + type.get(stNum));
				      bw.newLine();
				      
				    } catch (IOException e) {
				      System.out.println("Error writing file.");
				    }
			
			  }// if
		 
		 stActivity(i, j, stNum);
		 
	 }// sg
	 
	 	private static double stActivity(int i, int j, int num) {
	 		Object[] options = {
	 			    "Trade",
	 			    "Missions",
	 			    "Leave"
	 			};
	 		int result = JOptionPane.showOptionDialog(null, "========================================\n"
	   									 + "                      Welcome to " +  stName.get(num) + "!\n"
	   									 + "========================================",
	   									 fraction.get(num) + "'s " + type.get(num),
	   									 JOptionPane.YES_NO_CANCEL_OPTION,
	   									 JOptionPane.PLAIN_MESSAGE, 
	   									 null,
	   									 options,
	   									 options[0]);

	 		switch(result) {
	 		case 0:
	 		case 1:
	 		case 2: return SpaceMap.fuel;
	 		}
			return SpaceMap.fuel;

	 			
	    
	    }// stActivity
	 
	 
	 
}// class
