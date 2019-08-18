package Hangman;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class mainly takes in the arraylist of strings that have already deleted leading or trailing whitespaces and returns
 * the "pure" ArrayList of words for users to guess.
 * @author Kevin Long
 *
 */
public class HangmanParsing {
		
	private ArrayList<String> lines;
		
	public HangmanParsing(ArrayList<String> lines) {
		this.lines = lines;
	}
	/**
	 * Iterate through all lines of words and delete leading or trailing whitespaces.
	 * Therefore the word list will be more efficiently filtered.
	 * Since the 
	 * @param 
	 * @return pure word list that contains no uppercase, abbreviation, apostrophe, hyphen, compound and digits
	 */
	public ArrayList<String> matchCleaning() {
		
		// Create a regex pattern(since I don't know how to match the "-", I replace it with whitespace)
		String regex = "[A-Z0-9.'\\s]";
		
		
		Pattern p = Pattern.compile(regex);
		
		// Create an ArrayList of strings and populate it with words that meet none of the pattern words in the pattern string
		ArrayList<String> newlines = new ArrayList<String>();
		for (String line: this.lines) {
			line.replace("-", " ");
			Matcher m = p.matcher(line);
			if (!m.find()) {
				
				// Add words into the ArrayList
				newlines.add(line);
			}
		}
		return newlines;
	}
	
}
