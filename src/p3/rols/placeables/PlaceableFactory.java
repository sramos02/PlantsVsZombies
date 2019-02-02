package p3.rols.placeables;

import p3.gameLogic.Game;
import p3.rols.placeables.types.Stone;
import p3.rols.placeables.types.Torch;

public class PlaceableFactory {		
	
	private static Placeables[] available = {
		new Stone(),
		new Torch()
	};


	
	public static Placeables getP(String name, int x, int y, Game game){
		Placeables a = null;
		
		switch(name) {
		case "STONE": case "[X]": a = new Stone(x, y, game);
		break;
		case "TORCH": case "[T]": a = new Torch(x, y, game);
		break;

		}
		
		return a;
	}
	
	//Devuelve un string con todas las plantas listo para mostrar por pantalla
	public static String listOfAvilablePlants() {
		String a = "";
		for(int i = 0; i < available.length; i++) {
			a = a + available[i].plantHelp() + "\n";
		}
		return a;
	}
	
	
	//IDEA DE PUTA MADRE DE PSYCH0
	public static String getName(int n) {
		return available[n].getName();
	}


}
