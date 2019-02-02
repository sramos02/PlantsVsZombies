package p3.gameLogic.gameManager.auxiliar;

import java.nio.file.Paths;
import java.nio.file.InvalidPathException;


public class MyStringUtils {
	
	//Constructor
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
				result += elmnt;
		}
		return result;
	}
	
	//--------------FUNCIONES DEL PROGRAMA--------------------
	public static String centre(String text, int len){
		String out = String.format(" %"+len+"s %s %"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		return out.substring((int)start, (int)end);
	}
	
	// returns true if string argument is a valid filename
	public static boolean isValidFilename(String filename) {
		try {
			Paths.get(filename);
			return true;
		} catch (InvalidPathException ipe) {
			return false;
		}
	}


}

