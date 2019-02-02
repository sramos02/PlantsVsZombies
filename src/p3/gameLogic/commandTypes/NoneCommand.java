package p3.gameLogic.commandTypes;

import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public class NoneCommand extends NoParamCommands{
	private static String commandName = "NONE";
	public static final String helpText = "Skips cycle.";
	
	//Constructor
	public NoneCommand() {
		super(commandName, helpText, null);
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Hace update del juego y lo pinta
	public boolean execute(Game myGame) { 
		myGame.update(); 
		return true;
	}

	//Comprueba si el comando introducido es NONE y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command none = null;
		if(commandWords[0].equals("N") || commandWords[0].equals("NONE") || commandWords[0].equals("")) { 
			if (commandWords.length == 1) {none = new NoneCommand(); }
			else throw new CommandParseException("None command has no arguments");		
		}
		return none;
	}

	
}