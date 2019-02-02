package p3.rols.zombies.types;

import p3.gameLogic.Game;
import p3.rols.zombies.Zombie;

public class SportZombie extends Zombie {

	private static final String symbol = "X";
	private static final String name = "SPORTZOMBIE";
	private static int life = 2;
	private static final int damage = 1;
	private static final int frecuency = 1;
	private static int remainingCycles = frecuency;

	
	//Constructor
	public SportZombie(){
		super(symbol, name, life, damage, frecuency, remainingCycles);
		
	}	
	
	//Constructor 2
	public SportZombie(int x, int y, Game game) {
		
		super(symbol, name, life, damage, frecuency, remainingCycles);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	
	//------------------------FUNCIONES DEL PROGRAMA----------------------------
	//Funcion que comprueba si el zombie se puede mover, si no se puede mover entonces muerde (game)
	public void update() {
		this.move();
	}
	
	//Devuelve un string con todos los datos de un zombie
	public String externalise() {
		String plantInfo;
		plantInfo = this.symbol+":"+this.life+":"+this.x+":"+this.y+":"+this.remainingCycles ;
		return plantInfo;
	}
	
}