package Exercise3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import Helpers.StaticHelpers;

public class KeyGeneration {
	
	public static void GenerateKeyToFile(int length) throws IOException{
		FileOutputStream stream = null;
		File file = new File("exercise3/key.txt");
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		stream = new FileOutputStream(file);
		
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[length];
		random.nextBytes(bytes);
		stream.write(bytes);

		stream.close();
	}
	
	public static void setKey(String hex) throws IOException{
		System.out.println("Setting Key to Hex");
		FileOutputStream stream = null;
		File file = new File("exercise3/key.txt");
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		stream = new FileOutputStream(file);
		stream.write(StaticHelpers.hexStringToByteArray(hex));
		stream.close();
	}
	
	public static byte[] getEncyptionKey(int offset, int length) throws IOException{
		FileInputStream stream = null;
		File file = new File("exercise3/key.txt");
		stream = new FileInputStream(file);
		byte[] input = new byte[length];
		stream.read(input, offset, length);
		stream.close();
		return input;
	}
}
