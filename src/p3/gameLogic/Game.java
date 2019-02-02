package p3.gameLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import p3.PlantsVsZombies;
import p3.exceptions.CommandExecuteException;
import p3.exceptions.FileContentsException;
import p3.gameLogic.gameManager.SunCoinManager;
import p3.gameLogic.gameManager.ZombieManager;
import p3.gameLogic.gameManager.auxiliar.Board;
import p3.gameLogic.gameManager.auxiliar.Level;
import p3.gameLogic.print.BoardPrinter;
import p3.gameLogic.print.DebugPrinter;
import p3.gameLogic.print.ReleasePrinter;
import p3.rols.placeables.PlaceableFactory;
import p3.rols.placeables.Placeables;
import p3.rols.plants.Plant;
import p3.rols.plants.PlantFactory;
import p3.rols.plants.types.CherryBomb;
import p3.rols.plants.types.PeaShooter;
import p3.rols.zombies.Zombie;

public class Game {
	public static final String wrongPrefixMsg = "Unknown game attribute: ";
	public static final String lineTooLongMsg = "Too many words on line commencing: ";
	public static final String lineTooShortMsg = "Missing data on line commencing: ";
	private int cycles;
	private boolean winGame;
	private boolean looseGame;
	private boolean isFinished;
	private int maxZombies;
	private SunCoinManager coins;
	private Board board;
	private Level gameLevel;
	private String printMode;
	private ZombieManager zManager;
	private int seed;
	private Random rand; //Esto se usa para inicializar de nuevo el juego al cargarlo
	private String level;
	
	//Constructor
	public Game(String lev, Random rand, int seed) {	
		this.rand = rand;
		this.seed = seed;
		this.level = lev;
		this.winGame = false;
		this.looseGame = false;
		this.isFinished = false;
		this.cycles = 0;
		this.gameLevel = Level.fromString(lev);
		this.maxZombies = gameLevel.getMaxZombies();
		this.board = new Board(this.maxZombies);		
		this.coins = new SunCoinManager();
		this.zManager = new ZombieManager(this.gameLevel, rand);
		this.printMode = "RELEASE";
	}
	
	//------------------------FUNCIONES DEL PROGRAMA----------------------------	
	
	//Realiza la accción de TODAS las plantas recorriendo la lista de ellas
	public void plantAction() {	
		for(int i = 0; i < board.plantsNum(); i++) {
			board.getPlant(i).update();
		}
	}
	
	
	//Realiza la accion de un zombie, movimiento o ataque segun si tiene algo
	//delante o la casilla está vacia
	public void zombieAction() {  
		for(int pos = 0; pos < this.board.zombiesNum(); pos++) {
			Zombie zombie = this.board.getZombie(pos);
			
			if(this.board.getZombieType(pos) != "W"){  //Solo comprueba la acción de los que se mueven
				if(this.board.nothingHere((zombie.getX()), zombie.getY()-1) || this.board.isAStone((zombie.getX()), zombie.getY()-1)) zombie.update();
				else this.board.dmgToFlower(zombie.getX(), (zombie.getY() - 1), zombie.getDamage());
			}
			
			else zombie.update();
		}
	}

	
	//Realiza la accion de un peaShooter, dispara si puede comprobando todas las posiciones
	//desde la planta hasta el final del tablero
	public void peaShooterAction(PeaShooter p) {
		this.board.peaShoot(p.getX(), p.getY());
	}
	
	//Crea la accion de una cherryBomb, comprueba si explota y en este caso
	//mata la planta y danha los zombies cercanos
	public void cherryBombAction(CherryBomb c) {
		this.board.cherryXplode(c.getX(), c.getY());
		this.board.dmgToFlower(c.getX(), c.getY(), c.getLife());
	}
	
	
	public void wizardZombieAttack(int damage) {
		int a = board.plantsNum();
		if(a != 0) {
			int f = rand.nextInt(a);
			Plant objetive = board.getPlant(f);
			this.board.dmgToFlower(objetive.getX(), (objetive.getY()), damage);
		}
		
	}

	
	//Anhade una planta al juego del tipo requerido en las posiciones requeridas comprobando
	//si la posicion x,y esta disponible y si hay soles suficientes, en ese caso anhade la 
	//ueva planta a la lista y quita soles
	public boolean addPlant(String type, int x, int y) throws CommandExecuteException {
		Plant newPlant = null; 
	
		boolean done = isCorrectPos(x, y);
		if(done) {
			
			newPlant = PlantFactory.getPlant(type, x, y, this);
			if(coins.thereAreSuns(newPlant.getCost())){				
				this.board.setNewPlant(newPlant);
				coins.setSuns(coins.getSuns() - newPlant.getCost());
			}
			
			else { 
				update();
				done = true;
			}
		}
		return done;
	}
	
	

	public boolean addTorch(int x, int y) throws CommandExecuteException {
		Placeables newP = null; 
		
		boolean done = isCorrectPos(x, y);
		if(done) {
			
			newP = PlaceableFactory.getP("TORCH", x, y, this);
			if(coins.thereAreSuns(newP.getCost())){				
				this.board.setNewPlaceable(newP);
				coins.setSuns(coins.getSuns() - newP.getCost());
			}
			
			else { 
				update();
				done = true;
			}
		}
		return done;
	}
	
	public void setStones(int n) {
		Placeables newP = null;
		int i = 0;
		
		while(i < n) {
			int x = rand.nextInt(PlantsVsZombies.MAX_ROWS);
			int y = rand.nextInt(PlantsVsZombies.MAX_COLUMNS);
			
			if(board.nothingHere(x, y)) {
				newP = PlaceableFactory.getP("STONE", x, y, this);
				board.setNewPlaceable(newP);
				i++;
			}
			
		}
	}
		
	//Comprueba la posicion en la que se insertara una planta comprobando si entra
	//dentro de los limites, en este caso comprueba si la posicion está vacia 
	private boolean isCorrectPos(int x, int y) throws CommandExecuteException {
		boolean correct = false;
		boolean empty = true;
/*x*/	if((x < PlantsVsZombies.MAX_ROWS) && (x >= 0)) {
/*y*/		if((y < PlantsVsZombies.MAX_COLUMNS - 1) && (y >= 0)) {
				empty = board.nothingHere(x, y);
/*empty*/		if(empty) {correct = true; }
				else throw new CommandExecuteException("There is another plant in this position");
			}
		}
		if(!correct && empty) {throw new CommandExecuteException("Invalid coords: " + "[0 - " + (PlantsVsZombies.MAX_ROWS - 1) + "] [0 - " + (PlantsVsZombies.MAX_COLUMNS- 2) + "]");}
		return correct;
	}
	
	//Bucle del juego, realiza toda la logica del juego
	public void update() {	
		this.coins.sunHarvester(this.board.getSuns());
		this.plantAction();
		this.zombieAction();
		this.zManager.AddZombie(this.board, this);		
		this.winGame = ((this.zManager.getRemainingZombies() == 0) && (this.board.zombiesNum() == 0));
		this.looseGame = this.board.checkLoose();
		this.isFinished = (this.winGame || this.looseGame);
		this.cycles++;
	}
	
	//Crea un tablero que manda pintar en cada ciclo según el modo de pintado actual
	public void printGame() {
		BoardPrinter printer = null;
		String[][] board = null;
		String information = null;
		
		if(getPrintMode().equals("RELEASE")) { 
			board = new String[PlantsVsZombies.MAX_ROWS] [PlantsVsZombies.MAX_COLUMNS]; 
			printer = new ReleasePrinter(board, information);
		}
		else { 
			board = new String[PlantsVsZombies.MAX_ROWS * PlantsVsZombies.MAX_COLUMNS][1]; 
			printer = new DebugPrinter(board, information);
		}
		
		System.out.println(printer.printGame(this));
	}
	
	//Devuelve un string de un elemento con su vida
	public String getElement(int x, int y) {

		String element = " ";
		boolean full = false;
		full = (!board.nothingHere(x, y));
		if (full) {
			element = board.getObjectString(x, y, isDebug());
		}
		
		return element;
	}
	
	//Devuelve informacion importante acerca del juego que se mostrara antes del tablero
	  public String gameInformation() {
		String information = "\n";
		information = information + ("Number of cycles: ") + this.cycles + ("\n");
		information = information + ("Sun coins: ") + this.coins.getHarvestedSuns() + ("\n");
		information = information + ("Remaining zombies: " + this.zManager.getRemainingZombies()); 
		if(this.printMode.equals("DEBUG")) {
	  		information = information + ("\nLevel: " + gameLevel.getDificulty()); 
	  		information = information + ("\nSeed: " + seed); 
	
		}
		return information;
	}
	  
	//Devuelve informacion importante acerca del juego que se insertara en las partidas guardadas
	public String informationToFile() {
		String information = "\n";
		information = information + ("cycles: ") + this.cycles + ("\n");
		information = information + ("sunCoins: ") + this.coins.getHarvestedSuns() + ("\n");
		information = information + ("Level: " + gameLevel.getDificulty() + ("\n"));
		information = information + ("remZombies: " + this.zManager.getRemainingZombies() + ("\n"));  
		return information;
	}
	  
	 //Devuelve si el juego esta en modo debug o no
	 public boolean isDebug() {
		 if(this.printMode == "DEBUG") {return true;}
		 return false;
	 }
	 
	 //Escribe el fichero para guardar
	 public void store(BufferedWriter bw) throws IOException {
		bw.write("Plants Vs Zombies v3.0");
		bw.newLine();
		bw.write(informationToFile());
		board.store(bw);	
	 }
	 
	 //Comprueba la coherencia de la logica interna del juego al cargar una partida para evitar incongruencias
	 private boolean gameStateIsCorrect() {
		//1) CYCLES < 0
			if(this.cycles < 0) {return false;}
		//2) SUNCOINS < 0
			if(this.coins.getHarvestedSuns() < 0) {return false;}		
		//3) REMAININGZOMBIES < 0 || REMAININGZOMBIES > MAXZOMBIES(LEVEL)
			if((zManager.getRemainingZombies() < 0) || (zManager.getRemainingZombies() > gameLevel.getMaxZombies())) {return false;}
		return true;
	}
	 
	 //Carga una partida de fichero
	 public void load(BufferedReader br) throws IOException, FileContentsException{
	 	String[] line;
	 	Boolean correct;
	 	
		//Atributos que vienen desde el fichero
		String level;
		int cycles;
		int sunCoins;
		int remainingZombies;
		
		line = loadLine(br, "cycles", false);
		cycles = Integer.parseInt(line[0]);
		
		line = loadLine(br, "sunCoins", false);
		sunCoins = Integer.parseInt(line[0]);
		
		line = loadLine(br, "Level", false);
		level = line[0];
		
		line = loadLine(br, "remZombies", false);
		remainingZombies = Integer.parseInt(line[0]);
		
		
		Level aux = this.gameLevel.parse(level);
		if(aux == null) {
			throw new FileContentsException("Level can't be readed");
		}
		
		Board auxBoard = new Board(aux.getMaxZombies()); //Listas
				
		correct = auxBoard.load(br, this);
		if(correct) {	
			correct = gameStateIsCorrect();
			if(correct) { //INSERCION AL JUEGO
				//random se queda igual que el de antes
				//seed se queda igual que la de antes (esto no tiene sentido pero no nos dan la nueva)
				this.cycles = cycles;
				this.winGame = false;
				this.looseGame = false;
				this.isFinished = false;
				this.gameLevel.parse(level);
				this.maxZombies = gameLevel.getMaxZombies(); //(Requiere haber inicializado Level antes)
				this.coins.setSuns(sunCoins);
				
				board = auxBoard; //Aqui van las listas
				zManager = new ZombieManager(this.gameLevel, this.rand);
				zManager.setRemainingZombies(remainingZombies);
				printMode = "RELEASE";
			}
		}
			
	}
	 
	//Lee una linea del fichero (codigo dado por el profesor)
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException{
		String[] words;
		String line = inStream.readLine().trim();
		
		// cut the prefix and the following colon off the line then trim it to get attribute contents
		if ( !line.startsWith(prefix + ":") )
			throw new FileContentsException(wrongPrefixMsg + prefix);
		
		String contentString = line.substring(prefix.length()+1).trim();
		
		if (!contentString.equals("")) {
			if (!isList) {
				words = contentString.split("\\s+");
				if (words.length != 1) throw new FileContentsException(lineTooLongMsg + prefix);
			}
			else words = contentString.split(",\\s*");
		}
		else {
			if (!isList) throw new FileContentsException(lineTooShortMsg + prefix);
			words = new String[0];
		}
	return words;
	}
	

	//Crea un nuevo juego (reset)
	public void setNewGame() {
		new Game(level, rand, seed);
		this.rand = rand;
		this.seed = seed;
		this.level = level;
		this.winGame = false;
		this.looseGame = false;
		this.isFinished = false;
		this.cycles = 0;
		this.gameLevel = Level.fromString(level);
		this.maxZombies = gameLevel.getMaxZombies();
		this.board = new Board(this.maxZombies);		
		this.coins = new SunCoinManager();
		this.zManager = new ZombieManager(this.gameLevel, rand);
		this.printMode = "RELEASE";
		
	}
	
	//------------------------AUXILIAR----------------------------	
	  public boolean getWin() {
		  return this.winGame;
	  }
	  
	  public boolean getLoose() {
		  return this.looseGame;
	  }
	  
	  public void endGame() {
			this.isFinished = true;
	  }
	  
	  public boolean isFinished() {
		  return this.isFinished;
	  }

	  public void setPrintMode(String mode) {
		  this.printMode = mode;
	  }
	  
	  public String getPrintMode() {
		  return this.printMode;
	  }
	  
	  public int getPlantCount() { 
		  return this.board.getPlantCount();
	  }
	  
	  
	  public int getPCount() { 
		  return this.board.getPCount();
	  }
	  
	  public int getZombieCount() {
		  
		  return this.board.getZombieCount();
		  
	  }
	  
	  public String getPlantByPos(int i, boolean isDebug) {
		  
		  return this.board.getPlantByPos(i, isDebug);
		  
	  }
	  
	  public String getZombieByPos(int i, boolean isDebug) {
		  
		  return this.board.getZombieByPos(i, isDebug);
		  
	  }
	  
	  public String getPByPos(int i, boolean isDebug) {
		  
		  return this.board.getPByPos(i, isDebug);
		  
	  }

	public void giveCoins(int coins) {
		this.coins.setSuns(this.coins.getSuns() + coins);
	}


}