package p3.gameLogic.commandTypes;

import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;

public class AddCommand extends Command {
	static String commandName = "ADD";
	static String helpText = "Adds a plant in position x, y.";
	static String[] commandHelp;

	private int x, y;
	private String planType;
	
	//Constructor1
	public AddCommand() {
		super(commandName, helpText, commandHelp);
	}

	//Constructor2
	public AddCommand(String planType, int x, int y) {
		super(commandName, helpText, commandHelp);
		this.planType = planType;
		this.x = x;
		this.y = y;
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta anhadir planta y activa la funciona pintar
	public boolean execute(Game myGame) throws CommandExecuteException{
		boolean done;	
		if(this.planType != "TORCH") {done = myGame.addPlant(planType, x, y);}
		else {done = myGame.addTorch(x, y);}
		return done;
	}

	//Comprueba si el comando introducido es ADD y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException {
		Command add = null;
		if(commandWords[0].equals("A") || commandWords[0].equals("ADD")) {
			if(commandWords.length == 4) {
				commandWords[1] = commandWords[1].toUpperCase();
				if (!(commandWords[1].toUpperCase().equals("P") || commandWords[1].toUpperCase().equals("S") ||
						   commandWords[1].toUpperCase().equals("W") || commandWords[1].toUpperCase().equals("C") || 
						   commandWords[1].toUpperCase().equals("PEASHOOTER") || commandWords[1].toUpperCase().equals("SUNFLOWER") ||
						   commandWords[1].toUpperCase().equals("WALLNUT") || commandWords[1].toUpperCase().equals("CHERRYBOMB") || commandWords[1].toUpperCase().equals("TORCH"))) {
					throw new CommandParseException("Unknown plant name " + commandWords[1] + ", try command List");
				}
				else { 
					add = new AddCommand(commandWords[1], Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3])); }
			}
			else throw new CommandParseException("Incorrect number of arguments for add command: Add <Plant> <y> <x>");
		}
		return add;
	}
	
	
}