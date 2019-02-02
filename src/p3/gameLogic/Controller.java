package p3.gameLogic;

import java.util.Random;
import java.util.Scanner;

import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.exceptions.FileContentsException;
import p3.gameLogic.commandTypes.Command;
import p3.gameLogic.commandTypes.CommandParse;

public class Controller {
	
	private Game myGame;
	private Scanner keyboard;
	private boolean print;
	private int seed;
	private String lev;
	private Random rand;
	
	//Constructor
	public Controller(String lev, Random rand, int seed) {
		myGame = new Game(lev, rand, seed);
		keyboard = new Scanner(System.in);
		this.lev = lev;
		this.rand = rand;
		this.seed = seed;
		print = true;
	}
	
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Genera el bucle del juego, lee la opcion del jugador y ejecuta la opcion elegida
	public void run() {
		myGame.setStones(3);
		myGame.printGame();
		while (!myGame.isFinished()) {
			
			System.out.print("Command >: ");
			String[] words = keyboard.nextLine().trim().split("\\s+");
			
			try {
				Command command = CommandParse.parseCommand(words);
				print = command.execute(myGame); 
				if(print) {myGame.printGame();}
					print = true	;			
			}
			catch (CommandParseException | CommandExecuteException ex) {
				System.err.format(ex.getMessage() + "\n");
			}
			
		
		}
		System.out.print("GAME OVER ");
		if(myGame.getWin()) {System.out.print("PLAYERS WIN");}
		else {if(myGame.getLoose()) {System.out.print("ZOMBIES WIN");}}
	}

}
	
	