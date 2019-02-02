package p3.gameLogic.commandTypes;

import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;


public class SunCoinCommand extends Command{
	static String commandName = "GIVECOINS";
	static String helpText = "Give x coins to the player.";
	static String[] commandHelp;

	private int coins;
	
	//Constructor
	public SunCoinCommand() {
		super(commandName, helpText, commandHelp);
	}
	
	//Constructor2
	public SunCoinCommand(int coins) {
		super(commandName, helpText, commandHelp);
		this.coins = coins;
	}
		

	//--------------FUNCIONES DEL PROGRAMA--------------------
	public boolean execute(Game myGame) {
		myGame.giveCoins(coins);
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		Command coins = null;
		if((commandWords[0].equals("GC")) || (commandWords[0].equals("GIVECOINS"))) { 
			if(commandWords.length == 2) {
				coins = new SunCoinCommand(Integer.parseInt(commandWords[1])); 
			}
			else throw new CommandParseException("Incorrect number of arguments for load command: Givecoins <coins>");	
		}
		return coins;
	}

}