package p3.rols.lists;

import p3.PlantsVsZombies;
import p3.rols.placeables.Placeables;
import p3.rols.plants.types.CherryBomb;
import p3.rols.zombies.Zombie;

public class ZombieList {

	Zombie[] zList = new Zombie[PlantsVsZombies.MAX_ZOMBIES];
	private int zombiesNumber;
	
	//Constructor
	public ZombieList(){
		zombiesNumber = 0;
		
	}
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------
	//Update de todos los zombies
	public void update(){
		for(int i = 0; i < zombiesNumber; i++){
			zList[i].update();
			
		}
	}
	
	//Parece que no lo necesitamos, devuelve la posicion dadas las coordenadas
	public int isHere(int x, int y){
		int pos = -1;
		int cont = 0;
		boolean found = false;
		while ((cont < this.zombiesNumber) && (!found)) {
			if((this.getZombieByPos(cont).getX() == x) && (this.getZombieByPos(cont).getY() == y))
			{found = true; pos = cont;}
			cont++;
		}
		return pos;
	}
		



	//Danho de peashooter
	public boolean takeShoot(int damage, int x, int y, int pos, PlaceableList xl) {
		boolean found = false, dead;
		if(available(x, y, xl, pos)) {
			dead = this.zList[pos].damaged(damage);
			if(dead) {
				for(int i = pos; i < this.zombiesNumber - 1; i++){
					this.zList[i] = this.zList[i + 1];
				}
				this.zombiesNumber--;
			}
			found = true;
		}
		pos++;
		return found;
	}
	
	
	public boolean available(int x, int y, PlaceableList xl, int pos) {
		if((this.zList[pos].getX() == x) && (this.zList[pos].getY() > y)) { //si hay un zombie delante
			for(int i = 0; i < xl.getPNumber(); i++) {
				if((xl.getPByPos(i).getX() == x) && (xl.getPByPos(i).getY() > y)) {
					if(xl.getPByPos(i).getY() <= this.zList[pos].getY()) {
						return true;
					}
					
				}
				
			}
		}
		return false;
	}
	
	//Se activa cuando explota una petacereza y comprueba si tiene zombies a su alrededor para hacerles daño
	public void takeXplosion(int x, int y) {
		int pos = 0;
		boolean dead;
		CherryBomb aux = new CherryBomb(); 
		

		while((pos < this.zombiesNumber)) {  //La unica manera de que funcione correctamente es comprobar todas las posiciones por separado
			if((this.zList[pos].getX() == x-1) && (this.zList[pos].getY() == y)) {dead = this.zList[pos].damaged(aux.getDamage()); if(dead) {deleteZombie(this.zList[pos], pos);}} 
			if((this.zList[pos].getX() == x) && (this.zList[pos].getY() == y-1)) {dead = this.zList[pos].damaged(aux.getDamage()); if(dead) {deleteZombie(this.zList[pos], pos);}}
			if((this.zList[pos].getX() == x) && (this.zList[pos].getY() == y+1)) {dead = this.zList[pos].damaged(aux.getDamage()); if(dead) {deleteZombie(this.zList[pos], pos);}}
			if((this.zList[pos].getX() == x+1) && (this.zList[pos].getY() == y)) {dead = this.zList[pos].damaged(aux.getDamage()); if(dead) {deleteZombie(this.zList[pos], pos);}}
		}
	}
	
	//Elimina un zombie de una lista
	void deleteZombie(Zombie z, int pos) {
		for(int j = pos; j < this.zombiesNumber - 1; j++){
			this.zList[j] = this.zList[j + 1];
		}
		this.zombiesNumber--;	
	}
	
	//Comrpeuab si TODOS los zombies de la partida han muertos, en este caso el jugador gana la partida
	public boolean checkLoose() {
		
		int pos = 0;
		boolean found = false;
		while(pos < (this.zombiesNumber) && !found) {
			found = (this.zList[pos].getY() == 0);
			pos++;
		}
		
		return found;
	}
	
	//Devuelve un zombie dadas unas coordenadas
	public Zombie getZombieByCoord(int x, int y) {
		
		Zombie found = null;
		for(int pos = 0; pos < this.zombiesNumber; pos++) {
			if((this.zList[pos].getX() == x) && (this.zList[pos].getY() == y)) {found = this.zList[pos];}
		}
		return found;
	}
	
	//Carga un zombie de un fichero y lo inserta a la lista de zombies con sus nuevos atributos
	public void loadZombie(int i, int life, int x, int y, int remCycles) {
		this.zList[i].setLife(life);
		this.zList[i].setX(x);
		this.zList[i].setY(y);
		this.zList[i].setRemainingCycles(remCycles);
	}

	//-------------------AUXILIAR-----------------
	public void setNewZombie(Zombie z1) {
		this.zList[this.zombiesNumber] = z1;
		this.zombiesNumber++;
		
	}

	public int getPosX(int pos) {
		return zList[pos].getX();
	}
	
	public int getPosY(int pos) {
		return zList[pos].getY();
	}
	
	public String toString(int i, boolean isDebug) {
		
		return this.zList[i].toString(isDebug);
		
	}
	
	public int getZNumber() {
		return this.zombiesNumber;
	}
	
	public Zombie getZombieByPos(int pos) {
		return this.zList[pos];
	}
	
	public String externalise(int i) {
		return this.zList[i].externalise();
	}



}
