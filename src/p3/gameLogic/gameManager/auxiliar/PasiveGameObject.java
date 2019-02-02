package p3.gameLogic.gameManager.auxiliar;

import p3.gameLogic.Game;

public abstract class PasiveGameObject {
	protected String symbol;
	protected String name;
	protected int x;
	protected int y;
	protected int life;
	protected Game g;
	
	//Constructor
	public PasiveGameObject(String symbol, String name, int life){
		this.symbol = symbol;
		this.name = name;
	}
		
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
	
	
	public String getName(){
		return this.name;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	

}
