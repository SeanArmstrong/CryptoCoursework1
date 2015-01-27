package exercise1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Helpers.StaticHelpers;

public class Decryptor {
	
	private char[] bookCounterArray;
	private char[] cipherCounterArray;
	private char[] alphaArray = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

			
	public Decryptor(char[] bookCounterArray, char[] cipherCounterArray, String file){
		System.out.println("Cipher Counter Array:");
		System.out.println(cipherCounterArray);
		System.out.println("Book Counter Array:");
		System.out.println(bookCounterArray);
		
		this.bookCounterArray = bookCounterArray;
		this.cipherCounterArray = cipherCounterArray;
		
		try{
			//new file reader to take in information from a given document
			FileReader reader = new FileReader(file);
			Scanner input = new Scanner(reader);
			String result1 = "";
			String result2 = "";
			String result3 = "";
			String result4 = "";

			//For each line
			while(input.hasNextLine()){
				//For each character
				for( char character : input.nextLine().toCharArray()){
					//So long as the character is a letter
					if (Character.isLetter(character)){	//(abc)
						result1 = result1 + method1(character); 
						result2 = result2 + method2(character);
						result3 = result3 + method3(character);
						result4 = result4 + method4(character);
					} else { //(.,!")
						//else return the character as it was
						result1 = result1 + character;
						result2 = result2 + character;
						result3 = result3 + character;
						result4 = result4 + character;
					}
				}
				result1 = result1 + "\n";
				result2 = result2 + "\n";
				result3 = result3 + "\n";
				result4 = result4 + "\n";

			}
			// Print deciphered text
			System.out.println("\nResult from method 1:\n" + result1);
			System.out.println("\nResult from method 2:\n" + result2);
			System.out.println("\nResult from method 3:\n" + result3);
			System.out.println("\nResult from method 4:\n" + result4);
			input.close();
		}catch(IOException e){
			//error if the file cannot be read
			System.out.println("Error, File Not Found");
		}
	}
	
	/*
	 * Method 1 - Replace letter position in the file 
	 * by the other letter in the same position in 
	 * the freq array from the book.
	 * e.g.
	 * E was the most common letter and T was the second.
	 * So A would be replaced with E and B would be replaced with T
	 */
	private char method1(char character){
		int temp = (int)character;
		temp = temp - 97;
		return bookCounterArray[temp];
	}
	
	/*
	 * From the cipher text I can see that there are 
	 * two letter that appear on their own
	 * It is likely that these are a's or i's
	 * Shifting e back 4 gives a and shifting m back
	 * 4 gives i.
	 * 
	 */
	private char method2(char character){
		int temp = StaticHelpers.findPositionInArray(character, alphaArray);
		if(temp-4 < 0){
			temp = temp + 26;
		}
		return alphaArray[(temp-4)%26];
	}
	
	/*
	 * Introduce the frequency array of the cipher.
	 * This method then maps the cipher to the book
	 * The result is a partially decoded cipher
	 */
	private char method3(char character){
		int position = StaticHelpers.findPositionInArray(character, cipherCounterArray);	
		return bookCounterArray[(position%26)];
	}
	
	/*
	 * Method 4 - Most Common Letter - Ceaser Cipher
	 * Shift position in alphabet by difference between
	 * the two frequency arrays
	 * e.g.
	 * The most common letter in the book was a 'e'
	 * The most common letter in the cipher was a 'i'
	 * The difference in position if 4
	 * Therefore move each letter 4 characters back
	 */
	private char method4(char character){
		int mostCommonBook = (int) bookCounterArray[0];
		int mostCommonCipher = (int) cipherCounterArray[0];
		int shift = mostCommonBook - mostCommonCipher;
		
		int position = StaticHelpers.findPositionInArray(character, alphaArray);
		position = position + shift;
		if(position < 0){
			position = position + 26;
		}
		return alphaArray[(position%26)];		
	}
}
