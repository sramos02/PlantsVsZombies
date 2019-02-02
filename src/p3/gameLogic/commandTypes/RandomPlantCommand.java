package p3.gameLogic.commandTypes;

import java.util.Random;

import p3.PlantsVsZombies;
import p3.exceptions.CommandExecuteException;
import p3.exceptions.CommandParseException;
import p3.gameLogic.Game;
import p3.rols.plants.PlantFactory;

public class RandomPlantCommand extends Command{
	static String commandName = "RPLANT";
	static String helpText = "Adds a random plant in position x, y.";
	static String[] commandHelp;

	private int x, y;
	
	//Constructor1
	public RandomPlantCommand() {
		super(commandName, helpText, commandHelp);
	}

	//Constructor2
	public RandomPlantCommand(int x, int y) {
		super(commandName, helpText, commandHelp);
		this.x = x;
		this.y = y;
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Ejecuta anhadir planta y activa la funciona pintar
	public boolean execute(Game myGame) throws CommandExecuteException{
		boolean done;
		Random rand = new Random();
		
		String plants[] = availablePlants();
		int a = rand.nextInt(4);
		done = myGame.addPlant(plants[a], x, y);
		return done;
	}
	
	
	
	
	public String[] availablePlants(){
		String plants[] = new String[PlantsVsZombies.MAX_PLANTS];
		
		for(int i = 0; i < PlantsVsZombies.MAX_PLANTS; i++) {
			plants[i] = "";
		}

		String aux = PlantFactory.listOfAvilablePlants();				//LEO LA LISTA ENTERA
		String aux2[] = aux.trim().split("\n");							//LA SEPARO POR PLANTAS
													
		for(int i = 0; i < 4; i++) {  									
			String aux3[] = aux2[i].trim().split("\\s+");				//ME QUEDO SOLO CON EL NOMBRE DE CADA PLANTA
			String a = aux3[0].substring(0, aux3[0].length() - 1);		//LE QUITO LOS DOS PUTOS PUNTOS DEL FINAL
			plants[i]= a; 												//LA METO EN UN ARRAY DE STRINGS
		}
		return plants;
		
	}

	//Comprueba si el comando introducido es ADD y en ese caso si es correcto, entonces crea el comando
	public Command parse(String[] commandWords) throws CommandParseException {
		Command add = null;
		if(commandWords[0].equals("RP") || commandWords[0].equals("RPLANT")) {
			if(commandWords.length == 3) {
				add = new RandomPlantCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2])); }
			else throw new CommandParseException("Incorrect number of arguments for add command: Rplant <y> <x>");
		}
		return add;
	}
	
	
}