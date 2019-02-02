package p3.gameLogic.commandTypes;

import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public class PrintModeCommand extends Command {
	static String commandName = "PRINTMODE";
	static String helpText = "Change print mode [Release, Debug].";
	static String mode; 
	
	//Constructor1
	public PrintModeCommand() {
		super(commandName, helpText, mode);
	}
	
	//Constructor2
	public PrintModeCommand(String mode) {
		super(commandName, helpText, mode);
		this.mode = mode;
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta cambiar el modo de pintado y desactiva la funciona pintar
	public boolean execute(Game game) throws CommandExecuteException{
		game.setPrintMode(mode);
		System.out.println("Print mode change to " + this.mode);
		return false;
	}

	//Comprueba si el comando introducido es PRINTMODE y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command print = null;
			if((commandWords[0].equals("P")) || commandWords[0].equals("PRINTMODE")) {
				if((commandWords.length == 2)) {
					commandWords[1] = commandWords[1].toUpperCase();
					if((commandWords[1].equals("DEBUG")) || (commandWords[1].equals("RELEASE"))){print = new PrintModeCommand(commandWords[1]);} 
					else throw new CommandParseException("Incorrect type of printmode: <Release/Debug>");
			}
			else throw new CommandParseException("Incorrect number of arguments for printmode command: PrintMode <mode>");
		}
		return print;
	}
	
}