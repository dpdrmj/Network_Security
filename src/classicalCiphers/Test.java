package classicalCiphers;
public class Test {

public static void main(String[] args) {
		
	
	String plainText="";
		
	/*
		String key = "K";
		String cipherText="WMCEEIKLGRPIFVMEUGXQPWQVIOIAVEYXUEKFKBTALVXTGAFXYEVKPAGY";
		Caesar crypt = new Caesar(key);
		cipherText = crypt.encrypt(plainText);*/
		
		
	 System.out.println("playfair cipher");
	plainText = "thegoldthetreestump";
	PlayFair p = new PlayFair("ABCDE");
	System.out.println(p.encrypt(plainText));
	System.out.println(p.decrypt(p.encrypt(plainText)));
		
	
	System.out.println("hill cipher");
	Hill h = new Hill();
	System.out.println(h.encrypt("MINDIT"));
	System.out.println(h.decrypt(h.encrypt("MINDIT")));
	

	System.out.println("vigenere cipher");
	Vigenere v = new Vigenere("ABCD");
	plainText = "MICHIGAN TECHNOLOGICAL UNIVERSITY";
	System.out.println(v.encrypt(plainText));
	System.out.println(v.decrypt(v.encrypt(plainText)));
	
	
	
	}

}
