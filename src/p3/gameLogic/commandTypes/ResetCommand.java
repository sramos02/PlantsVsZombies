package p3.gameLogic.commandTypes;

import p3.gameLogic.Controller;
import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public class ResetCommand extends NoParamCommands{
	private static String commandName = "RESET";
	public static final String helpText = "Starts a new game.";
	public Controller c;
	
	//Constructor
	public ResetCommand() {
		super(commandName, helpText, null);
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Resetea el juego y crea uno nuevo
	public boolean execute(Game myGame) { 
		myGame.setNewGame();		
		return false;
	}

	//Comprueba si el comando introducido es RESET y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException {
		Command reset = null;
		if(commandWords[0].equals("R") || commandWords[0].equals("RESET")) {
			if (commandWords.length == 1) {reset =  new ResetCommand();}
		else throw new CommandParseException("Reset command has no arguments");	
		}
		return reset;
	}
		
}