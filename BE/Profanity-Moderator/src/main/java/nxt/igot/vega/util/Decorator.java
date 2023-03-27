package nxt.igot.vega.util;

public class Decorator {

	public static String enumToString(String enumText) {
		StringBuilder formattedText = new StringBuilder();
		 for (String word : enumText.split("_")){
			 formattedText.append(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ");
		 }
		 return formattedText.toString().trim();
	}
}
