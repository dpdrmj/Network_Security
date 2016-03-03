package classicalCiphers;

public interface CipherAlgorithm {

	String encrypt(String plainText);
	String decrypt(String cipherText);
}
