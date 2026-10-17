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
	static ArrayList<String> planets = new ArrayList<>();

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
    	
    	File f = new File("Planet " + "i" + i + "j" + j + ".txt");

        if (!f.exists()) {
        	loadPlanets();
    	
        Random rand = new Random();

        int r = rand.nextInt(planets.size());

        String[] pType = {"Terrestrial planet", "Gas giant","Gas dwarf", "Ice giant", "Dwarf planet"};
		String[] pSize = {"Small", "Medium", "Large", "Giant"};
		String[] pRings = {"No rings", "Has rings"};
		String[] pRotation = {"Fast rotation", "Moderate rotation", "Slow rotation"};
		String[] pMoons = {"0", "1", "2", "3"};
		String[] pWeather = {"Strong winds", "Storm", "Winds", "No winds and storm", "No storm", "Extremely cold", "Extremely hot"};
		  
	
				if(arr[i][j] == SpaceMap.planet) {
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("Planet i" + i + "j" + j + ".txt"))) {
						  r = rand.nextInt(planets.size());
						  bw.write("Name: " + planets.get(r));
						  bw.newLine(); 
						  r = rand.nextInt(pType.length-1);
					      bw.write("Type: " + pType[r]);
					      bw.newLine();  
					      r = rand.nextInt(pSize.length-1);
					      bw.write("Size: " + pSize[r]);
					      bw.newLine();  
					      r = rand.nextInt(pRings.length-1);
					      bw.write("Rings: " + pRings[r]);
					      bw.newLine();  
					      r = rand.nextInt(pRotation.length-1);
					      bw.write("Rotation: " + pRotation[r]);
					      bw.newLine();  
					      r = rand.nextInt(pMoons.length-1);
					      bw.write("Moons: " + pMoons[r]);
					      bw.newLine();  
					      r = rand.nextInt(pWeather.length-1);
					      bw.write("Weather: " + pWeather[r]);
					    } catch (IOException e) {
					      System.out.println("Error writing file.");
					    }
				
				}
        } else {
         JOptionPane.showMessageDialog(null, "The planet have been already scanned!");
        }
        	
        	
        	
        	
        	
    	
		
        
    }//pg
  //------------------------------------------------------------------------------
    
	
    
    
   
    
}
