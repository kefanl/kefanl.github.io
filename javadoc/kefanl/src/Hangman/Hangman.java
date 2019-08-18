package Hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



/**
 * This class is the main class that implements the traditional and evil version of hangmangame
 * @author Kevin Long
 *
 */
public class Hangman {
	
	
	// This class will be instantiated only when the computer has generate a word list that contains at least one word
	// This class will read gameSet and length of word
	ArrayList<String> testSet;
	int wordLength;
	
	String guessedChars;
	
	// Create three new map for count word frequency in different location and controls the print content
	Map<Integer, Boolean> selectOrNot;	
	Map<Integer, Character> selectPrint;
	Map<Integer,Integer> selectFrequency;
	
	
	/**
	 * Create a constructor of Hangman Game
	 * @param words
	 * @param wordLength
	 */
	public Hangman(ArrayList<String> words, int wordLength) {	
		this.testSet = words;
		this.wordLength = wordLength;
		
		
		this.selectOrNot = new HashMap<Integer, Boolean>();
		this.selectPrint = new HashMap<Integer, Character>();
		this.selectFrequency = new HashMap<Integer, Integer>();	
		this.guessedChars = "";
		
		// Pupulates the three Maps with default (key-value) pairs
		// To make latter comparisons easier, pupulates the Maps with lenghth+1 (key-value) pairs, and the last pair represents the group that the character doesn't locate
		// at any loctions
		for(int i = 0; i <= this.wordLength; i++) {
			this.selectOrNot.put(i, false);
			this.selectPrint.put(i, '-');
			this.selectFrequency.put(i, 0);
		}
	}
	
	
	/**
	 * This method uses the guessed char to divide groups of words in the word group by updating the word frequency map
	 * @param a
	 */
	public void updateMap(char a) {
		this.guessedChars += a;
		for (String word: this.testSet) {
			int j = 0;
			for(int i = 0; i < this.wordLength; i ++ ) {
				if(word.charAt(i) == a) {
					this.selectFrequency.replace(i, this.selectFrequency.get(i), this.selectFrequency.get(i)+1);
					j += 1;
				}
			}			
			// If the word has no character a, then it updates the map on location wordlength
			if(j == 0) {
				this.selectFrequency.replace(this.wordLength, this.selectFrequency.get(this.wordLength), (this.selectFrequency.get(this.wordLength)+1));
			}			
		}
	}
	
	
	/**
	 * This method gets the maximum frequency for the map
	 * @param a
	 */
	public int getMaxFrequency() {			
		// Create a new boolean that has the default value false, which indicates the state that the word that user expects to be within are actually not in the largest group(s) 		
		// TO-DO: Get Max Frequency and the position that has not selected
		int maxFrequency = 0;			
		// Iterate through all positions and find maximum position frequency
		for(int i = 0; i <= this.wordLength; i++) {
			if ((this.selectFrequency.get(i) > maxFrequency) && (this.selectOrNot.get(i) == false)) {
				maxFrequency = this.selectFrequency.get(i);
			}
		}
		return maxFrequency;
	}
	
	
	/**
	 * This method updates the Map pair based on maxfrequency
	 * @param a
	 */
	public ArrayList<Integer> updatePosition(char a) {
		ArrayList<Integer> hasposition = new ArrayList<Integer>();
		// Loop through the SelectionFrequency Map and find if the max group(s) is(are) the group(s) containing the word the user guesses 
		for (int i = 0; i < this.wordLength; i ++) {
			if ((this.selectFrequency.get(i) >= this.getMaxFrequency()) && (this.selectOrNot.get(i) != true) && (this.getMaxFrequency() != 0)) {
				hasposition.add(i);
			}
		}
		return hasposition;
	}
	
	
	/**
	 * This method update print method and select method
	 * @param a
	 */
	public void updatePrint(char a) {
		ArrayList<Integer> hasposition = this.updatePosition(a);
			
		// If 2 or more locations has the maximum frequency and the word list is over 1, randomly select one position for updating
		if ((hasposition.size() >= 2) && (this.testSet.size() != 1)) {
			Random rand = new Random();
			int j = rand.nextInt(hasposition.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(hasposition.get(j));
			hasposition = temp;
		}		
		// Update the game set if necessary
		if (hasposition.size()!=0) {
			ArrayList<String> tempSet = new ArrayList<String>();
			for(String word: this.testSet) {
				// System.out.println(word);
				for(int position: hasposition) {
					if(word.charAt(position) == a) {	
						tempSet.add(word);
						// This breaks ensure that a word with multiple a characters will not be added repeatedly 
						break;
					}
				}
			}			
			this.testSet = tempSet;
			// System.out.println("After selection the size is: "+ this.testSet.size());
		} else {
			System.out.println("Incorrect guesses: [" + a + "]");
		}
		
		//Update other two Map based on position and designed selection
		for (int position: hasposition) {			
			this.selectOrNot.replace(position, false, true);
			this.selectPrint.replace(position, '-', a);								
		}
		
	}
	
	
	/**
	 * This method initialize the word frequency map
	 */
	public void reInitialize() {
		this.selectFrequency = new HashMap<Integer, Integer>();
		for(int i = 0; i <= wordLength; i ++) {
			this.selectFrequency.put(i, 0);
		}	
	}
	
	/**
	 * This method record the character that are recorded and check if user has typed a character that has been typed
	 * @param a
	 */
	public void remindRepeatance(char a) {
		for(int i = 0; i < this.guessedChars.length();i++) {
			if (a == this.guessedChars.charAt(i)) {
				System.out.println("You have just guessed a repeated words. This may be time-wasting if you are running a traditional game.");
				break;
			}
		}
		
	}
	
	/**
	 * Check if all positions in a word are guessed
	 * @return
	 */
	public boolean guessAll() {
		boolean notOver = false;
		for(int i = 0; i < this.wordLength; i ++) {
			if(this.selectOrNot.get(i) == false) {
				notOver = true;
				}
		}
		return !notOver;
	}
	
	/**
	 * This method print the word based on the state of the word being guessed or not
	 */
	public String print() {
		String s = "";
		for(int i = 0; i < this.wordLength; i ++) {
			s += this.selectPrint.get(i);
		}
		return s;
	}		
}
