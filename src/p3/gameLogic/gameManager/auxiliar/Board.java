package p3.gameLogic.gameManager.auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import p3.exceptions.FileContentsException;
import p3.gameLogic.Game;
import p3.rols.lists.PlaceableList;
import p3.rols.lists.PlantList;
import p3.rols.lists.ZombieList;
import p3.rols.placeables.PlaceableFactory;
import p3.rols.placeables.Placeables;
import p3.rols.plants.Plant;
import p3.rols.plants.PlantFactory;
import p3.rols.plants.types.Sunflower;
import p3.rols.zombies.Zombie;
import p3.rols.zombies.ZombieFactory;

public class Board{
	
	private PlantList pl;
	private ZombieList zl;
	private PlaceableList xl;
	
	//Constructor
	public Board(int maxZombies) {	
		this.pl = new PlantList();
		this.zl = new ZombieList();
		this.xl = new PlaceableList();
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Recolecta los soles en cada turno del juego
	public int getSuns() {
		
		int ret = 0;
		for(int i = 0; i < this.pl.getPNumber(); i++) {
			Plant aux = this.pl.getPlantByPos(i);
			if(aux.isSunFlower()) {
				Sunflower a = (Sunflower) aux;
				ret += a.action();
				if(torchBuf(aux.getX(), aux.getY())) {ret += 5;} //bufo extra
			}
		}
		
		return ret;
	}
	
	//Crea un String con un objeto del juego dadas sus coordenadas y si estamos en modo Debug
	//Para ello recorrerá TODOS los emenetos del tablero separados por listas
	public String getObjectString(int x, int y, boolean isDebug) {
		String obj = " ";

		for(int i = 0; i< this.pl.getPNumber(); i++) {
			Plant pln = null;
			pln = this.pl.getPlantByCoord(x, y);
			if(pln != null) {return pln.toString(isDebug);}
		}
		
		for(int i = 0; i< this.zl.getZNumber(); i++) {
			Zombie zb = null;
			zb = this.zl.getZombieByCoord(x, y);
			if(zb != null) {return zb.toString(isDebug);}
		}
		
		for(int i = 0; i < this.xl.getPNumber(); i++) {
			Placeables p = null;
			p = this.xl.getPByCoord(x, y);
			if(p != null) {return p.toString(isDebug);}
		}
		return obj;
	}

	//Comprueba si hay algo en las coordenadas propuestas de nuevo recorriendo TODO
	public boolean nothingHere(int x, int y) {
		return ((this.pl.getPlantByCoord(x, y) == null) && (this.zl.getZombieByCoord(x, y) == null) && (this.xl.getPByCoord(x, y) == null));
	}
	
	//Danha una planta con el danho recibido
	public void dmgToFlower(int x, int y, int damage) {
		this.pl.damagePlant(x, y, damage); 		
	}
	
	
	//Anhade un zombie a la lista haciendo un random de todos los posibles
	public void addZombie(int y, int x, Game game) {
		
		Random rand = new Random();
		int numRand = rand.nextInt(4);
		Zombie aux;
		if(numRand == 0)
			aux = ZombieFactory.getZombie("COMMONZOMBIE", x, y, game);
		else if(numRand == 1)
			aux = ZombieFactory.getZombie("BUCKETHEADZOMBIE", x, y, game);
		else if(numRand == 2)
			aux = ZombieFactory.getZombie("SPORTZOMBIE", x, y, game);
		else 
			aux = ZombieFactory.getZombie("WIZARDZOMBIE", x, y, game);
		this.zl.setNewZombie((Zombie)aux);
		
	}
	
	//Guarda las listas en un fichero bw
	public void store(BufferedWriter bw) throws IOException{
		bw.write("plantList: ");
		if(this.pl.getPNumber() > 0) {
			for(int i = 0; i < this.pl.getPNumber()  - 1; i++) {
				bw.write(getPlantInfoByPos(i) + ", ");
			}
			bw.write(getPlantInfoByPos(this.pl.getPNumber() - 1));
		}
		
		bw.write("\n");
		bw.write("zombieList: ");
		if(this.zl.getZNumber() > 0) {
			for(int i = 0; i< this.zl.getZNumber() - 1; i++) {
				bw.write(this.getZombieInfoByPos(i) + ", ");
			}
			bw.write(getZombieInfoByPos(this.zl.getZNumber() - 1));
		}
		
		bw.write("\n");
		bw.write("placeableList: ");
		if(this.xl.getPNumber() > 0) {
			for(int i = 0; i< this.xl.getPNumber() - 1; i++) {
				bw.write(this.getPInfoByPos(i) + ", ");
			}
			bw.write(getPInfoByPos(this.xl.getPNumber() - 1));
		}
		
	}
	
	//Carga las listas de un tablero br introduciendo TODOS los objetos del juego en sus listas
	public boolean load(BufferedReader br, Game myGame) throws IOException, FileContentsException {
		String[] line;
		String[] words;
		
		//Info
		String type;
		int life;
		int x; 
		int y;
		int remCycles;
		
		line = myGame.loadLine(br, "plantList", true);
		for(int i = 0; i < line.length; i++) {
			words = line[i].split(":");

			type = words[0];
			life = Integer.parseInt(words[1]);
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[3]);
			remCycles = Integer.parseInt(words[4]);
			
			if(nothingHere(x, y)) {
				Plant nPlant = PlantFactory.getPlant(type, x, y, myGame);
				setNewPlant(nPlant);
				pl.loadPlant(i, life, x, y, remCycles);
			}
			else return false;
		}
		
		line = myGame.loadLine(br, "zombieList", true);
		for(int i = 0; i < line.length; i++) {
			words = line[i].split(":");
			
			type = words[0];
			life = Integer.parseInt(words[1]);
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[3]);
			remCycles = Integer.parseInt(words[4]);
			
			if(nothingHere(x, y)) {
				Zombie nZombie = ZombieFactory.getZombie(type, x, y, myGame);
				setNewZombie(nZombie);
				zl.loadZombie(i, life, x, y, remCycles);
			}
			else return false;
		}
			
		line = myGame.loadLine(br, "placeableList", true);
		for(int i = 0; i < line.length; i++) {
			words = line[i].split(":");
			
			type = words[0];
			life = Integer.parseInt(words[1]);
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[3]);
			
			if(nothingHere(x, y)) {
				Placeables p = PlaceableFactory.getP(type, x, y, myGame);
				setNewPlaceable(p);
				xl.loadP(i, life, x, y);
			}
			else return false;
		}
			
		
		if(!checkPositions()) {return false;}
		return true;
	}


	public void peaShoot(int x, int y) {
		int pos = 0;
		boolean found = false;
		while((pos < zombiesNum()) && !found) {
			int damage = 1;
			if(torchBuf(x, y)) {damage = damage + 1;}
			found = zl.takeShoot(damage, x, y, pos, xl);
			pos++;
			
		}
	}
	
	
	public boolean torchBuf(int x, int y) {
			
		for(int i = 0; i < xl.getPNumber(); i++) {
			
			Placeables torch = xl.getPByPos(i);

			if((torch.getName() == "TORCH") &&
					(((torch.getX() + 1 == x) && (torch.getY() == y)) ||
					((torch.getX() == x) && (torch.getY() + 1 == y))  || 
					((torch.getX() - 1 == x) && (torch.getY() == y))  ||
					((torch.getX() == x) && (torch.getY() - 1 == y)))) {
						return true;
			}
		}
		
		return false;
	}
	
	//Comprueba que todas las plantas y los zombies se encuentren dentro de rango
	public boolean checkPositions() {
		for(int i = 0; i < pl.getPNumber(); i++) {
			if((pl.getPlantByPos(i).getX() > 3) || (pl.getPlantByPos(i).getX() < 0)) {return false;}
			if((pl.getPlantByPos(i).getY() >= 7)  || (pl.getPlantByPos(i).getY() < 0)) {return false;}
		}	
		for(int i = 0; i < zl.getZNumber(); i++) {
			if((zl.getZombieByPos(i).getX() > 3) || (zl.getZombieByPos(i).getX() < 0)) {return false;}
			if((zl.getZombieByPos(i).getY() > 7) || (zl.getZombieByPos(i).getY() < 0)) {return false;}
		}
		
		for(int i = 0; i < xl.getPNumber(); i++) {
			if((xl.getPByPos(i).getX() > 3) || (xl.getPByPos(i).getX() < 0)) {return false;}
			if((xl.getPByPos(i).getY() > 7) || (xl.getPByPos(i).getY() < 0)) {return false;}
		}
		
		return true;
	}
	

	public boolean isAStone(int x, int y) {
		for(int i = 0; i < xl.getPNumber(); i++) {
			if(xl.getPByCoord(x, y) != null) {return true;}
		}
		return false;
	}
	
	
	//------------------------AUXILIAR----------------------------
	public void setNewPlant(Plant p) {		
		this.pl.setNewPlant(p);
	}
	
	public void setNewPlaceable(Placeables p) {
		this.xl.setNewP(p);
	}

	
	public void setNewZombie(Zombie z) {		
		this.zl.setNewZombie(z);
	}
	

	public void cherryXplode(int x, int y) {
		this.zl.takeXplosion(x, y);
	}
	
	public boolean checkLoose() {
		return (this.zl.checkLoose());
	}
	
	public int zombiesNum() {
		return zl.getZNumber();
	}
		
	public int plantsNum() {
		return pl.getPNumber();
	}
   	
	public Zombie getZombie(int pos) {
		
		return this.zl.getZombieByPos(pos);
	}
	
	public Plant getPlant(int pos) {
		return this.pl.getPlantByPos(pos);
	}
	
	public int getPlantCount() {
		
		return this.pl.getPNumber();
		
	}
	
	public int getPCount() {
		
		return this.xl.getPNumber();
		
	}
	
	public int getZombieCount() {
		
		return this.zl.getZNumber();
		
	}
	
	public String getPlantInfoByPos(int i) {
		return this.pl.externalise(i);
	}
	
	
	public String getPlantByPos(int i, boolean isDebug) {
		
		return this.pl.toString(i, isDebug);
		
	}
	
	public String getZombieInfoByPos(int i) {
		return this.zl.externalise(i);
		
	}
	
	public String getPInfoByPos(int i) {
		return this.xl.externalise(i);
		
	}
	
	public String getZombieByPos(int i, boolean isDebug) {
		
		return this.zl.toString(i, isDebug);
		
	}
	
	public String getPByPos(int i, boolean isDebug) {
		
		return this.xl.toString(i, isDebug);
		
	}

	public String getZombieType(int pos) {
		Zombie a = zl.getZombieByPos(pos);
		return a.getSymbol();
	}

}