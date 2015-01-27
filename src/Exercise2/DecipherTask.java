package Exercise2;

import Helpers.CharacterCount;
import Helpers.StaticHelpers;

public class DecipherTask {
	
	private char[] alphaArray = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private char[][] multi;
	int[] shifts;
	
	public DecipherTask(){
		
		System.out.println("\nDecipher Task");

		System.out.println("List Index of Concidence for different key length");

		int keyLengthsToTry = 30;
		int bestKeyLength = findBestKeyLength(keyLengthsToTry);
		
		System.out.println("\nIndex " + bestKeyLength + " has the closest index of concidence to 0.065 which is the index of the english alphabet");
		System.out.println("\nGet 2D array from index of concidence for key length " + bestKeyLength + "\n");
		IndexOfCoincidence indexOfCoincidence = new IndexOfCoincidence("test.txt", bestKeyLength);
		multi = indexOfCoincidence.getMulti();
		
		//Frequency of each array
		System.out.println("Run a frequency analysis on each common to find the most common letter");
		System.out.println("Find the shift difference between the most common letter in the column most common english alphabet letter (e)");
		freqeuncyAnalysisOfEachColumn();
		System.out.println("Shift Array: " + StaticHelpers.oneDArrayString(shifts));
		
		System.out.println("\nPrint the resultant array taking the shift values into account\n");
		printResultantArray();
	}
	
	private int findBestKeyLength(int keyLengthsToTry){
		int bestKeyLength = 0;
		double closestIndex = 100.0;
		for(int i = 1; i <= keyLengthsToTry; i++){
			IndexOfCoincidence indexofconcidence = new IndexOfCoincidence("test.txt", i);
			double concidence = indexofconcidence.getCoincidence();
			System.out.println("Key Length: " + i + " Index: " + concidence);
			if(Math.abs(0.065 - concidence) < closestIndex){
				bestKeyLength = i;
				closestIndex = Math.abs(0.065 - concidence);
			}
		}
		
		return bestKeyLength;
	}
	
	private void freqeuncyAnalysisOfEachColumn(){
		int columnsCount = multi[0].length;
		shifts = new int[columnsCount];

		for(int i = 0; i < columnsCount; i++){
			
			char[] columnArray = new char[multi.length];
			for(int j = 0; j < multi.length; j++){
					columnArray[j] = multi[j][i];		
			}
			columnArray = StaticHelpers.trimWhiteSpace(columnArray);
			CharacterCount counter = new CharacterCount(columnArray);
			int mostCommonInCipher = (int) counter.getFreqArray()[0];
			int mostCommonLetter = (int) 'e';
			int shift = mostCommonLetter - mostCommonInCipher;
			shifts[i] = shift;
		}
	}
	
	private void printResultantArray(){
		String result = "";
		for(int i = 0; i < multi.length; i++){
			for(int j = 0; j < multi[i].length; j++){
				int temp = (int) multi[i][j];
				temp = temp - 97 + shifts[j];
				if(temp < 0 ){
					temp = temp + 26;
				}
				if(temp > 0){ // check for empty chars at end of 2d array
					result = result + alphaArray[temp%26];
				}
			}
			if(i % 50 == 0 && i != 0){
				result = result + "\n";
			}
		}
		System.out.println(result);
	}
}
