package p3.rols.plants.types;

import p3.gameLogic.Game;
import p3.rols.plants.Plant;

public class CherryBomb extends Plant{
		
	private static final String symbol = "C";
	private static final String name = "CHERRYBOMB";
	private static int life = 2;
	private static final int damage = 10;
	private static final int frecuency = 2;
	private static int remainingCycles = frecuency;
	private static final int cost = 50;
	
	//Constructor
	public CherryBomb(){
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		
	}
	
	//Constructor 2
	public CherryBomb(int x, int y, Game game) {
		
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------	
	public void update() {
		if(CherryBomb.remainingCycles > 1) {CherryBomb.remainingCycles--;}
		else g.cherryBombAction(this);
	}
	
	
}