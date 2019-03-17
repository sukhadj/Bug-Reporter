package security;

import java.util.*;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class SHA256 {

	public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter data for which SHA256 is required:");
        String data = sn.nextLine();
        
        SHA256 sj = new SHA256();
        String hash = sj.getSHA256Hash(data);
        System.out.println("The SHA256 (hexadecimal encoded) hash is:"+hash);
    }
	
	public String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

	private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
