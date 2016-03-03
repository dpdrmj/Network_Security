package classicalCiphers;


public class PlayFair implements CipherAlgorithm{
	String key;
	char A[][] = new char[5][5];
	
	PlayFair(String key) {
		// TODO Auto-generated constructor stub
		this.key = key;
		int a[] = new int[26];
		for(int i=0;i<26;i++)a[i]=0;
		int j=0,k=0;
		
		for(int i=0;i<key.length();i++)
		{
			if(a[key.charAt(i)-'A']==0)
			{
				A[j][k++] = key.charAt(i);
				a[key.charAt(i)-'A']++;
				if(k==5)
					{j++;k=0;}
			}
		}
		/**assuming that keyword doesn't contain i and j**/
		
		for(int x=0;x<26;x++)
		{
			if(a[x]==0 && x!=(int)('I'-'A'))  /*not adding i to the matrix*/
			{
				if(j==5)
					break;
				A[j][k++] = (char)('A'+x);
				a[x]++;
				if(k==5)
				{j++;k=0;}
			}
		}
		
		for(int x=0;x<5;x++)
		{
			for(int y=0;y<5;y++)
			{
				System.out.print(A[x][y]+" ");
			}
			System.out.println();
		}
	}
	
	
	void substitute(StringBuilder cipherText,char ch1,char ch2)
	{
		int i1=0,j1=0,i2=0,j2=0;
		
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(A[i][j]==ch1)
				{
					i1 = i;
					j1 = j;
				}
				
				if(A[i][j]==ch2)
				{
					i2 = i;
					j2 = j;
				}
				
			}
		}
		
		
		if(i1==i2)
		{
			cipherText.append(A[i1][(j1+1)%5]);
			cipherText.append(A[i2][(j2+1)%5]);
		}
		else if(j1==j2)
		{
			cipherText.append(A[(i1+1)%5][j1]);
			cipherText.append(A[(i2+1)%5][j2]);
		}
		else
		{
			cipherText.append(A[i1][j2]);
			cipherText.append(A[i2][j1]);
		}
		
		
	}
	
	void dsubstitute(StringBuilder cipherText,char ch1,char ch2)
	{
		int i1=0,j1=0,i2=0,j2=0;
		
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(A[i][j]==ch1)
				{
					i1 = i;
					j1 = j;
				}
				
				if(A[i][j]==ch2)
				{
					i2 = i;
					j2 = j;
				}
				
			}
		}
		
		
		if(i1==i2)
		{
			cipherText.append(A[i1][(j1+4)%5]);
			cipherText.append(A[i2][(j2+4)%5]);
		}
		else if(j1==j2)
		{
			cipherText.append(A[(i1+4)%5][j1]);
			cipherText.append(A[(i2+4)%5][j2]);
		}
		else
		{
			cipherText.append(A[i1][j2]);
			cipherText.append(A[i2][j1]);
		}
		
		
	}
	@Override
	public String encrypt(String plainText)
	{
		String plainText1 = plainText.toUpperCase().replace('I', 'J').replace(" ", "");
		StringBuilder cipherText = new StringBuilder();
		int i=0,n=plainText1.length();
		
		while(i<n)
		{
			char ch1 = plainText1.charAt(i);
			char ch2;
			
			if(i==n-1)
				ch2='X';
			else
			    ch2 = plainText1.charAt(i+1);
			
			if(ch1!=ch2)
			{
				this.substitute(cipherText,ch1,ch2);
				i = i+2;
			}
			
			else
			{
				ch2 = 'X';
				this.substitute(cipherText,ch1,ch2);
				i++;
			}
		}
		
		
		return cipherText.toString();
	}
	
	@Override
	public String decrypt(String cipherText)
	{
		StringBuilder plainText = new StringBuilder();
		int i=0,n=cipherText.length();
		
		while(i<n)
		{
			char ch1 = cipherText.charAt(i);
			char ch2;
			
			if(i==n-1)
				ch2='X';
			else
			    ch2 = cipherText.charAt(i+1);
			
			if(ch1!=ch2)
			{
				this.dsubstitute(plainText,ch1,ch2);
				i = i+2;
			}
			
			else
			{
				ch2 = 'X';
				this.dsubstitute(plainText,ch1,ch2);
				i++;
			}
		}
		return plainText.toString();
	}

}
