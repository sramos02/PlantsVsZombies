package p3.gameLogic.print;

import p3.PlantsVsZombies;
import p3.gameLogic.Game;

public class ReleasePrinter extends BoardPrinter{
	
	//Constructor
	public ReleasePrinter(String[][] board, String information) {
		super(board, information);
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Codifica el juego en un gran String[][]
	public void encodeGame(Game game) {
		
		for(int i = 0; i < PlantsVsZombies.MAX_ROWS; i++) {
			for(int j = 0; j < PlantsVsZombies.MAX_COLUMNS; j++) {
				this.board[i][j] = game.getElement(i, j); 
			}
		}

	}
	
	//Crea el tablero y muestra en que modo pintara el juego (game)
	public String printGame(Game myGame) {		
		encodeGame(myGame);
		information = myGame.gameInformation();
		return (this.boardToString(myGame, 7, PlantsVsZombies.MAX_COLUMNS, PlantsVsZombies.MAX_ROWS, false));
	}
}