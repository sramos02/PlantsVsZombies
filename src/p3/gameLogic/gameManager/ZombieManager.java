package p3.gameLogic.gameManager;

import java.util.Random;

import p3.gameLogic.Game;
import p3.gameLogic.gameManager.auxiliar.Board;
import p3.gameLogic.gameManager.auxiliar.Level;

public class ZombieManager {
	private Level level;
	private Random rand;
	private int remainingZombies;
	

	//Constructor
	public ZombieManager(Level lev, Random rand) {
		
		this.remainingZombies = lev.getMaxZombies();
		this.level = lev;
		this.rand = rand;
	}

	//------------------------FUNCIONES DEL PROGRAMA----------------------------
	//Anhade un zombie al juego
	public void AddZombie(Board board, Game game) {
		if((this.remainingZombies > board.zombiesNum()) && (this.level.getFrequency() >= this.rand.nextDouble())) {
			int y = this.rand.nextInt(4);
			board.addZombie(7, y, game);
			this.remainingZombies--;
		}
	}
	
	//------------------------AUXILIAR----------------------------
	public int getRemainingZombies() {
		
		return this.remainingZombies;
	}

	public void setRemainingZombies(int rZombies) {
		this.remainingZombies = rZombies;
	}
}

