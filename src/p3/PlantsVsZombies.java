package p3;

import java.util.Random;

import p3.exceptions.CommandInvalidArgs;
import p3.gameLogic.Controller;

public class PlantsVsZombies {

	public static final int MAX_PLANTS = 28; 
	public static final int MAX_ZOMBIES = 10;
	public static final int MAX_ROWS = 4;
	public static final int MAX_COLUMNS = 8;
	
	//Main
	public static void main (String [] args){
		String level = null;
		int seed = ' ';	
		Random rand;
		
		
		try {
			if (args.length == 1 || args.length == 2) {	
				level = args[0];
				
				if(!(level.toUpperCase().equals("EASY") || !(level.toUpperCase().equals("HARD")) || !(level.toUpperCase().equals("INSANE"))))
					throw new CommandInvalidArgs("Level must be one of this: EASY|HARD|INSANE");
				else {		
					if(args.length == 2) {
						seed = Integer.parseInt(args[1]); 
						rand = new Random(seed);
					}
					else rand = new Random();
					
					Controller controller = new Controller(level, rand, seed);
					controller.run();
				}
			}
			
			else new NumberFormatException("Usage: plantsVsZombies <level> [seed]");
		}
	
		catch(CommandInvalidArgs ex) {
			System.err.format(ex.getMessage() + "\n");
		}
		catch(NumberFormatException ex) {
			System.err.format("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number");
		}
	}
}