package DB;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class User {
	private String mail;
	private byte[] hashpassword;

	public User(String mail, byte[] hashpassword) {
		this.mail = mail;
		this.hashpassword = hashpassword;
	}

	public String getMail() {
		return mail;
	}

	protected boolean correctPassword(byte[] enteredhashedpass) {
		
		if (enteredhashedpass.equals(new String(hashpassword))) {
			return true;
		} else {
			return false;
		}
	}
	
	public byte[] hasher(char[] pass) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(toBytes(pass)); // Change this to "UTF-16" if needed
		return  md.digest();
	}
	
	private byte[] toBytes(char[] chars) {
		    CharBuffer charBuffer = CharBuffer.wrap(chars);
		    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
		    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
		            byteBuffer.position(), byteBuffer.limit());
		    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
		    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
		    return bytes;
		}
}
