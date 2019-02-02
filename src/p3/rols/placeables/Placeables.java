package p3.rols.placeables;

import p3.gameLogic.gameManager.auxiliar.PasiveGameObject;

public class Placeables extends PasiveGameObject{
		
		protected int cost;

		//Constructor 
		public Placeables(String symbol, String name, int life, int cost){
			super(symbol, name, life);
			this.cost = cost;
		}
		
		//-------------------FUNCIONES DEL PROGRAMA-----------------

		public int getCost() {
			return this.cost;
		}
		
		public boolean damaged(int zombieDamage){
			boolean dead = false;
			if(this.getName() == "STONE") {return true;} //Nunca muere 
			
			this.life = life - zombieDamage;
			dead = (this.life <= 0);
			return dead;
		}
		
		
		//-------------Factory------------	
		public String toString(boolean isDebug) {
			String info;
			if(!isDebug) {
				if(this.name != "STONE") {info =  symbol + "[" + this.life + "]";}
				else {info =  symbol;}
			}
			else info = symbol + "[l:" + this.life + ",x:" + x + ",y:" + y + "]";
			return info;																
		}
		
		public String externalise() {
			String a;
			a = this.symbol+":"+this.life+":"+this.x+":"+this.y;
			return a;
		}
		

		
		//Comprueba si el nombre coincide con la planta y de ser asi la devuelve nueva
		public Placeables parse(String a) {
			if(a.equals(name) || a.equals(symbol)) {return this;}
			else {return null;}
		}
		
		//Devuelve la información que se mostrará por pantalla en la lista de plantas
		public String plantHelp() {
			String info = name + ": Cost: " + cost + "suncoins" ;
			return info;
		}


		public void setLife(int l) {
			this.life = l;
		}

}

