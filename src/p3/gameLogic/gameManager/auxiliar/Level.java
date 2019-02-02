package p3.gameLogic.gameManager.auxiliar;

public enum Level {
    
	EASY("easy", 3, 0.1), HARD("hard", 5, 0.2), INSANE("insane", 10, 0.3);

    private String dificulty;
    private double frequency;
    private int maxZombies;

    //Constructor
    private Level(String level, int zombies, double freq){
    	this.dificulty = level;
    	this.maxZombies = zombies;
    	this.frequency = freq;
    }

	//--------------FUNCIONES DEL PROGRAMA--------------------
    public static Level fromString(String lev) {
        for (Level level : Level.values()) {
            if (level.dificulty.equals(lev))
                return level;
        }
        return null;
    }
    
    public String getDificulty() {
    	return this.dificulty;
    }
    
    public int getMaxZombies() {
    	return this.maxZombies;
    }
    
    public double getFrequency() {
    	return this.frequency;
    }
	
	public static Level parse(String inputString) {
		for (Level level : Level. values() )
		if (level . name().equalsIgnoreCase(inputString)) return level;
		return null;
		}
    
    

}