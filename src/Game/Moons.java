package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Moons {
	static ArrayList<String>  MoonName = new ArrayList<>();
	private String MoonNameObj, MoonColorObj, MoonShapeObj;
	private int diameter;
	
	// ================= Moons constructor ================= // New planet generation
	Moons(String name, String color, String shape, int diameter){
		MoonNameObj = name;
		MoonColorObj = color;
		MoonShapeObj = shape;
		this.diameter = diameter;
	}// Moons constructor
	
	

	
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
	
}// class 
