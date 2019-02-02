package p3.rols.plants.types;

import p3.gameLogic.Game;
import p3.rols.plants.Plant;

public class Sunflower extends Plant {

	private static final String symbol = "S";
	private static final String name = "SUNFLOWER";
	private static int life = 1;
	private static final int damage = 0;
	private static final int frecuency = 2;
	private static int remainingCycles = frecuency;
	private static final int cost = 20;
	public static final int generatedSuns = 10;
	
	//Constructor
	public Sunflower(){
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
	}
	
	//Constructor 2
	public Sunflower(int x, int y, Game game) {
		
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------
	public void update() {
		//Funcion implementada en el game, por no cambiar cdigo dejamos esto
		//vacio y utilizamos la funcin accion(return int)
	}
	
	//Realiza la accion de la planta si toca, si no resta un contador para la accion
	public int action(){
		int ret = 0;
		if(Sunflower.remainingCycles > 1) {Sunflower.remainingCycles--;}
		else {
			ret = generatedSuns;
			Sunflower.remainingCycles = Sunflower.frecuency;
		}
		return ret;
	}
	
//	//Devuelve la informacion que se mostrara por pantalla en la lista de plantas
//	@Override
//	public String plantHelp() {
//		String info = name + ": Cost: " + cost + "suncoins    Harm: " + damage ;
//		return info;
//	}
	
	
}

