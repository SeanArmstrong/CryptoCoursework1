package Exercise2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Helpers.CharacterCount;
import Helpers.StaticHelpers;

public class IndexOfCoincidence {
	
	private String file;
	private int keySize;
	private char[][] multi;
	
	public IndexOfCoincidence(String file, int keySize){
		this.file = file;
		this.keySize = keySize;
		
		try{
			String line = readInFile();
			set2DArray(line);
			//printArray();
		}catch(IOException e){
			//error if the file cannot be read
			System.out.println("Error, File Not Found");
		}
	}
	
	public double getCoincidence(){
		int columnsCount = multi[0].length;
		double[] keys = new double[columnsCount];

		for(int i = 0; i < columnsCount; i++){
			
			char[] columnArray = new char[multi.length];
			for(int j = 0; j < multi.length; j++){
					columnArray[j] = multi[j][i];		
			}
			columnArray = StaticHelpers.trimWhiteSpace(columnArray);
			CharacterCount counter = new CharacterCount(columnArray);
			int totalCharacters = counter.getTotalCharacters();
			keys[i] = 0;
			for(int amount : counter.getSortedCharacterTally().values()){
				keys[i] = keys[i] + Math.pow((double) amount/totalCharacters, 2);
			}
		}
		
		// Average Keys
		double total = 0;
		for(int i = 0; i < keys.length; i++){
			total = total + keys[i];
		}
		return total / columnsCount;
	}
	
	public char[][] getMulti(){
		return multi;
	}
	
	private String readInFile() throws IOException{
		FileReader reader = new FileReader(file);
		Scanner input = new Scanner(reader);
		String line = "";
		while(input.hasNextLine()){
			line = line + input.nextLine();
		}
		input.close();
		return line;
	}
	
	private void set2DArray(String line){
		multi = new char[(line.length()/keySize) + 1][keySize];
		char[] charArray = line.toCharArray();
		for(int i = 0; i < charArray.length; i+=keySize){
			for(int j = 0; j < keySize && i+j < charArray.length; j++ ){
				multi[i/keySize][j] = charArray[i+j];
			}
		}
	}
	
	private void printArray(){
	   for(int i = 0; i < multi.length; i++){
	      for(int j = 0; j < multi[i].length; j++){
	         System.out.print(multi[i][j]);
	      }
	      System.out.println();
	   }
	}
}
