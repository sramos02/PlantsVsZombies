package p3.gameLogic.gameManager.auxiliar;

import p3.gameLogic.Game;

public abstract class GameObject {
	protected String symbol;
	protected String name;
	protected int x;
	protected int y;
	protected int life;
	protected int damage;
	protected int frecuency;
	protected int remainingCycles;
	protected Game g;
	
	//Constructor
	public GameObject(String symbol, String name, int life, int damage, int frecuency, int remainingCycles){
		this.symbol = symbol;
		this.name = name;
		this.life = life;
		this.damage = damage;
		this.frecuency = frecuency;
		this.remainingCycles = remainingCycles;
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Metodos comunes redefinidos para planta y zombie 
	public abstract void update();
	public abstract boolean damaged(int damage);
	
	//------------------------AUXILIAR----------------------------
	public void setGame(Game g){
		this.g = g;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public int getFrecuency(){
		return this.frecuency;
	}
	
	public int getRemainingCycles(){
		return this.remainingCycles;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	
	
}