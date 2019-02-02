package p3.gameLogic.print;

import p3.gameLogic.Game;
import p3.gameLogic.gameManager.auxiliar.MyStringUtils;

public abstract class BoardPrinter implements GamePrinter {
	protected String[][] board;
	protected String information;
	
	//Constructor
	public BoardPrinter(String[][] board, String information) { 
		this.board = board;
		this.information = information;
	}

	//--------------FUNCIONES DEL PROGRAMA--------------------
	//Clase abstracta que codificará el juego y creará tableros para mostrar posteriormente
	public abstract void encodeGame(Game myGame); 

	//Pinta el tablero dependiendo del modo de pintado
	public String boardToString(Game myGame, int cellSize, int maxColumns, int maxRows, boolean isDebug){	
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String space = " ";
		StringBuilder str = null;

		String margin = MyStringUtils.repeat(space, marginSize);
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (maxColumns * (cellSize + 1)) - 1);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		str = new StringBuilder();
		str.append(information);
		str.append(lineDelimiter);

		if(!isDebug) {
			for(int i = 0; i < maxRows; i++) {
				str.append(margin).append(vDelimiter);	 
				for (int j = 0 ; j < maxColumns; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
			}	
		}
		else {
			for (int i = 0 ; i < maxColumns; i++) {
				str.append( MyStringUtils.centre(board[i][0], cellSize)).append(vDelimiter);
			}
			str.append(lineDelimiter);
		}
		return str.toString();
	}
}