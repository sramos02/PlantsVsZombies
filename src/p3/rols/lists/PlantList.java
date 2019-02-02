package p3.rols.lists;



import p3.PlantsVsZombies;
import p3.gameLogic.gameManager.auxiliar.Board;
import p3.rols.plants.Plant;

public class PlantList {
	
	Plant[] plantList = new Plant[PlantsVsZombies.MAX_PLANTS];
	private int plantsNumber;
	
	//Constructor
	public PlantList() {
		plantsNumber = 0;
	}
	

	//-------------------FUNCIONES DEL PROGRAMA-----------------
	//Hace update de todas las plantas de la lista, se utiliza en game para actualizar el juego
	public void update(){
		for(int i = 0; i < plantsNumber; i++){
			plantList[i].update();
		}
	}
	
	//Elimina una planta del array de peashooters y actualiza el tablero
	public void damagePlant(int x, int y, int damage) {
		int pos = this.isHere(x, y);
		boolean dead = false;
		if(pos != -1) {
			dead = this.plantList[pos].damaged(damage);
			if(dead) {
				for(int i = pos; i < this.plantsNumber - 1; i++){
					this.plantList[i] = this.plantList[i + 1];
				}
				this.plantsNumber--;
			}
		}
	}
	
	//Introduce una nueva planta a la lista desde un fichero actualizando todos sus atributos
	public void loadPlant(int i, int life, int x, int y, int remCycles) {
		this.plantList[i].setLife(life);
		this.plantList[i].setX(x);
		this.plantList[i].setY(y);
		this.plantList[i].setRemainingCycles(remCycles);
	}
	
	//Busca una planta en el array dadas sus coordenadas, devuelve su posicion
	public int isHere(int x, int y) {
		int pos = -1; //- significa no encontrada
		int cont = 0;
		boolean found = false;
		while ((cont < plantsNumber) &&(!found)) {
			if((getPlantByPos(cont).getX() == x) && (getPlantByPos(cont).getY() == y)) {pos = cont; found = true;}
			else cont++;
		}
		return pos;
	}
		
	//Inserta una nueva planta al final del array y aumenta el numero de plantas
	public void setNewPlant(Plant newP) {
		
		plantList[this.plantsNumber] = newP;
		this.plantsNumber++;
	}
	
	public Plant getPlantByCoord(int x, int y) {
		Plant found = null;
		for(int pos = 0; pos < plantsNumber; pos++) {
			if((this.plantList[pos].getX() == x) && (this.plantList[pos].getY() == y)) {found = this.plantList[pos];}
		}
		return found;
	}

	
	//------------------------AUXILIAR----------------------------
	public int getPNumber() {
		return this.plantsNumber;
	}
	
	public Plant getPlantByPos(int pos) {
		return this.plantList[pos];
	}
	
	public String toString(int i, boolean isDebug) {
		
		return this.plantList[i].toString(isDebug);
		
	}
	
	public String externalise(int i) {
		return this.plantList[i].externalise();
	}


	public void setPlantsNumber(int plants) {
		this.plantsNumber = plants;
	}
	



	
}


