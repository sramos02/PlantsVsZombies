package p3.rols.zombies;

import p3.gameLogic.gameManager.auxiliar.GameObject;

public abstract class Zombie extends GameObject {

	
	//Constructor 
	public Zombie(String symbol, String name, int life, int damage, int frecuency, 
			int remainingCycles){
		super(symbol, name, life, damage, frecuency, remainingCycles);
		
	}
	
	// Recibe danho
	//@Override
	public boolean damaged(int plantDamage){
		boolean dead = false;
		
		this.life = life - plantDamage;
		dead = (this.life <= 0);
		return dead;
	}
	
	//Se mueve el zombie
	public void move() {
			this.y--;
	}
	
	//------------ZombieFactory-----------------
	//Devuelve un String con la informacion del zombie
	public String toString(boolean isDebug) {
		String info;
		if(!isDebug) info =  symbol + "[" + this.life + "]";
		else info = symbol + "[l:" + this.life + ",x:" + x + ",y:" + y + ",t:" + remainingCycles + "]";
		return info;																
	}
	
	public String externalise() {
		String zombieInfo;
		zombieInfo = this.symbol+":"+this.life+":"+this.x+":"+this.y+":"+this.remainingCycles ;
		return zombieInfo;
	}
	
	//Comprueba si el nombre coincide con el zombie y de ser asi lo devuelve nuevo
	public Zombie parse(String zombieName) {
		if((!zombieName.equals(name)) || (!zombieName.equals(symbol))) {return null;}
		else {return this;}
	}

	public void setLife(int l) {
		this.life = l;
	}

	public void setRemainingCycles(int remCycles) {
		this.remainingCycles = remCycles;
	}
	
	
}
