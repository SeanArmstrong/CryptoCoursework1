package Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class CharacterCount {
	
	private char[] freqArray = new char[26];
	private TreeMap<Character,Integer> sortedCharacterTally;

	public CharacterCount(String file){
		
		// Map characters to a integer value
		HashMap<Character, Integer> characterTally = new HashMap<Character, Integer>();
		
		// Comparator to compare TreeMap by value instead of Key
		TreeValueComparator treeValueComp =  new TreeValueComparator(characterTally);
	    sortedCharacterTally = new TreeMap<Character,Integer>(treeValueComp);

		try{
			//new file reader to take in information from a given document
			FileReader reader = new FileReader(file);
			Scanner input = new Scanner(reader);

			while(input.hasNextLine()){
				for( char character : getStrippedLine(input.nextLine())){
					Integer givenCharacter = characterTally.get(character);
					if (givenCharacter == null) {
				        givenCharacter = 0;
				    }
				    characterTally.put(character, givenCharacter + 1);
				}			
			}		
			input.close();
		}catch(IOException e){
			//error if the file cannot be read
			System.out.println("Error, File Not Found");
		}
		
		sortedCharacterTally.putAll(characterTally);
		
		//Print out sorted map
		//System.out.println("Printing Map");
		//StaticHelpers.printSortedMap(sortedCharacterTally);
		
		//Set freq array
		convertMapToArray(sortedCharacterTally);
	}
	
	public CharacterCount(char[] array){
		
		// Map characters to a integer value
		HashMap<Character, Integer> characterTally = new HashMap<Character, Integer>();
		
		// Comparator to compare TreeMap by value instead of Key
		TreeValueComparator treeValueComp =  new TreeValueComparator(characterTally);
	    sortedCharacterTally = new TreeMap<Character,Integer>(treeValueComp);

	    //new file reader to take in information from a given document
			for( char character : array){
				Integer givenCharacter = characterTally.get(character);
				if (givenCharacter == null) {
			        givenCharacter = 0;
			    }
			    characterTally.put(character, givenCharacter + 1);
			}			
					
		
		sortedCharacterTally.putAll(characterTally);
		
		//Print out sorted map
		//printSortedMap(sortedCharacterTally);
		
		//Set freq array
		convertMapToArray(sortedCharacterTally);
	}
	
	public TreeMap<Character, Integer> getSortedCharacterTally() {
		return sortedCharacterTally;
	}
	
	public int getTotalCharacters(){
		int total = 0;
		for (int letterCount : sortedCharacterTally.values()){
			total = total + letterCount;
		}

		return total;
	}

	public char[] getFreqArray() {
		return freqArray;
	}
	
	private char[] convertMapToArray(Map<Character, Integer> map){
		char[] array = new char[map.keySet().size()];
		int index = 0;
		for(Character element : map.keySet()){
			freqArray[index++] = element.charValue();
		}
		return array;
	}
	

	
	private char[] getStrippedLine(String nextLine){
		return nextLine.replaceAll("[^A-Za-z]", "").toLowerCase().toCharArray();
	}

}
