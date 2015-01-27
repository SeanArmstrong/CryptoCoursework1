package Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StaticHelpers {
	
	final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static void writeToFile(String encryptedFile, String fileName) {
		try{
			PrintWriter writer = new PrintWriter(fileName);
			String[] encryptedWithNewLines = encryptedFile.split("\n");
			for( String string : encryptedWithNewLines){
				writer.write(string);
				writer.println();
			}
			writer.close();
		} catch (IOException e){
			System.out.println("Cannot write encryptedFile");
		}
	}
	
	public static int findPositionInArray(char character, char[] array){
		for(int i = 0; i < array.length; i++){
			if(character == array[i]){
				return i;
			}
		}
		return -1;		
	}
	
	public static void printSortedMap(Map<Character, Integer> map){
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	
	public static char[] trimWhiteSpace(char[] array){
		return new String(array).trim().toCharArray();
	}
	
	public static String oneDArrayString(int[] shiftArray){
		String result = "";
		for(int i = 0; i < shiftArray.length; i++){
			result = result + shiftArray[i] + ", ";
		}
		return result;
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] stringToByteArray(String message){
		return message.getBytes();
		
	}
	
	public static byte[] hexStringToByteArray(String key) {
	    int len = key.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(key.charAt(i), 16) << 4)
	                             + Character.digit(key.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static String getHexOfFile(String inputFile) throws IOException{
		FileInputStream stream = null;
		File file = new File(inputFile);
		stream = new FileInputStream(file);
		byte[] input = new byte[stream.available()];
		stream.read(input);
		stream.close();
		return bytesToHex(input);
	}
}
