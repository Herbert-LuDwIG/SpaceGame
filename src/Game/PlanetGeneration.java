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



public class PlanetGeneration {

	//------------------------------------------------------------------------------
	static ArrayList<String>  planets = new ArrayList<>();
	static ArrayList<PlanetGeneration>  planetObj = new ArrayList<>();
	public static int pNum = -1;
	private String pNameObj, pTypeObj, pRotObj, pWeatherObj;
	private int pSizeObj, pMoonObj, ID, pX, pY;
	private String pRingObj;
	
	//================= PlanetGeneration constructor =================
	PlanetGeneration(String name, String type, int size, String rings, String rotation, int moons, String weather, int ajdi, int x, int y){
		pNameObj = name;
		pTypeObj = type;
		pSizeObj = size;
		pRingObj = rings;
		pRotObj = rotation;
		pMoonObj = moons;
		pWeatherObj = weather;
		ID = ajdi;
		pX = x;
		pY = y;
	}// PlanetGeneration constructor
	
	
	//================= init =================
	static void init() {
	    if (planetObj.isEmpty()) {
	        for (int i = 0; i < SpaceMap.maximum; i++) {
	            planetObj.add(null);
	        }
	    }
	}// init
	
	
	
	//================= loadPlanets =================
    public static void loadPlanets() {
        try (BufferedReader br = new BufferedReader(new FileReader("pName.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                planets.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }// loadPlanets

    
    
    
    
    
  //================= pg =================
    public static void pg(char[][] arr, int i, int j) {
    	
    	int pID = (i*100)+j, sizePlanet, moonsP;
    	String weatherP;
		 
    	if (planetObj.isEmpty()) {
	        for (int i2 = 0; i2 < SpaceMap.maximum; i2++) {
	            planetObj.add(null);
	        }
	    }
    	
    	
	        if (planetObj.get(pID) == null) {
        	
    	
        Random rand = new Random();
        
        int r = rand.nextInt(planets.size());
        
        //JOptionPane.showMessageDialog(null, planets.get(r));
        
        String[] pType2 = {"Terrestrial planet", "Gas giant", "Ice giant", "Gas dwarf", "Dwarf planet"};
		String[] pRings2 = {"No rings", "Has rings"};
		String[] pRotation2 = {"Fast rotation", "Moderate rotation", "Slow rotation"};
		String[] pWeather2 = {"Extremely cold", "Strong winds", "Storm", "Winds", "No winds and storm", "No storm", "Temperate temperature", "Extremely hot"};
		  
	
		if(arr[i][j] == SpaceMap.planet) {
					
					 
						  int r1 = rand.nextInt(pType2.length);
						 
					      if(pType2[r1].equals("Terrestrial planet")) {
					    	  int r2 = rand.nextInt(8500)+4500;
					    	  sizePlanet = r2;
					    	  
					      } else if(pType2[r1].equals("Gas dwarf")) {
					    	  int r2 = rand.nextInt(14000)+10800;
					    	  sizePlanet = r2;
					    	 
					      } else if(pType2[r1].equals("Dwarf planet")){
					    	  int r2 = rand.nextInt(1450)+950;
					    	  sizePlanet = r2;
					    	 
					      }	else {
					    	  int r2 = rand.nextInt(93500)+49500;
					    	  sizePlanet = r2;
					    	  
					      }
					    
					    //planet rings  
					      int r3 = rand.nextInt(pRings2.length);
					     // pRings.add(pRings2[r3]);
					     
					      
					    //planet rotation  
					      int r4 = rand.nextInt(pRotation2.length);
					     // pRotation.add(pRotation2[r4]);
					        
					      
					    //planet moons  
					      int r5 = rand.nextInt(4);
					      moonsP = r5;
					      
					      
					    //planet weather
					      if(pType2[r1].equals("Ice giant")) {
					      int r6 = rand.nextInt(pWeather2.length-3)+1;
					      weatherP = pWeather2[r6] + ", " + pWeather2[0];
					      
					      } else {	
					      int r6 = rand.nextInt(pWeather2.length);
					      weatherP = pWeather2[r6];
						  
					      }
					   
					      SpaceMap.pDisc++; 
					      
					      PlanetGeneration planet = new PlanetGeneration(planets.get(r), pType2[r1], sizePlanet, pRings2[r3], pRotation2[r4], moonsP, weatherP, pID, i, j);
					      planetObj.set(pID, planet);
					      showInfo(pID);
				  } else if (arr[i][j] != SpaceMap.planet) {
					    return;
				  }// if
				} else {
			         JOptionPane.showMessageDialog(null, "The planet have been already scanned!");
		        }// else
				

   
    }//pg
  //------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
  //================= showInfo =================
    public static void showInfo(int num) {
    	JOptionPane.showMessageDialog(null, "Name: " + planetObj.get(num).pNameObj + 
    										"\nType: " + planetObj.get(num).pTypeObj +
    										"\nSize: " + planetObj.get(num).pSizeObj + 
    										"\nRings: " + planetObj.get(num).pRingObj +
    										"\nRotation: " + planetObj.get(num).pRotObj + 
    										"\nMoons: " + planetObj.get(num).pMoonObj + 
    										"\nWeather: " + planetObj.get(num).pWeatherObj, "Planet scan", JOptionPane.PLAIN_MESSAGE);
    
    }// showInfo
    
    
    
    
    
    
    
    
  //================= select =================
    public static void select(int pnum) {

        ArrayList<String> SMTH = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();

        for (int i = 0; i < planetObj.size(); i++) {

            if (planetObj.get(i) != null) {

                SMTH.add(planetObj.get(i).pNameObj
                    + " i" + planetObj.get(i).pX
                    + " j" + planetObj.get(i).pY);

                IDs.add(i);
            }
        }

        if (SMTH.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No scanned planets!");
            return;
        }

        String selected = (String) JOptionPane.showInputDialog(
            null,
            "Which planet will you select?",
            "Planet selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            SMTH.toArray(),
            SMTH.get(0)
        );

        if (selected == null) {
            return;
        }

        int pnum2 = SMTH.indexOf(selected);

        if (pnum2 >= 0) {
            pnum2 = IDs.get(pnum2);
            showInfo(pnum2);
        }
    }// select
    
    
    }// class