package p3.gameLogic.commandTypes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;
import p3.gameLogic.gameManager.auxiliar.MyStringUtils;

public class SaveCommand extends Command {
	static String commandName = "SAVE";
	static String helpText = "Save the state of the game to a file.";
	static String[] commandHelp;
	private String fileName;
	
	//Constructor
	public SaveCommand() {
		super(commandName, helpText, commandHelp);
	}
	
	//Constructor2
	public SaveCommand(String fileName) {
		super(commandName, helpText, commandHelp);
		this.fileName = fileName;
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Busca el comando introducido y lo crea si existe uno que coincida, en caso contrario salta un error
	public boolean execute(Game myGame) throws CommandExecuteException{
					 
		if(MyStringUtils.isValidFilename(this.fileName)) {
			
			try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName +".dat") );
			myGame.store(bw);
			bw.flush();
			bw.close();
			System.out.println("Game successfully saved in file " + fileName + ".dat. Use the load command to reload it");

			}	
			catch(IOException e) {
				throw new CommandExecuteException("File error, objects couldn´t be saved properly");
			}
		
		}
		
		return false;
	}

	//Comprueba si el comando introducido es SAVE y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException{
		Command save = null;
		if((commandWords[0].equals("S")) || (commandWords[0].equals("SAVE"))) { 
			if(commandWords.length == 2) {
				commandWords[0].toUpperCase();
				save = new SaveCommand(commandWords[1]); 
			}
			else throw new CommandParseException("Incorrect number of arguments for save command: Save <FileName>");	
		}
		return save;
	}
}
