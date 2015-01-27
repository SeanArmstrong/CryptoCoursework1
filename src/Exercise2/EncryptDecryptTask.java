package Exercise2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Helpers.CharacterCount;
import Helpers.StaticHelpers;

public class EncryptDecryptTask {

	private char[] keyArray;
	private int[] shiftArray;
	private int keyIndex = 0;
	private char[] alphaArray = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public EncryptDecryptTask(){
		System.out.println("Ask the user for a key");
		setKey();
		
		System.out.println("\nSet a shift array for the given key where a = 0, b = 1");
		setShiftArray();
		
		String fileToEncrypt = "sherlockSmall.txt";
	
		System.out.println("\nEncrypting File: " + fileToEncrypt + "...");
		
		String encryptedFile = encryptDecryptFile(fileToEncrypt, true);
		
		System.out.println("Done");
		
		System.out.println("\nPrinting to file exercise2/encryptedFile.txt");
		StaticHelpers.writeToFile(encryptedFile, "exercise2/encryptedFile.txt");
		
		System.out.println("\nCharacter Count on encrypted file");
		CharacterCount encryptedCount = new CharacterCount("exercise2/encryptedFile.txt");
		StaticHelpers.printSortedMap(encryptedCount.getSortedCharacterTally());
		
		System.out.println("\nDecrypting the file encryptedFile.txt...");
		String decryptedFile = encryptDecryptFile("exercise2/encryptedFile.txt", false);
		System.out.println("Done");
		
	}
	
	private String encryptDecryptFile(String file, boolean encrypting){
		try {
			FileReader reader = new FileReader(file);
			Scanner file_input = new Scanner(reader);
			String result = "";
			keyIndex = 0;
	
			//For each line
			while(file_input.hasNextLine()){
				//For each character
				for( char character : file_input.nextLine().toLowerCase().toCharArray()){
					//So long as the character is a letter
					if (Character.isLetter(character)){	//(abc)
						int position = StaticHelpers.findPositionInArray(character, alphaArray);
						if(position >= 0){
							if(encrypting){
								position = position + shiftArray[keyIndex];
							} else { //Decrypting
								position = position - shiftArray[keyIndex];
								if (position < 0){
									position = position + 26;
								}
							}
							result = result + alphaArray[position%26];
							increaseIndex(); //Only increase when acceptable character
						} else {
							// Accent Character
							result = result + character;
						}
					} else { //(.,!")
						//else return the character as it was
						result = result + character;
					}
				}
				result = result + "\n";
			}
			// Print deciphered text
			file_input.close();
			return result;
		} catch (IOException e) {
			System.out.println("File not found");
			return "File Not Found";
		}
	}
	
	private void setKey(){
		Scanner user_input = new Scanner (System.in);
		String key = "";
		while(!isAlpha(key)){
			System.out.print("\nEnter the Key containing only letters\n");
			key = user_input.next().toLowerCase();
		}
		user_input.close();
		System.out.println("Key:" + key);
		keyArray = key.toCharArray();
	}
	
	private void setShiftArray(){
		shiftArray = new int[keyArray.length];
		for( int i = 0; i < keyArray.length; i++ ){
			int temp = (int) keyArray[i];
			temp = temp - 97;
			shiftArray[i] = temp;
		}
		System.out.println("Shift Array: " + StaticHelpers.oneDArrayString(shiftArray));
	}
	
	private void increaseIndex(){
		if (keyArray.length-1  == keyIndex){
			keyIndex = 0;
		} else {
			keyIndex++;
		}
	}
	
	private boolean isAlpha(String key) {
	    return key.matches("[a-zA-Z]+");
	}
}
