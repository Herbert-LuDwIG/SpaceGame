package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;



public class PlanetGeneration {

	// ================= Attributes ================= //
	static ArrayList<String> planetsName = new ArrayList<>();
	static ArrayList<String> MoonName = new ArrayList<>();
	static ArrayList<PlanetGeneration>  planetObj = new ArrayList<>();
	private String pNameObj, pTypeObj, pRotObj, pWeatherObj;
	private int pMoonQuanObj, pX, pY;
	private String pRingObj, pSizeObj;
	public static ArrayList<String> moons = new ArrayList<>();
	
	
	
	
	
	// ================= PlanetGeneration constructor ================= // New planet generation without moon/-s
	PlanetGeneration(String name, String type, String size, String rings, String rotation, int moons, String weather, int ajdi, int x, int y){
		pNameObj = name;
		pTypeObj = type;
		pSizeObj = size;
		pRingObj = rings;
		pRotObj = rotation;
		pMoonQuanObj = moons;
		pWeatherObj = weather;
		pX = x;
		pY = y;
	}// PlanetGeneration constructor
	
	
	
	
		
	// ================= loadPlanets ================= //
	public static void loadPlanets() {

	    try (InputStream is = PlanetGeneration.class.getResourceAsStream("/resources_names/pName.txt")) {

	        if (is == null) {
	           
	            return;
	        }// if

	        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

	            String line;

	            while ((line = br.readLine()) != null) {
	                if (!line.isBlank()) {
	                	planetsName.add(line.trim());
	                }// if
	            }// while
	        }// try

	        

	    } catch (IOException e) {
	        e.printStackTrace();
	    }// catch
	}// loadPlanets

    
	
	
	
	// ================= loadMoons ================= // 
	public static void loadMoons() {

	    try (InputStream is = PlanetGeneration.class.getResourceAsStream("/resources_names/pMoonName.txt")) {

	        if (is == null) {
	           
	            return;
	        }// if

	        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

	            String line;

	            while ((line = br.readLine()) != null) {
	                if (!line.isBlank()) {
	                	MoonName.add(line.trim());
	                }// if
	            }// while
	        }// try

	    } catch (IOException e) {
	        e.printStackTrace();
	    }// catch
	}// loadMoons
	
	
	
	
	//========== emptyAnArray ==========// Make an array to fill it with planets 
    public static void emptyAnArray() {
    	
    	if (planetObj.isEmpty()) {
	        for (int i2 = 0; i2 < SpaceMap.maximum; i2++) {
	            planetObj.add(null);
	        }// i
	    }// if
    	
    	
    	if (moons.isEmpty()) {
	        for (int i2 = 0; i2 < SpaceMap.maximum; i2++) {
	            moons.add(null);
	        }// i
	    }// if
    	
    	
    }// emptyAnArray
    
    
    
  //================= pg ================= //
    public static void pg(char[][] arr, int i, int j) {
    	
    	int pID = (i*100)+j, moonsP;
    	String weatherP, sizePlanet;    	
    	
    	
    	if (planetsName.isEmpty()) {
    	    JOptionPane.showMessageDialog(
    	        null,
    	        "No planets loaded! Check pName.txt"
    	    );
    	    return;
    	}
    	
	    if (planetObj.get(pID) == null) {
        	
    	 //JOptionPane.showMessageDialog(null, planets.get(r));
        
        String[] pType2 = {"Terrestrial planet", "Gas giant", "Ice giant", "Gas dwarf", "Dwarf planet"};
		String[] pRings2 = {"No rings", "Has rings"};
		String[] pRotation2 = {"Fast rotation", "Moderate rotation", "Slow rotation"};
		String[] pWeather2 = {"Extremely cold", "Strong winds", "Storm", "Winds", "No winds and storm", "No storm", "Temperate temperature", "Extremely hot"};
		  
	
		if(arr[i][j] == SpaceMap.planet && SpaceMap.scanLVL == 1) {
				Random rand = new Random();
        
        		int r = rand.nextInt(planetsName.size());
					 
						  int r1 = rand.nextInt(pType2.length);
						  int r2;
					      if(pType2[r1].equals("Terrestrial planet")) {
					    	   r2 = rand.nextInt(8500)+4500;
					    	  sizePlanet = r2 + " km";
					    	  
					      } else if(pType2[r1].equals("Gas dwarf")) {
					    	   r2 = rand.nextInt(14000)+10800;
					    	  sizePlanet = r2 + " km";
					    	 
					      } else if(pType2[r1].equals("Dwarf planet")){
					    	   r2 = rand.nextInt(1450)+950;
					    	  sizePlanet = r2 + " km";
					    	 
					      }	else {
					    	   r2 = rand.nextInt(93500)+49500;
					    	  sizePlanet = r2 + " km";
					    	  
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
					      
					      PlanetGeneration planet = new PlanetGeneration(planetsName.get(r), pType2[r1], sizePlanet, pRings2[r3], pRotation2[r4], moonsP, weatherP, pID, i, j);
					      planetObj.set(pID, planet);
					      showInfo(pID);
					// scanLVL = 0      
				  } else if (arr[i][j] == SpaceMap.planet && SpaceMap.scanLVL == 0) {
					  JOptionPane.showMessageDialog(null, "Planet scanner is needed!");
					  return;
					 // scanLVL = 2   
				  } else if(arr[i][j] == SpaceMap.planet && SpaceMap.scanLVL == 2) {
					 // JOptionPane.showMessageDialog(null, "Advanced scanner is aquaried!");
					  
					  Random rand = new Random();
				        
		        		int r = rand.nextInt(planetsName.size());
							 
								  int r1 = rand.nextInt(pType2.length);
								  int r2;
							      if(pType2[r1].equals("Terrestrial planet")) {
							    	   r2 = rand.nextInt(8500)+4500;
							    	  sizePlanet = r2 + " km";
							    	  
							      } else if(pType2[r1].equals("Gas dwarf")) {
							    	   r2 = rand.nextInt(14000)+10800;
							    	  sizePlanet = r2 + " km";
							    	 
							      } else if(pType2[r1].equals("Dwarf planet")){
							    	   r2 = rand.nextInt(1450)+950;
							    	  sizePlanet = r2 + " km";
							    	 
							      }	else {
							    	   r2 = rand.nextInt(93500)+49500;
							    	  sizePlanet = r2 + " km";
							    	  
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
							      
							      //---------------------------------------------------
							      
							        String[] mColor = {"Gray", "Blue", "Red", "Yellow", "Purple", "Gray and Red gradients", "Gray and Blue gradients", "Gray and Yellow gradients", "Gray and Purple gradients", 
											  "Red and Blue gradients", "Red and Yellow gradients", "Red and Purple gradients",
											  "Yellow and Blue gradients", "Yellow and Purple gradients", 
											  "Blue and Purple gradient"};
							        String[] mShape = {"Sphere", "Elongated"};

							        String moon = "";
							        for (int i2 = 0; i2 < moonsP; i2++) {
							        	 moon += "--------------------------------\n";
							             moon += "Moon name: " + (Moons.MoonName.get(rand.nextInt(Moons.MoonName.size()))) + "\n";
							             moon += "Color of the moon: " + (mColor[rand.nextInt(mColor.length)]) + "\n";
							             moon += "Shape of the moon:" + (mShape[rand.nextInt(mShape.length)]) + "\n";
							             moon += "Moon's diameter: " + (rand.nextInt(5500) + 1) + " km\n";							               
							        }// i2   
							      moons.set(pID, moon);
							     
							      PlanetGeneration planet = new PlanetGeneration(planetsName.get(r), pType2[r1], sizePlanet, pRings2[r3], pRotation2[r4], moonsP, weatherP, pID, i, j);
							      planetObj.set(pID, planet);
							      showInfo(pID);
							      
				  }// if...else if...else if
				} else {
			         JOptionPane.showMessageDialog(null, "The planet have been already scanned!");
		        }// if..else
			}//pg

    
    
    
    
    
  //================= showInfo ================= //
    public static void showInfo(int num) {
    	
    	if(planetObj.get(num).pMoonQuanObj != 0 && moons.get(num) != null) {
    		JOptionPane.showMessageDialog(null, "Name: " + planetObj.get(num).pNameObj + 
												"\nType: " + planetObj.get(num).pTypeObj +
												"\nSize: " + planetObj.get(num).pSizeObj + 
												"\nRings: " + planetObj.get(num).pRingObj +
												"\nRotation: " + planetObj.get(num).pRotObj + 
												"\nMoons: " + planetObj.get(num).pMoonQuanObj + 
												"\nWeather: " + planetObj.get(num).pWeatherObj, "Planet scan", JOptionPane.PLAIN_MESSAGE);
    		JOptionPane.showMessageDialog(null, moons.get(num), "Planet moons", JOptionPane.PLAIN_MESSAGE);
    	} else {
    		JOptionPane.showMessageDialog(null, "Name: " + planetObj.get(num).pNameObj + 
    											"\nType: " + planetObj.get(num).pTypeObj +
    											"\nSize: " + planetObj.get(num).pSizeObj + 
    											"\nRings: " + planetObj.get(num).pRingObj +
    											"\nRotation: " + planetObj.get(num).pRotObj + 
    											"\nMoons: " + planetObj.get(num).pMoonQuanObj + 
    											"\nWeather: " + planetObj.get(num).pWeatherObj, "Planet scan", JOptionPane.PLAIN_MESSAGE);
    	}// if..else
    
    }// showInfo
    
    
    
    
    
    
    
    
  // ================= select ================= //
    public static void select() {

        ArrayList<String> SMTH = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();

        for (int i = 0; i < planetObj.size(); i++) {

            if (planetObj.get(i) != null) {

                SMTH.add(planetObj.get(i).pNameObj
                    + " i" + planetObj.get(i).pX
                    + " j" + planetObj.get(i).pY);

                IDs.add(i);
            }// if
        }// if

        if (SMTH.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No scanned planets!");
            return;
        }// if

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

        if (pnum2 >= 0) 
            pnum2 = IDs.get(pnum2);
            showInfo(pnum2);
        
    }// select
    
    
    }// class