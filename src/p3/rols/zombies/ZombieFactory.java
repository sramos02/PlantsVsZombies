package p3.rols.zombies;

import p3.gameLogic.Game;
import p3.rols.zombies.types.BucketHeadZombie;
import p3.rols.zombies.types.CommonZombie;
import p3.rols.zombies.types.SportZombie;
import p3.rols.zombies.types.WizardZombie;

public class ZombieFactory {

	//Constructor
	private static Zombie[] availableZombies = {
			new CommonZombie(),
			new BucketHeadZombie(),
			new SportZombie(),
			new WizardZombie()
		
	};
	
	//-------------------FUNCIONES DEL PROGRAMA-----------------
	//Al igual que CommandParse, recorre todos los zombies y busca uno en 
	//concreto, si lo encuentra lo crea y sino devuelve error
	public static Zombie getZombie(String zombieName, int x, int y, Game game){
		Zombie newZombie = null;
		
		switch(zombieName) {
		case "SPORTZOMBIE": case "X": newZombie = new SportZombie(x, y, game);
		break;
		case "COMMONZOMBIE": case "N": newZombie = new CommonZombie(x, y, game);
		break;
		case "BUCKETHEADZOMBIE": case "B": newZombie = new BucketHeadZombie(x, y, game);
		break;
		case "WIZARDZOMBIE": case "W": newZombie = new WizardZombie(x, y, game);
		break;
		}
		
		return newZombie;
	}
	
	
}
