package p3.rols.plants;

import p3.gameLogic.Game;
import p3.rols.plants.types.CherryBomb;
import p3.rols.plants.types.PeaShooter;
import p3.rols.plants.types.Sunflower;
import p3.rols.plants.types.Wallnut;

public class PlantFactory {
		
	private static Plant[] availablePlants = {
		new Sunflower(),
		new PeaShooter(),
		new CherryBomb(),
		new Wallnut()
	
	};


	//Al igual que CommandParse, recorre todas las plantas y busca una en 
	//concreto, si la encuentra la crea y sino devuelve error
	//Cuando devuelve una planta la devuelve lista para ejecutarse
	public static Plant getPlant(String plantName, int x, int y, Game game){
		Plant plant = null;
		
		switch(plantName) {
		case "PEASHOOTER": case "P": plant = new PeaShooter(x, y, game);
		break;
		case "SUNFLOWER": case "S": plant = new Sunflower(x, y, game);
		break;
		case "WALLNUT": case "W": plant = new Wallnut(x, y, game);
		break;
		case "CHERRYBOMB": case "C": plant = new CherryBomb(x, y, game);
		break;
		}
		
		return plant;
	}
	
	//Devuelve un string con todas las plantas listo para mostrar por pantalla
	public static String listOfAvilablePlants() {
		String plants = "";
		for(int i = 0; i < availablePlants.length; i++) {
			plants = plants + availablePlants[i].plantHelp() + "\n";
		}
		return plants;
	}
	
	
	//IDEA DE PUTA MADRE DE PSYCH0
	public static String getPlantName(int n) {
		return availablePlants[n].getName();
	}

}
