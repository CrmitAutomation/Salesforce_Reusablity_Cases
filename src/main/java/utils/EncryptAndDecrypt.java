package utils;
import java.util.Base64;


public class EncryptAndDecrypt {
	 public static String decrypt(String encryptedText) {
	        return new String(Base64.getDecoder().decode(encryptedText));
	    }
}
