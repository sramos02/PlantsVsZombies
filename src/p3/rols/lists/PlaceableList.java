package p3.rols.lists;

import p3.PlantsVsZombies;
import p3.rols.placeables.Placeables;

public class PlaceableList {

	
	Placeables[] pList = new Placeables[PlantsVsZombies.MAX_PLANTS]; //Por ejemplo
	private int pNumber;
	
	//Constructor
	public PlaceableList() {
		pNumber = 0;
	}
	

	//-------------------FUNCIONES DEL PROGRAMA-----------------
	public void damageP(int x, int y, int damage) {
		int pos = this.isHere(x, y);
		boolean dead = false;
		if(pos != -1) {
			dead = this.pList[pos].damaged(damage);
			if(dead) {
				for(int i = pos; i < this.pNumber - 1; i++){
					this.pList[i] = this.pList[i + 1];
				}
				this.pNumber--;
			}
		}
	}
	
	public void loadP(int i, int life, int x, int y) {
		this.pList[i].setLife(life);
		this.pList[i].setX(x);
		this.pList[i].setY(y);
	}
	 
	public int isHere(int x, int y) {
		int pos = -1; //- significa no encontrada
		int cont = 0;
		boolean found = false;
		while ((cont < pNumber) &&(!found)) {
			if((getPByPos(cont).getX() == x) && (getPByPos(cont).getY() == y)) {pos = cont; found = true;} //pff que palo
			else cont++;
		}
		return pos;
	}
		
	//Inserta una nueva planta al final del array y aumenta el numero de plantas
	public void setNewP(Placeables newP) {
		
		pList[this.pNumber] = newP;
		this.pNumber++;
	}
	
	public Placeables getPByCoord(int x, int y) {
		Placeables found = null;
		for(int pos = 0; pos < pNumber; pos++) {
			if((this.pList[pos].getX() == x) && (this.pList[pos].getY() == y)) {found = this.pList[pos];}
		}
		return found;
	}

	
	//------------------------AUXILIAR----------------------------
	public int getPNumber() {
		return this.pNumber;
	}
	
	public Placeables getPByPos(int pos) {
		return this.pList[pos];
	}
	
	public String toString(int i, boolean isDebug) {
		
		return this.pList[i].toString(isDebug);
		
	}
	
	public String externalise(int i) {
		return this.pList[i].externalise();
	}


	public void setPNumber(int plants) {
		this.pNumber = plants;
	}


	public void setNewPlant(Placeables p) {
		// TODO Auto-generated method stub
		
	}


}
