package p3.gameLogic.commandTypes;

import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public abstract class Command {
	protected final String commandName;
	private String commandText;
	private String[] helpText;
	private String printHelp;
	
	//Constructor1
	public Command(String commandTex, String commandTextHelp, String[] helpInfo){
		this.commandName = commandTex;
		this.commandText = commandTextHelp;
		this.helpText = helpInfo;
	}	
	
	//Constructor2 (Para la funcion pintar, para no tener que pasar un array sin necesitarlo)
	public Command(String commandTex, String commandTextHelp, String helpInfo){
		this.commandName = commandTex;
		this.commandText = commandTextHelp;
		this.printHelp = helpInfo;
	}	

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta la accion propuesta y hace un set de pintar el tablero
	public abstract boolean execute(Game game) throws CommandExecuteException;	
	
	//Comrueba si el comando introducido es correcto y en ese caso lo crea
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	//Crea una cadena para poder mostrar en la ayuda de los comandos
	public String helpText(){return commandName + ": " + commandText;}
}