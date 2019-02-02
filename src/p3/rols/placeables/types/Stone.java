package p3.rols.placeables.types;

import p3.gameLogic.Game;
import p3.rols.placeables.Placeables;


public class Stone extends Placeables{

	private static final String symbol = "[X]";
	private static final String name = "STONE";
	private static int life = 1; //Nunca recibe daño
	private static final int cost = 50;
	
	//Constructor
	public Stone(){
		super(symbol, name, life, cost);
		
	}
	
	//Constructor 2
	public Stone(int x, int y, Game game) {
		
		super(symbol, name, life, cost);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	
}
