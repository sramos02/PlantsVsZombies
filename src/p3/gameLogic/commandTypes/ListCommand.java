package p3.gameLogic.commandTypes;

import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;
import p3.rols.plants.PlantFactory;

public class ListCommand extends NoParamCommands{
	private static String commandName = "LIST";
	public static final String helpText = "Prints the list of available plants.";
	
	//Constructor
	public ListCommand() {
		super(commandName, helpText, null);
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Busca el comando introducido y lo crea si existe uno que coincida, en caso contrario salta un error
	public boolean execute(Game myGame) {
		String a = PlantFactory.listOfAvilablePlants();
		System.out.println(a);
		return false;
	}

	//Comprueba si el comando introducido es LIST y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command list = null;
		if(commandWords[0].equals("L") || commandWords[0].equals("LIST")) { 
			if (commandWords.length == 1) {list = new ListCommand(); }
			else throw new CommandParseException("List command has no arguments");		
		}
		return list;
	}
	
}