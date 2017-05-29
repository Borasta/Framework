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

	private final static OLogs log = OLogs.getInstance();
	
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
		log.log(new StringBuilder("Se encriptara un mensaje en ").append(security).toString());
		
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance(security);
			byte[] buffer = text.getBytes();
			md.update(buffer);
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			log.error(new StringBuilder("No se encuentra ese algoritmo de encriptacion: ").append(e).toString());
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
