package classicalCiphers;

class Vigenere implements CipherAlgorithm {

	String key;
	Vigenere(String key)
	{
		this.key = key;
	}
	public String encrypt(String plainText)
	{
		StringBuilder cipherText= new StringBuilder();
		plainText = plainText.toUpperCase();
		StringBuilder plainText1 = new StringBuilder();
		
		for(int i=0;i<plainText.length();i++)
		{
			char ch = plainText.charAt(i);
			
			if(ch>='A'&&ch<='Z')
			{
				plainText1.append(ch);
			}
		}
		
		int j = 0;
		for(int i = 0;i<plainText1.length();i++)
		{
			char ch = plainText1.charAt(i);
			cipherText.append((char)((ch-'A'+(key.charAt(j++%key.length())-'A'))%26+'A'));
					
		}
		return cipherText.toString();
		
	}

	
	@Override
	public String decrypt(String cipherText) {
		String cipherText2 = cipherText;
		StringBuilder plainText = new StringBuilder();
		int j = 0;
		for(int i =0;i<cipherText2.length();i++)
		{
			char ch = cipherText2.charAt(i);
			if(ch>='A'&&ch<='Z')
				plainText.append((char)((ch-key.charAt(j++%key.length())+26)%26+'a'));	
		}
		
		
		
		
		return plainText.toString();
	}
	

}
