package p3.gameLogic.commandTypes;

import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public class ExitCommand extends NoParamCommands {
	private static String commandName = "EXIT";
	public static final String helpText = "Terminates the program.";
	
	//Constructor
	public ExitCommand() {
		super(commandName, helpText, null);
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta salir del programa y desactiva la funciona pintar
	public boolean execute(Game myGame) {
		myGame.endGame();
		return false;
	}
	
	//Comprueba si el comando introducido es EXIT y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command end = null;
		if(commandWords[0].equals("E") || commandWords[0].equals("EXIT")) {
			if(commandWords.length == 1) {end = new ExitCommand();}
			else throw new CommandParseException("Exit command has no arguments");		
		}
		return end;
	}	
	
}