package Game;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SpaceShip {
	// ========== Attributes ========== //
	public static boolean hasDrill = false, Autopilot = false;
	public static int engineCoef = 0, scanLVL = 0, storageCoef = 50, haulDefCoef = 0;
	public static double Fe = 1, Cu = 0, U = 1 , Al = 1, Ti = 0;
	
	// ========== InventoryCheck ========== // Shows player's inventory
	public static void InventoryCheck() {
		JOptionPane.showMessageDialog(null, "Iron ore: " + Fe + "/" + SpaceMap.StorageCap + " kg\n" + 
											"Copper ore: " + Cu + "/" + SpaceMap.StorageCap + " kg\n" + 
											"Uranuim ore: " + U + "/" + SpaceMap.StorageCap + " kg\n"  +
											"Aluminium ore: " + Al + "/" + SpaceMap.StorageCap + " kg\n" + 
											"Titanium ore: " + Ti + "/" + SpaceMap.StorageCap + " kg\n"  , "Storage", JOptionPane.INFORMATION_MESSAGE);
	}// InventoryCheck
	
	
	
	
	// ========== UpgradesCheck ========== // Shows player's upgrades
	public static void UpgradesCheck() {
	String strEng = "", strScn = "", strStg = "", stg = "", strHal = "", eng = "";
	switch(engineCoef) {
	case 0: strEng = "N/A"; eng = " 0"; break;
	case 1: strEng = "Mk1"; eng = "40"; break;
	case 2: strEng = "Mk2"; eng = "80"; break;
	}// switch "engineCoef"
	
	switch(scanLVL) {
	case 0: strScn = "N/A"; break;
	case 1: strScn = "Mk1"; break;
	case 2: strScn = "Mk2"; break;
	}// switch "scanLVL"
	
	switch(storageCoef) {
	case 50: strStg = "N/A"; stg = " 50"; break;
	case 100: strStg = "Mk1"; stg = "100"; break;
	case 200: strStg = "Mk2"; stg = "200"; break;
	}// switch "storageCoef"
	
	switch(haulDefCoef) {
	case 0: strHal = "N/A"; break;
	case 15: strHal = "Mk1"; break;
	case 30: strHal = "Mk2"; break;
	}// switch "haulDefCoef"
		
		
	 String str = "==================================\n"
				 +"||          ~UPGRADES~          ||\n"
				 +"==================================\n"
				 +"||                              ||\n"
				 +"||  [1] ENGINE:      " + strEng + " [-" + eng + "%]  ||\n"
				 +"||  [2] SCANNER:     " + strScn + "        ||\n"
				 +"||  [3] STORAGE:     " + strStg + " [" + stg + "]  ||\n"
				 +"||  [4] HAUL:        " + strHal + " [-" + haulDefCoef + "%]  ||\n"
				 +"||  [5] AUTOPILOT:  " + ((Autopilot) ? " [X]"  + "        ||\n" : " [ ]" + "        ||\n")
				 +"||  [6] LASER DRILL:" + ((hasDrill) ? " [X]"  + "        ||\n" : " [ ]" + "        ||\n")
				 +"||                              ||\n"
				 +"==================================\n";	
	 
	 	JTextArea Upgradesarea = new JTextArea(str);
		Upgradesarea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		Upgradesarea.setEditable(false);
		Upgradesarea.setOpaque(false);
	 
		JOptionPane.showMessageDialog(null, Upgradesarea);
	 
	 
	}// UpgradesCheck
	
}// class 