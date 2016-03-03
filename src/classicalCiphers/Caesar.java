package classicalCiphers;


class Caesar implements CipherAlgorithm {

	String key;
	Caesar(String key)
	{
		this.key = key;
	}
	@Override
	public String encrypt(String plainText) {
		
		StringBuilder cipherText = new StringBuilder();
		char key1 = key.charAt(0);
		String plainText1 = plainText.toUpperCase();
		
		for(int i = 0;i<plainText1.length();i++)
		{
			char ch = plainText1.charAt(i);
			if(ch>='A'&&ch<='Z')
			{
				cipherText.append((char)((ch-'A'+key1-'A')%26+'A'));
			}
		}
       
		return cipherText.toString();
	}

	@Override
	public String decrypt(String cipherText) {
		
		StringBuilder plainText = new StringBuilder();
		char key1 = key.charAt(0);
		
		for(int i =0;i<cipherText.length();i++)
		{
			char ch = cipherText.charAt(i);
			if(ch>='A'&&ch<='Z')
			{
				plainText.append((char)((ch-key1+26)%26+'a'));
			}
		}
		
		return plainText.toString();
	}
}
