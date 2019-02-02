package p3.rols.plants.types;

import p3.gameLogic.Game;
import p3.rols.plants.Plant;

public class Wallnut extends Plant{
	
	private static final String symbol = "W";
	private static final String name = "WALLNUT";
	private static int life = 10;
	private static final int damage = 0;
	private static final int frecuency = 0;
	private static int remainingCycles = 0;
	private static final int cost = 50;
	
	//Constructor
	public Wallnut(){
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		
	}
	
	//Constructor 2
	public Wallnut(int x, int y, Game game) {
		
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------
	public void update() {}
	
}
