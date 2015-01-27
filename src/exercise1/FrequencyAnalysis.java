package exercise1;

import Helpers.CharacterCount;

public class FrequencyAnalysis {
	public static void main(String[] args){
		
		String bookFile = "sherlock.txt";
		String cipherFile = "ciphertext.txt";
		
		
		/*
		 * CharacterCount takes in a given file.
		 * Reads the file and creates a ordered array of
		 * characters where the first character is the most
		 * common letter from that file and the last letter is 
		 * the least common letter within that file
		 */
		CharacterCount counter = new CharacterCount(bookFile);
		CharacterCount cipherCounter = new CharacterCount(cipherFile);
		
		Decryptor decrypt = new Decryptor(counter.getFreqArray(), cipherCounter.getFreqArray(), cipherFile);
	}

}
