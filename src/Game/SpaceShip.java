package Game;

import javax.swing.JOptionPane;

public class SpaceShip {

	//========== InventoryCheck ==========// Shows the inventory
	public static void InventoryCheck(double Fe, double Cu, double U, double Al, double Ti) {
		JOptionPane.showMessageDialog(null, "Iron ore: " + Fe + "/" + SpaceMap.StorageCap + " kg\n" + 
											"Copper ore: " + Cu + "/" + SpaceMap.StorageCap + " kg\n" + 
											"Uranuim ore: " + U + "/" + SpaceMap.StorageCap + " kg\n"  +
											"Aluminium ore: " + Al + "/" + SpaceMap.StorageCap + " kg\n" + 
											"Titanium ore: " + Ti + "/" + SpaceMap.StorageCap + " kg\n"  , "Storage", JOptionPane.INFORMATION_MESSAGE);
	}// InventoryCheck
	
}
