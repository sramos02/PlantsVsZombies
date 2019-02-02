package p3.gameLogic.gameManager;

public class SunCoinManager {
	private int harvestedSuns;
	
	public SunCoinManager() {
		this.harvestedSuns = 50;
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Se encarga de recolectar todos los soles del juego
	public void sunHarvester(int newSuns) {
		
		this.harvestedSuns = this.harvestedSuns + newSuns;
	}
	
	//Devuelve un booleano diciendo si quedan soles suficientes para comprar una planta concreta
	public boolean thereAreSuns(int necessarySuns) {
		boolean ok = false;
		if(this.getHarvestedSuns() >= necessarySuns) {ok = true;}
		else {System.out.println("No tiene soles suficientes");}
		return ok;
	}
	
	//------------------------AUXILIAR----------------------------
	public int getSuns() {
		return getHarvestedSuns();
	}

	public int getHarvestedSuns() {
		return this.harvestedSuns;
	}

	public void setHarvestedSuns(int harvestedSuns) {
		this.harvestedSuns = harvestedSuns;
	}
	
	public void setSuns(int newSuns) {
		this.harvestedSuns = newSuns;
	}
	
}