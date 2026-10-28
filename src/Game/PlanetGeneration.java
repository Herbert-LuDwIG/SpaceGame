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
	static ArrayList<String>  pName = new ArrayList<>();
	static ArrayList<String>  pType = new ArrayList<>();
	static ArrayList<Integer> pSize = new ArrayList<>();
	static ArrayList<String>  pRings = new ArrayList<>();
	static ArrayList<String>  pRotation = new ArrayList<>();
	static ArrayList<Integer> pMoons = new ArrayList<>();
	static ArrayList<String>  pWeather = new ArrayList<>();
	static ArrayList<String>  pCoordinates = new ArrayList<>();
	static ArrayList<Integer> pCx = new ArrayList<>();
	static ArrayList<Integer> pCy = new ArrayList<>();
	public static int pNum = -1;
	
    private static void loadPlanets() {
        try (BufferedReader br = new BufferedReader(new FileReader("pName.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                planets.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void pg(char[][] arr, int i, int j) {
    	
    	File f = new File("Planet i" + i + "j" + j + ".txt");

        if (!f.exists()) {
        	loadPlanets();
    	
        Random rand = new Random();
        
        int r = rand.nextInt(planets.size());
        //pName.add(planets.get(r));
        
        String[] pType2 = {"Terrestrial planet", "Gas giant", "Ice giant", "Gas dwarf", "Dwarf planet"};
		//String[] pSize2 = {"Small", "Medium", "Large", "Giant"};
		String[] pRings2 = {"No rings", "Has rings"};
		String[] pRotation2 = {"Fast rotation", "Moderate rotation", "Slow rotation"};
		//String[] pMoons = {"0", "1", "2", "3"};
		String[] pWeather2 = {"Extremely cold", "Strong winds", "Storm", "Winds", "No winds and storm", "No storm", "Temperate temperature", "Extremely hot"};
		  
	
		if(arr[i][j] == SpaceMap.planet) {
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("Planet i" + i + "j" + j + ".txt"))) {
						pNum++;
						
						//planet name
						  pName.add(planets.get(r));
						  bw.write("Name: " + pName.get(pNum));
						  bw.newLine(); 
						  
						//planet type  
						  int r1 = rand.nextInt(pType2.length-1);
						  pType.add(pType2[r1]);
					      bw.write("Type: " + pType.get(pNum));
					      bw.newLine();
					      
					    //planet size  
					      if(pType2[r1] == "Terrestrial planet") {
					    	  int r2 = rand.nextInt(8500)+4500;
					    	  pSize.add(r2);
					    	  bw.write("Size: " + pSize.get(pNum) + " KM");
					      } else if(pType2[r1] == "Gas dwarf") {
					    	  int r2 = rand.nextInt(14000)+10800;
					    	  pSize.add(r2);
					    	  bw.write("Size: " + pSize.get(pNum) + " KM");
					      } else if(pType2[r1] == "Dwarf planet"){
					    	  int r2 = rand.nextInt(1450)+950;
					    	  pSize.add(r2);
					    	  bw.write("Size: " + pSize.get(pNum) + " KM"); 
					      }	else {
					    	  int r2 = rand.nextInt(93500)+49500;
					    	  pSize.add(r2);
					    	  bw.write("Size: " + pSize.get(pNum) + " KM"); 
					      }
					      bw.newLine(); 
					      
					    //planet rings  
					      int r3 = rand.nextInt(pRings2.length-1);
					      pRings.add(pRings2[r3]);
					      bw.write("Rings: " + pRings.get(pNum));
					      bw.newLine(); 
					      
					    //planet rotation  
					      int r4 = rand.nextInt(pRotation2.length-1);
					      pRotation.add(pRotation2[r4]);
					      bw.write("Rotation: " + pRotation.get(pNum));
					      bw.newLine();  
					      
					    //planet moons  
					      int r5 = rand.nextInt(4);
					      pMoons.add(r5);
					      bw.write("Moons: " + pMoons.get(pNum));
					      bw.newLine(); 
					      
					    //planet weather
					      if(pType2[r1] == "Ice giant") {
					      int r6 = rand.nextInt(pWeather2.length-2)+1;
					      pWeather.add(pWeather2[r6] + ", " + pWeather2[0]);
					      bw.write("Weather: " + pWeather.get(pNum)); 
					      } else {	
					      int r6 = rand.nextInt(pWeather2.length-1);
					      pWeather.add(pWeather2[r6]);
						  bw.write("Weather: " + pWeather.get(pNum)); 
					      }
					      
					      pCx.add(i);
						  pCy.add(j);
					      SpaceMap.pDisc++; // Increases quanity of discovered planets
					      
					    } catch (IOException e) {
					      System.out.println("Error writing file.");
					    }
				
				}} else {
			         JOptionPane.showMessageDialog(null, "The planet have been already scanned!");
		        }
		
				showInfo(i, j);

   
    }//pg
  //------------------------------------------------------------------------------
    
    public static void showInfo(int i, int j) {
    	try (BufferedReader br = new BufferedReader(new FileReader("Planet i" + i + "j" + j + ".txt"))) {
    		  StringBuilder text = new StringBuilder();
    	        String line;

    	        while ((line = br.readLine()) != null) {
    	            text.append(line).append("\n");
    	        }

    	        JOptionPane.showMessageDialog(null, text.toString());

        } catch (IOException e) {
            
        }
    
    }// showInfo
    
    
    public static int select(int pnum) {

        ArrayList<String> SMTH = new ArrayList<>();

        for (int i = 0; i < pName.size(); i++) {
            SMTH.add(pName.get(i) + " i" + pCx.get(i) + "j" + pCy.get(i));
        }

        
        if (SMTH.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No scanned planets!");
            return -1;
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
            return pnum;
        }

        pnum = SMTH.indexOf(selected);

        if (pnum >= 0 && pnum < pCx.size() && pnum < pCy.size()) {
            showInfo(pCx.get(pnum), pCy.get(pnum));
        }

        
        
        return pnum;
    }// select
    
    
    }// class