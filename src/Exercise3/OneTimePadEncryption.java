package Exercise3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OneTimePadEncryption {

	public static void encrypt(String inputFile, String outputFile) throws IOException{
		FileInputStream stream = null;
		File file = new File(inputFile);
		stream = new FileInputStream(file);
		byte[] input = new byte[stream.available()];
		stream.read(input);
		stream.close();
		
		byte[] key = KeyGeneration.getEncyptionKey(0, input.length);
		
		byte[] resultBytes = new byte[input.length];
		for(int i = 0; i < input.length; i++){
			resultBytes[i] = (byte) (input[i] ^ key[i]);
		}
		
		writeEncryptionToFile(resultBytes, outputFile);
	}
	
	private static void writeEncryptionToFile(byte[] result, String outputFile) throws IOException{
		FileOutputStream stream = null;
		File file = new File(outputFile);
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		stream = new FileOutputStream(file);
		
		stream.write(result);
		
		stream.close();
	}
}
