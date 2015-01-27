package Exercise3;

public class OneTimePad {
	
	public static void main(String args[]){		
		try {
			
			/*
			 * Uncomment the following to check that my encryption works out the same
			 * as that given in the exercise
			 */
			/*
			KeyGeneration.setKey("6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2");
			OneTimePadEncryption.encrypt("exercise3/silverlining.txt", "exercise3/onetimepadencryption.txt");
			OneTimePadEncryption.encrypt("exercise3/onetimepadencryption.txt", "exercise3/onetimepadDecryption.txt");
			System.out.println(StaticHelpers.getHexOfFile("exercise3/onetimepadencryption.txt"));
			System.out.println("28b14ab7ecc33ea157b539ea426c5e9def0d81627eed498809c17ef9404cc5".toUpperCase());
			*/
			
			//Generate a random key
			System.out.println("Generating Key...");
			KeyGeneration.GenerateKeyToFile(2000000);
			System.out.println("Done");
			
			//Encrypt first param and write output to second param
			System.out.println("Encrypting File...");
			OneTimePadEncryption.encrypt("sherlock.txt", "exercise3/onetimepadencryption.txt");
			System.out.println("Done");
			
			//Decrypt first param and write to second param
			System.out.println("Decrypting File...");
			OneTimePadEncryption.encrypt("exercise3/onetimepadencryption.txt", "exercise3/onetimepadDecryption.txt");
			System.out.println("Done");		
			
			//Run One Time Pad Attack
			//OTPAttack.runOTPAttack();
			
			
			//Friends Files
			//Will need to set the key in KeyGeneration getEncyptionKey() to friend/otp
			//OneTimePadEncryption.encrypt("friend/EncryptedText", "friend/friendDecryption.txt");
		} catch (Exception e) {
			System.out.println("Exception - May be due to missing file");
			e.printStackTrace();
		}
	}
}
