package p3.rols.plants.types;

import p3.gameLogic.Game;
import p3.rols.plants.Plant;

public class PeaShooter extends Plant {
	
	private static final String symbol = "P";
	private static final String name = "PEASHOOTER";
	private static int life = 3;
	private static final int damage = 1;
	private static final int frecuency = 1;
	private static int remainingCycles = frecuency;
	private static final int cost = 50;
	private Game g;
	
	
	
	
	//Constructor
	public PeaShooter(){
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		
	}
	
	//Constructor 2
	public PeaShooter(int x, int y, Game game) {
		
		super(symbol, name, life, damage, frecuency, remainingCycles, cost);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------
	public void update() {
		g.peaShooterAction(this);
	}
	

	
	
	
}