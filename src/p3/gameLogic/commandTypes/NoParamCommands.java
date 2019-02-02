package p3.gameLogic.commandTypes;

import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

//Clase que extiende a Command y llama a los comandos que no tienen parametros
 public abstract class NoParamCommands extends Command {	
	
	//Constructor
	public NoParamCommands(String commandTex, String commandTextHelp, String[] helpInfo) {
		super(commandTex, commandTextHelp, helpInfo);
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta la accion propuesta y hace un set de pintar el tablero
	public abstract boolean execute(Game game);
	
	//Comrueba si el comando introducido es correcto y en ese caso lo crea
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
}