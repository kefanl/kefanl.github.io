package Hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing all methods in Hangman Class.
 * Baiscally I wrote two versions in a same structure, therefore only the game controller class will control
 * the version that user is playing.
 * @author Kevin Long
 *
 */
class HangmanTest {
	
	Hangman testHangman;
	
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<String> words = new ArrayList<String>();
		words.add("abe");
		words.add("abc");
		words.add("bce");
		words.add("bbd");
		words.add("ace");
		this.testHangman = new Hangman(words, 3);	
	}

	@Test
	void testHangman() {
		assertTrue(this.testHangman.wordLength == 3);
		assertTrue(this.testHangman.guessedChars.equals(""));
		assertTrue(this.testHangman.selectFrequency.get(0) == 0);
		assertTrue(this.testHangman.selectOrNot.get(0) == false);
		assertTrue(this.testHangman.selectPrint.get(0) == '-');
	}

	@Test
	void testUpdateMap() {
		this.testHangman.updateMap('a');
		assertTrue(this.testHangman.selectFrequency.get(0) == 3);
		assertTrue(this.testHangman.selectFrequency.get(1) == 0);
		assertTrue(this.testHangman.selectFrequency.get(2) == 0);
		assertTrue(this.testHangman.selectFrequency.get(3) == 2);
	}

	@Test
	void testGetMaxFrequency() {
		assertTrue(this.testHangman.getMaxFrequency() == 0);
		this.testHangman.updateMap('a');
		assertTrue(this.testHangman.getMaxFrequency() == 3);
	}

	@Test
	void testUpdatePosition() {
		this.testHangman.updateMap('a');
		ArrayList<Integer> s = this.testHangman.updatePosition('a');
		assertTrue(s.get(0) == 0);
	}

	@Test
	void testUpdatePrint() {
		this.testHangman.updateMap('a');
		this.testHangman.updatePrint('a');
		assertTrue(this.testHangman.selectOrNot.get(0) == true);
		assertTrue(this.testHangman.selectPrint.get(0) == 'a');
	}

	@Test
	void testReInitialize() {
		assertTrue(this.testHangman.selectFrequency.get(0) == 0);
		this.testHangman.updateMap('a');
		assertTrue(this.testHangman.selectFrequency.get(0) == 3);
		this.testHangman.reInitialize();
		assertTrue(this.testHangman.selectFrequency.get(0) == 0);
	}

	@Test
	void testRemindRepeatance() {
		assertTrue(this.testHangman.guessedChars.equals(""));
		this.testHangman.updateMap('a');
		assertTrue(this.testHangman.guessedChars.charAt(0) == 'a');
		this.testHangman.updateMap('b');	
		assertTrue(this.testHangman.guessedChars.charAt(1) == 'b');
	}

	@Test
	void testGuessAll() {
		assertTrue(this.testHangman.guessAll() == false);
		this.testHangman.updateMap('a');
		this.testHangman.updatePrint('a');
		this.testHangman.reInitialize();
		assertTrue(this.testHangman.guessAll() == false);
		this.testHangman.updateMap('b');
		this.testHangman.updatePrint('b');
		this.testHangman.reInitialize();
		assertTrue(this.testHangman.guessAll() == false);
		this.testHangman.updateMap('c');
		this.testHangman.updatePrint('c');	
		assertTrue(this.testHangman.guessAll() == true);
		
	}

	@Test
	void testPrint() {
		assertTrue(this.testHangman.print().equals("---"));
		this.testHangman.updateMap('a');
		this.testHangman.updatePrint('a');
		this.testHangman.reInitialize();
		assertTrue(this.testHangman.print().equals("a--"));
		this.testHangman.updateMap('b');
		this.testHangman.updatePrint('b');
		this.testHangman.reInitialize();
		assertTrue(this.testHangman.print().equals("ab-"));
		this.testHangman.updateMap('c');
		this.testHangman.updatePrint('c');	
		assertTrue(this.testHangman.print().equals("abc"));
	}

}
