package odbg.org.libs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author orlando
 *
 * @version 1.2
 */

public class Security {

	public static String getMD2( String text ) {
		return Security.criptMsg(text, "MD2");
	}
	
	public static String getMD4( String text ) {
		return Security.criptMsg(text, "MD4");
	}
	
	public static String getMD5( String text ) {
		return Security.criptMsg(text, "MD5");
	}
	
	public static String getSHA1( String text ) {
        return Security.criptMsg(text, "SHA1");
	}
	
	private static String criptMsg( String text, String security ) {
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance(security);
			byte[] buffer = text.getBytes();
			md.update(buffer);
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return Security.bytesToHex(digest);
	}
	
	private static String bytesToHex( byte[] bytes ) {
		StringBuilder hex = new StringBuilder();
		for (byte aux : bytes) {
			int b = aux & 0xff;
			String hexTemp = Integer.toHexString(b);
			
			if (hexTemp.length() == 1) 
				hex.append("0");
			hex.append(hexTemp);
		}
		
		return hex.toString();
	}
	
}
