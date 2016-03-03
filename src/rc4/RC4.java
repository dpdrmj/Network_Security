package rc4;


public class RC4 {
	int S[];

	RC4()
	{
		S = new int[256];
		
	}
	
	void keyInit(int key[])
	{
		/*initialization*/
		int T[] = new int[256];
		for(int i=0;i<256;i++)
		{
			S[i] = i;
			T[i] = key[i%key.length];
		}
		
		/*permutation*/
		int j=0;
		for(int i=0;i<256;i++)
		{
			j = (j+S[i]+T[i])%256;
			
			int t = S[i];
			S[i] = S[j];
			S[j] = t;
		}
		T = null;
				
	}
	
	
	String[] encrypt(int key[],int plainText[])
	{
		keyInit(key);
		int i=0,j=0;
		String cipherText[] = new String[plainText.length];
		
		for(int k=0;k<plainText.length;k++)
		{
			i = (i+1)%256;
			j = (j+S[i])%256;
			int t = S[i];
			S[i] = S[j];
			S[j] = t;
			t = (S[i]+S[j])%256;
			//key = S[t];
			
			cipherText[k] = Integer.toHexString(plainText[k]^S[t]);
			
		}
		return cipherText;
	}
	
	String[] decrypt(int key[],int cipherText[])
	{
		return encrypt(key, cipherText);
	}
	
	
	
	public static void main(String[] args)
	{
		int key[] = {0x12,0x32,0xff,0xef,0xdd,0xab,0x1a,0x2f};
		int plainText[] = {0x23,0x33,0x12,0xaa,0xdf,0xff,0xab,0xcb,0xfe,0xcd,0xcc};
				
		
		RC4 x = new RC4();
		
		String cipherText[] = x.encrypt(key, plainText);
		int cipherText1[] = new int[cipherText.length];
		System.out.println("Encryption");
		for(int i=0;i<cipherText.length;i++)
		{
			System.out.print(cipherText[i]);
			cipherText1[i] = Integer.parseInt(cipherText[i],16);
		}
		System.out.println();
		System.out.println("decryption");
		String plainText1[] = x.decrypt(key, cipherText1);
		
		for(int i=0;i<plainText1.length;i++)
		{
			System.out.print(plainText1[i]);
			
		}
        System.out.println();

	}

}
