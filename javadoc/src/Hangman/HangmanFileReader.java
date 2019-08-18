package Hangman;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class mainly handle the I/O and 
 * catches exceptions as it may throws out sometimes
 * @author Kevin Long
 *
 */
public class HangmanFileReader {
	
	
	// This method only has one attribute 
	private String filename;
	
	// Create a constructor
	public HangmanFileReader(String filename) {
		this.filename = filename;
	}
	
	
	// Read file from computer I/O system
	public ArrayList<String> getCleanContext() {
		
		
		// Create a new ArrayList to store the strings without leading or trailing whitespaces
		ArrayList<String> lines = new ArrayList<String>();				
		try {
			
			// Create file reader and bufferedReader to read file
			File file = new File(this.filename);
			FileReader myReader = new FileReader(file);
			BufferedReader bfReader = new BufferedReader(myReader);
			
			String line = bfReader.readLine();
			
			// When the file has at least one line that is not read my the buffered reader, read ana trims the Line
			while(line != null) {
				
				// Call trim() method to delete leading or trailing whitespaces
				line = line.trim();
				
				// Pay attention that some lines may only contains pure whitepaces, do not add them
				if(!line.isEmpty()) {
					lines.add(line);
				}
				line = bfReader.readLine();
			}
			
			// Close file reader and buffered reader after reading all lines
			bfReader.close();
			myReader.close();
			
			
		} catch (FileNotFoundException e) { // Catch necessary exceptions
			e.printStackTrace();
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
		
		// return the ArrayList<String> to the user
		return lines;
	}
	
}
