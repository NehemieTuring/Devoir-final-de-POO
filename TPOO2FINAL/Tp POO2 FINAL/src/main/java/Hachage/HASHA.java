package Hachage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HASHA {
    public static String hashSHA(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes());
            StringBuilder hexString  = new StringBuilder();
            for(byte b : encodedHash){
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
