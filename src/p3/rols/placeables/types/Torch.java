package p3.rols.placeables.types;

import p3.gameLogic.Game;
import p3.gameLogic.gameManager.auxiliar.PasiveGameObject;
import p3.rols.placeables.Placeables;

public class Torch extends Placeables{

	private static final String symbol = "[T]";
	private static final String name = "TORCH";
	private static int life = 2;
	private static final int cost = 50;
	
	//Constructor
	public Torch(){
		super(symbol, name, life, cost);
		
	}
	
	//Constructor 2
	public Torch(int x, int y, Game game) {
		
		super(symbol, name, life, cost);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}

}
