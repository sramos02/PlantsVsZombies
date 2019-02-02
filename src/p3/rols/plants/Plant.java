package p3.rols.plants;

import p3.gameLogic.gameManager.auxiliar.GameObject;

public abstract class Plant extends GameObject{
	
	protected int cost;

	
	//Constructor 
	public Plant(String symbol, String name, int life, int damage, int frecuency, int remainingCycles, int cost){
		super(symbol, name, life, damage, frecuency, remainingCycles);
		this.cost = cost;
	}
	
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------

	public int getCost() {
		return this.cost;
	}
	
	@Override
	public boolean damaged(int zombieDamage){
		boolean dead = false;
		
		this.life = life - zombieDamage;
		dead = (this.life <= 0);
		return dead;
	}
	
	public boolean isSunFlower() {
		return this.symbol.equals("S");
	}
	
	public boolean isPeashooter() {
		return this.symbol.equals("P");
	}
	
	public boolean isCherrybomb() {
		return this.symbol.equals("C");
	}

	
	
	//-------------PlantFactory------------	

	//Devuelve un String con la información de la planta
	public String toString(boolean isDebug) {
		String info;
		if(!isDebug) info =  symbol + "[" + this.life + "]";
		else info = symbol + "[l:" + this.life + ",x:" + x + ",y:" + y + ",t:" + this.remainingCycles + "]";
		return info;																
	}
	
	public String externalise() {
		String plantInfo;
		plantInfo = this.symbol+":"+this.life+":"+this.x+":"+this.y+":"+this.remainingCycles ;
		return plantInfo;
	}
	

	
	//Comprueba si el nombre coincide con la planta y de ser asi la devuelve nueva
	public Plant parse(String plantName) {
		if(plantName.equals(name) || plantName.equals(symbol)) {return this;}
		else {return null;}
	}
	
	//Devuelve la información que se mostrará por pantalla en la lista de plantas
	public String plantHelp() {
		String info = name + ": Cost: " + cost + "suncoins    Harm: " + damage ;
		return info;
	}


	public void setLife(int l) {
		this.life = l;
	}


	public void setRemainingCycles(int remCycles) {
		this.remainingCycles = remCycles;
	}

}
