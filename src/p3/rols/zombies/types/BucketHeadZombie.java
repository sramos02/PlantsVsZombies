package p3.rols.zombies.types;

import p3.gameLogic.Game;
import p3.rols.zombies.Zombie;

public class BucketHeadZombie extends Zombie {

	private static final String symbol = "B";
	private static final String name = "BUCKETHEADZOMBIE";
	private static int life = 8;
	private static final int damage = 1;
	private static final int frecuency = 4;
	private static int remainingCycles = frecuency;
	
	
	//Constructor
	public BucketHeadZombie(){
		super(symbol, name, life, damage, frecuency, remainingCycles);
		
	}

	//Constructor 2
	public BucketHeadZombie(int x, int y, Game game) {
		
		super(symbol, name, life, damage, frecuency, remainingCycles);
		this.x = x;
		this.y = y;
		this.g = game;
		
	}
	//-------------------FUNCIONES DEL PROGRAMA-----------------
	//Funcion que comprueba si el zombie se puede mover, si no se puede mover entonces muerde (game)
	public void update() {
		BucketHeadZombie.remainingCycles--;
		if(BucketHeadZombie.remainingCycles == 0) {
			this.move();
			BucketHeadZombie.remainingCycles = BucketHeadZombie.frecuency;
		}
	}
	
	//Devuelve un string con todos los datos de un zombie
	public String externalise() {
		String plantInfo;
		plantInfo = this.symbol+":"+this.life+":"+this.x+":"+this.y+":"+this.remainingCycles ;
		return plantInfo;
	}
	

	
}