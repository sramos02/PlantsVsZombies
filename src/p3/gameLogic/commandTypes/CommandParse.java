package p3.gameLogic.commandTypes;


import p3.exceptions.CommandParseException;

public abstract class CommandParse {

	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new NoneCommand(),
		new ExitCommand(),
		new ListCommand(),
		new PrintModeCommand(),
		new ResetCommand(),
		new SaveCommand(),
		new RandomPlantCommand(),
		new SunCoinCommand(),
		new LoadCommand()
	};
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Busca el comando introducido y lo crea si existe uno que coincida, en caso contrario salta un error
	public static Command parseCommand(String[ ] commandWords) throws CommandParseException{
		Command newCommand = null;
		boolean found = false;
		int i = 0;
		commandWords[0] = commandWords[0].toUpperCase();
		
		while((i < availableCommands.length) && !found) {
			if(availableCommands[i].parse(commandWords) != null) {newCommand = availableCommands[i].parse(commandWords); found = true;}
			else{i++;}
		}
		if(!found) {throw new CommandParseException("Unknown command. Use 'help' to see the available commands");}
		return newCommand;
		
	}
	
	//Crea la lista de ayuda para el jugador
	public static String commandHelp(){
		String help = "";
		for(int i = 0; i < availableCommands.length; i++) {
			help = help + availableCommands[i].helpText() + "\n";
		}
		return help;
	}


}