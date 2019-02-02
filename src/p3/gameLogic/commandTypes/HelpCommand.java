package p3.gameLogic.commandTypes;

import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public class HelpCommand extends NoParamCommands{
	private static String commandName = "HELP";
	public static final String helpText = "Prints this help message.";
	
	//Constructor
	public HelpCommand() {
		super(commandName, helpText, null);
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta salir del programa y desactiva la funciona pintar
	public boolean execute(Game game) {
		System.out.println(CommandParse.commandHelp());
		return false;
	}

	//Comprueba si el comando introducido es HELP y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command help = null;
		if(commandWords[0].equals("H") || commandWords[0].equals("HELP")) { 
			if (commandWords.length == 1) {help = new HelpCommand(); }
			else throw new CommandParseException("Help command has no arguments");		
		}
		return help;
	}


}