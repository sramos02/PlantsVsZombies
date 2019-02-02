package p3.gameLogic.commandTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.exceptions.FileContentsException;
import p3.gameLogic.Game;
import p3.gameLogic.gameManager.auxiliar.MyStringUtils;

public class LoadCommand extends Command{
	static String commandName = "LOAD";
	static String helpText = "Load the state of the game from a file.";
	static String[] commandHelp;

	private String fileName;
	
	//Constructor
	public LoadCommand() {
		super(commandName, helpText, commandHelp);
	}
	//Constructor2
	public LoadCommand(String fileName) {
		super(commandName, helpText, commandHelp);
		this.fileName = fileName;
	}
		

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Busca el comando introducido y lo crea si existe uno que coincida, en caso contrario salta un error
	public boolean execute(Game myGame) throws CommandExecuteException {
		if (MyStringUtils.isValidFilename(fileName)) {
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName + ".dat"));
				br.readLine(); //Cabecera
				br.readLine(); //Salto de linea
				myGame.load(br);
				br.close();
				System.out.println("Game successfully loaded from file:" + fileName + "\n");
			}	
			catch(IOException e) {
				throw new CommandExecuteException("File couldn't be readed" + "\n");
			}
			catch(FileContentsException e) {
				throw new CommandExecuteException("File must be corrupted: " + e + "\n");
			}
			catch(NumberFormatException ex) {
				throw new CommandExecuteException(ex.getMessage() + " this letter must be a number" + "\n");
			}
			
		}
		return true;
	}

	//Comprueba si el comando introducido es LOAD y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command load = null;
		if((commandWords[0].equals("LO")) || (commandWords[0].equals("LOAD"))) { 
			if(commandWords.length == 2) {
				load = new LoadCommand(commandWords[1]); 
			}
			else throw new CommandParseException("Incorrect number of arguments for load command: Load <FileName>");	
		}
		return load;
	}

}
