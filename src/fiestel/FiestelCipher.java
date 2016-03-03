package fiestel;


public class FiestelCipher {
	
	byte key[][];
	int block_size;
	int rounds;
	
	public FiestelCipher(String key,int block_size,int rounds)
	{
		
		this.key = new byte[rounds][block_size/2];
		
		for(int i=0;i<rounds;i++)
		{
			for(int j=0;j<block_size/2;j++)
			{
				this.key[i][j] = (byte)key.charAt(i*block_size/2+j);
			}
		}
		
		this.block_size = block_size;
		this.rounds = rounds;
	}
	
	public byte[] f(byte plainText[],byte key[])
	{
		byte x[] = new byte[key.length];
		
		for(int i=0;i<plainText.length;i++)
		{
			//System.out.println("key["+i+"] = "+key[i]);
			x[i] = (byte) (plainText[i]^key[i]);
		}
		
		return x;
	}
	
	
	public String encrypt(String plainText)
	{
		
		StringBuilder cipherText = new StringBuilder();
		StringBuilder plainText1 = new StringBuilder(plainText);
		
		while(plainText1.length()%8!=0)
		{
			plainText1.append("x");
		}
		
		
		
		for(int i =0;i<plainText1.length();i+=block_size)
		{
			//System.out.println(i);
			byte left[] = plainText1.toString().substring(i, i+block_size/2).getBytes();
			byte right[] = plainText1.toString().substring(i+block_size/2, i+block_size).getBytes();
			
			for(int j=0;j<rounds;j++)
			{
				
				byte ciphertextTemp[] = this.f(right,key[j]);
				//System.out.println("\nround = "+j);
				for(int k=0;k<block_size/2;k++)
				{
					ciphertextTemp[k] = (byte)(ciphertextTemp[k]^left[k]);
					left[k] = right[k];
					right[k] = ciphertextTemp[k];
				}
				
				/*for(int i1=0;i1<8;i1++)
				{
					System.out.print((char)left[i1]);
				}
				for(int i1=0;i1<8;i1++)
				{
					System.out.print((char)right[i1]);
				}*/
				
			}
			//System.out.println();
			
			for(int k=0;k<block_size/2;k++)
			{
				cipherText.append((char)(right[k]));
			}
			for(int k=0;k<block_size/2;k++)
			{
				cipherText.append((char)(left[k]));
			}
						
		}		
		
		return cipherText.toString();
	}
	
	
	public String decrypt(String cipherText)
	{
		
		byte dkey[][] = new byte[rounds][block_size/2];
		for(int i=0;i<rounds;i++)
		{
			dkey[i] = key[rounds-i-1];
		}
		key = dkey;
		
		return encrypt(cipherText); 
		
	}
	
	
	
	
	public static void main(String[] args) 
	{
		String key ="abcdefghmnopqrstzsdfgtuhhgfedcbaabcdefghmnopqrstzsdfgtuhhgfedcbaabcdefghmnopqrstzsdfgtuhhgfedcbaabcdefghmnopqrstzsdfgtuhhgfedcba";// "abcdefghmnopqrstzsdfgtuhhgfedcba";
		String plainText = "mynameissanjeevi";
		FiestelCipher fc = new FiestelCipher(key,16,16);
		String cipherText = fc.encrypt(plainText);
		System.out.println(cipherText);
		System.out.println(fc.decrypt(cipherText));
		

			
	}

}




/*
 package fiestel;


public class FiestelCipher {
	
	byte key[];
	public FiestelCipher(String key)
	{
		this.key = key.getBytes();
						
	}
	
	public byte[] f(byte plainText[],byte key[],int round)
	{
		byte x[] = new byte[key.length];
		
		for(int i=0;i<plainText.length;i++)
		{
			x[i] = (byte) (plainText[i]^key[round*8+i]);
		}
		
		return x;
	}
	
	
	public String encrypt(String plainText)
	{
		StringBuilder cipherText = new StringBuilder();
		StringBuilder plainText1 = new StringBuilder(plainText);
		
		while(plainText1.length()%8!=0)
		{
			plainText1.append("x");
		}
		
		
		
		for(int i =0;i<plainText1.length();i+=16)
		{
			byte left[] = plainText1.toString().substring(i, i+8).getBytes();
			byte right[] = plainText1.toString().substring(i+8, i+16).getBytes();
			
			for(int j=0;j<4;j++)
			{
				
				byte ciphertextTemp[] = this.f(right,this.key,j);
				
				for(int k=0;k<8;k++)
				{
					ciphertextTemp[k] = (byte)(ciphertextTemp[k]^left[k]);
					left[k] = right[k];
					right[k] = ciphertextTemp[k];
				}
				
				
			}
			
			
			for(int k=0;k<8;k++)
			{
				cipherText.append((char)(right[k]));
			}
			for(int k=0;k<8;k++)
			{
				cipherText.append((char)(left[k]));
			}
						
		}		
		
		return cipherText.toString();
	}
	
	
	public String decrypt(String cipherText)
	{
		System.out.println("Decrypting ");
		
		String x = "hgfedcbazsdfgtuhmnopqrstabcdefgh";
		key = x.getBytes();
		
		
		return encrypt(cipherText); 
		
	}
	
	
	
	
	public static void main(String[] args) 
	{
		String key = "abcdefghmnopqrstzsdfgtuhhgfedcba";
		String plainText = "mynameismehuljai";
		FiestelCipher fc = new FiestelCipher(key);
		String cipherText = fc.encrypt(plainText);
		System.out.println(cipherText);
		System.out.println(fc.decrypt(cipherText));
		

			
	}

}

 */

